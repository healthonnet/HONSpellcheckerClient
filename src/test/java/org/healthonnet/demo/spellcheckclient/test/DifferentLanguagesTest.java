package org.healthonnet.demo.spellcheckclient.test;

import static org.healthonnet.demo.spellcheckclient.SpellcheckDictionary.English;
import static org.healthonnet.demo.spellcheckclient.SpellcheckDictionary.*;

import java.io.IOException;
import java.util.List;

import org.healthonnet.demo.spellcheckclient.SpellcheckDictionary;
import org.healthonnet.demo.spellcheckclient.SpellcheckRequester;
import org.healthonnet.demo.spellcheckclient.data.SpellcheckResponse;
import org.healthonnet.demo.spellcheckclient.data.Suggestion;
import org.junit.Assert;
import org.junit.Test;

public class DifferentLanguagesTest {

	@Test
	public void testEnglishVsFrenchVsSpanish() throws IOException {
		// test "text" in English, "texte" in French, "texto" in Spanish
		
		test(English, "texte", "text");
		test(French, "text", "texte");
		test(Spanish, "text", "texto");
	}
	
	private void test(SpellcheckDictionary dictionary, String input, String correction) throws IOException {
		SpellcheckResponse spellcheckResponse = SpellcheckRequester.getSpellcheckResponse(dictionary, 1, input);
		
		List<Suggestion> suggestions = spellcheckResponse.getSpellcheck().getSuggestions();
		
		Assert.assertEquals(1, suggestions.size());
		Assert.assertEquals(input, suggestions.get(0).getOriginalString());
		Assert.assertEquals(1, suggestions.get(0).getSuggestedCorrections().size());
		Assert.assertEquals(correction, suggestions.get(0).getSuggestedCorrections().get(0).getWord());
	}
	
}
