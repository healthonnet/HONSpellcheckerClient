package org.healthonnet.demo.spellcheckclient.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.healthonnet.demo.spellcheckclient.SpellcheckDeserializer;
import org.healthonnet.demo.spellcheckclient.data.Spellcheck;
import org.healthonnet.demo.spellcheckclient.data.SpellcheckResponse;
import org.healthonnet.demo.spellcheckclient.data.Suggestion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParsingTest {
	
	private Gson gson;
	
	@Before
	public void setUpGson() {
		
		gson = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(Spellcheck.class, new SpellcheckDeserializer())
			.create();
	}
	
	@Test
	public void testSingleWordSuggestion() throws IOException {
		
		SpellcheckResponse spellcheckResponse = getSpellcheckResponse("alzeimer");
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(1, suggestions.size());
		Assert.assertEquals("alzeimer", suggestions.get(0).getOriginalString());
		Assert.assertEquals(1, suggestions.get(0).getSuggestion().size());
		Assert.assertEquals("alzheimer", suggestions.get(0).getSuggestion().get(0));
	}
	
	@Test
	public void testMultipleWordSuggestion() throws IOException {
		SpellcheckResponse spellcheckResponse = getSpellcheckResponse("alzeimer", "diseas");
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(2, suggestions.size());
		Assert.assertEquals("alzeimer", suggestions.get(0).getOriginalString());
		Assert.assertEquals(1, suggestions.get(0).getSuggestion().size());
		Assert.assertEquals("alzheimer", suggestions.get(0).getSuggestion().get(0));
		
		Assert.assertEquals("diseas", suggestions.get(1).getOriginalString());
		Assert.assertEquals(1, suggestions.get(1).getSuggestion().size());
		Assert.assertEquals("disease", suggestions.get(1).getSuggestion().get(0));
		
	}
	
	private SpellcheckResponse getSpellcheckResponse(String... terms) throws IOException {
		
		String encodedTerms = null;
		try {
			encodedTerms = URLEncoder.encode(Joiner.on(' ').join(terms), Charsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			Throwables.propagate(e); // shouldn't happen
		}
		
		String request = "http://services.hon.ch/hon-search/select" +
				"?spellcheck=true" +
				"&wt=json" +
				"&rows=0" +
				"&indent=on" +
				"&q=" +
				encodedTerms;
		
		String json = getHttpResponse(request);
		SpellcheckResponse spellcheckResponse = gson.fromJson(json, SpellcheckResponse.class);
		
		// for debugging
		System.out.println(spellcheckResponse);
		System.out.println(gson.toJson(spellcheckResponse));
		
		return spellcheckResponse;
	}
	
	private String getHttpResponse(String url) throws IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("accept", "application/json");
		HttpResponse response = httpclient.execute(httpget);
		InputStream inputStream = response.getEntity().getContent();
		
		return CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
	}
}
