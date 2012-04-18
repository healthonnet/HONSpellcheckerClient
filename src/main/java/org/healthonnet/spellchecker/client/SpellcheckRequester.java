package org.healthonnet.spellchecker.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.healthonnet.spellchecker.client.data.Spellcheck;
import org.healthonnet.spellchecker.client.data.SpellcheckResponse;

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
	 * @param spellcheckDictionary the dictionary (i.e. language) to use
	 * @param numCorrections number of SuggestedCorrections to provide per term
	 * @param terms list of terms to suggest corrections for
	 * @return
	 * @throws IOException if cannot connect
	 */
	public static SpellcheckResponse getSpellcheckResponse(SpellcheckDictionary spellcheckDictionary, 
			int numCorrections, List<String> terms) throws IOException {
		String encodedTerms = null;
		try {
			encodedTerms = URLEncoder.encode(Joiner.on(' ').join(terms), Charsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			Throwables.propagate(e); // shouldn't happen
		}
		
		String request = "http://services.hon.ch/hon-search/spell" +
				"?wt=json" +
				"&indent=off" + // don't bother indenting
				"&spellcheck.extendedResults=true" + // give us frequencies for each correction
				"&spellcheck.count=" + numCorrections + // number of corrections per term
				"&spellcheck.dictionary=" + spellcheckDictionary.getCode() +
				"&q=" +
				encodedTerms;
		
		String json = getHttpResponse(request);
		SpellcheckResponse spellcheckResponse = getGson().fromJson(json, SpellcheckResponse.class);
		
		return spellcheckResponse;
	}
	
	/**
	 * Query the live HON spell checker to request corrections for each of the supplied terms.
	 * 
	 * @param spellcheckDictionary the dictionary (i.e. language) to use
	 * @param numCorrections number of SuggestedCorrections to provide per term
	 * @param terms list of terms to suggest corrections for
	 * @return
	 * @throws IOException if cannot connect
	 */
	public static SpellcheckResponse getSpellcheckResponse(SpellcheckDictionary spellcheckDictionary, 
			int numCorrections, String... terms) throws IOException {
		return getSpellcheckResponse(spellcheckDictionary, numCorrections, Arrays.asList(terms));
	}
	
	/**
	 * Ask the server to rebuild all the spellcheck dictionaries.
	 * @throws IOException if unsuccessful
	 */
	public static void rebuildAllSpellcheckDictionaries() throws IOException {
		
		for (SpellcheckDictionary spellcheckDictionary : SpellcheckDictionary.values()) {
			String request = "http://services.hon.ch/hon-search/spell" +
					"?wt=json" +
					"&indent=off" + // don't bother indenting
					"&q=foobar" + // have to put something here
					"&spellcheck.dictionary=" + spellcheckDictionary.getCode() + 
					"&spellcheck.build=true";
			getHttpResponse(request);
		}
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
