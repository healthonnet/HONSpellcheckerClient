package org.healthonnet.demo.spellcheckclient.test;

import java.io.IOException;
import java.util.List;

import org.healthonnet.demo.spellcheckclient.SpellcheckRequester;
import org.healthonnet.demo.spellcheckclient.data.SpellcheckResponse;
import org.healthonnet.demo.spellcheckclient.data.SuggestedCorrection;
import org.healthonnet.demo.spellcheckclient.data.Suggestion;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Longs;

public class JsonParsingTest {
	
	@Test
	public void testSingleWordSuggestion() throws IOException {
		
		SpellcheckResponse spellcheckResponse = SpellcheckRequester.getSpellcheckResponse(1, "alzeimer");
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(1, suggestions.size());
		Assert.assertEquals("alzeimer", suggestions.get(0).getOriginalString());
		Assert.assertEquals(1, suggestions.get(0).getSuggestedCorrections().size());
		Assert.assertEquals("alzheimer", suggestions.get(0).getSuggestedCorrections().get(0).getWord());
	}
	
	@Test
	public void testMultipleWordSuggestion() throws IOException {
		SpellcheckResponse spellcheckResponse = SpellcheckRequester.getSpellcheckResponse(1, "alzeimer", "diseas");
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(2, suggestions.size());
		Assert.assertEquals("alzeimer", suggestions.get(0).getOriginalString());
		Assert.assertEquals(1, suggestions.get(0).getSuggestedCorrections().size());
		Assert.assertEquals("alzheimer", suggestions.get(0).getSuggestedCorrections().get(0).getWord());
		
		Assert.assertEquals("diseas", suggestions.get(1).getOriginalString());
		Assert.assertEquals(1, suggestions.get(1).getSuggestedCorrections().size());
		Assert.assertEquals("disease", suggestions.get(1).getSuggestedCorrections().get(0).getWord());
		
	}
	
	@Test
	public void testMultipleSuggestions() throws IOException {
		SpellcheckResponse spellcheckResponse = SpellcheckRequester.getSpellcheckResponse(15, "diabetis");
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(1, suggestions.size());
		Assert.assertEquals("diabetis", suggestions.get(0).getOriginalString());
		Assert.assertEquals(15, suggestions.get(0).getSuggestedCorrections().size());
		
		// sort the suggested corrections by the max frequency
		SuggestedCorrection maxSuggestedCorrection = new Ordering<SuggestedCorrection>(){

			@Override
			public int compare(SuggestedCorrection left, SuggestedCorrection right) {
				return Longs.compare(left.getFreq(), right.getFreq());
			}}
			.max(suggestions.get(0).getSuggestedCorrections());
		
		// "diabetes" should have the highest frequency
		Assert.assertEquals("diabetes", maxSuggestedCorrection.getWord());
	}
}
