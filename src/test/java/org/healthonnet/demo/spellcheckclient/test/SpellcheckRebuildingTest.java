package org.healthonnet.demo.spellcheckclient.test;

import java.io.IOException;

import org.healthonnet.demo.spellcheckclient.SpellcheckRequester;
import org.junit.Ignore;
import org.junit.Test;

public class SpellcheckRebuildingTest {

	@Test
	@Ignore // only run this test if you want to force a rebuild of all the spellcheckers
	public void test() throws IOException {
		SpellcheckRequester.rebuildAllSpellcheckDictionaries();
	}

}
