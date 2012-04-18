package org.healthonnet.spellchecker.client.test;

import java.io.IOException;

import org.healthonnet.spellchecker.client.SpellcheckRequester;
import org.junit.Ignore;
import org.junit.Test;

public class SpellcheckRebuildingTest {

	@Test
	@Ignore // only run this test if you want to force a rebuild of all the spellcheckers
	public void test() throws IOException {
		SpellcheckRequester.rebuildAllSpellcheckDictionaries();
	}

}
