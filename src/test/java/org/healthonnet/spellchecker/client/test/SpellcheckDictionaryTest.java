package org.healthonnet.spellchecker.client.test;

import junit.framework.Assert;

import org.healthonnet.spellchecker.client.SpellcheckDictionary;
import org.junit.Test;

public class SpellcheckDictionaryTest {

	@Test
	public void testLanguageCodes() {
		Assert.assertEquals(SpellcheckDictionary.English, SpellcheckDictionary.findByCode("en"));
		Assert.assertEquals(SpellcheckDictionary.French, SpellcheckDictionary.findByCode("fr"));
		Assert.assertEquals(SpellcheckDictionary.Spanish, SpellcheckDictionary.findByCode("es"));
		Assert.assertEquals(SpellcheckDictionary.Default, SpellcheckDictionary.findByCode("default"));
		
	}
	
}
