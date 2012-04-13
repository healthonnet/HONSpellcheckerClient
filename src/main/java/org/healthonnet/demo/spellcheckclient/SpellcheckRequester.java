package org.healthonnet.demo.spellcheckclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.healthonnet.demo.spellcheckclient.data.Spellcheck;
import org.healthonnet.demo.spellcheckclient.data.SpellcheckResponse;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Convenience class for requesting spelling corrections for input tokens
 * @author nolan
 *
 */
public class SpellcheckRequester {

	
	/**
	 * Query the live HON spell checker to request corrections for each of the supplied terms.
	 * 
	 * @param numCorrections number of SuggestedCorrections to provide per term
	 * @param terms list of terms to suggest corrections for
	 * @return
	 * @throws IOException if cannot connect
	 */
	public static SpellcheckResponse getSpellcheckResponse(int numCorrections, String... terms) throws IOException {
		String encodedTerms = null;
		try {
			encodedTerms = URLEncoder.encode(Joiner.on(' ').join(terms), Charsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			Throwables.propagate(e); // shouldn't happen
		}
		
		String request = "http://services.hon.ch/hon-search/select" +
				"?spellcheck=true" +
				"&wt=json" +
				"&rows=0" + // no search results
				"&indent=off" + // don't bother indexing
				"&echoParams=none" + // don't bother giving us the parameters back
				"&spellcheck.extendedResults=true" + // give us frequencies for each correction
				"&spellcheck.count=" + numCorrections + // number of corrections per term
				"&q=" +
				encodedTerms;
		
		String json = getHttpResponse(request);
		SpellcheckResponse spellcheckResponse = getGson().fromJson(json, SpellcheckResponse.class);
		
		// for debugging
		System.out.println(spellcheckResponse);
		System.out.println(getGson().toJson(spellcheckResponse));
		
		return spellcheckResponse;
	}
	
	private static String getHttpResponse(String url) throws IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("accept", "application/json");
		HttpResponse response = httpclient.execute(httpget);
		InputStream inputStream = response.getEntity().getContent();
		
		return CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
	}
	
	private static Gson getGson() {
		return SingletonHolder.GSON;
	}
	
	private static class SingletonHolder {
		// inner class singleton pattern, per "Effective Java" by Joshua Bloch
		private static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(Spellcheck.class, new SpellcheckDeserializer())
			.create(); 
	}
	
}
