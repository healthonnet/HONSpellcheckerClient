package org.healthonnet.demo.spellcheckclient;

import java.util.Arrays;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * List of dictionaries (i.e. languages) currently accepted by the HON Spellchecker.
 * 
 * "Default" means a dictionary that includes all languages.
 * @author nolan
 *
 */
public enum SpellcheckDictionary {
	
	Default ("default"),
	Bulgarian ("bg"),
	Czech ("cs"),
	Danish ("da"),
	German ("de"),
	Greek ("el"),
	English ("en"),
	Spanish ("es"),
	Estonian ("et"),
	Croatian ("hr"),
	Finnish ("fi"),
	French ("fr"),
	Hungarian ("hu"),
	Icelandic ("is"),
	Italian ("it"),
	Lithuanian ("lt"),
	Latvian ("lv"),
	Maltese ("mt"),
	Dutch ("nl"),
	Norwegian ("no"),
	Polish ("pl"),
	Portuguese ("pt"),
	Romanian ("ro"),
	Slovakian ("sk"),
	Slovenian ("sl"),
	Swedish ("sv"),
	;
	
	private static ImmutableMap<String,SpellcheckDictionary> codeLookup = ImmutableMap.copyOf(
			Maps.uniqueIndex(Arrays.asList(SpellcheckDictionary.values()).iterator(), new Function<SpellcheckDictionary, String>() {

				public String apply(SpellcheckDictionary dictionary) {
					return dictionary.getCode();
				}
			}));
	
	private String code;
	
	private SpellcheckDictionary(String code) {
		this.code = code;
	}
	
	/**
	 * Code for this dictionary, as accepted by the spellchecker system.  Most are ISO 639-1 codes, but one (Default) is just "default".
	 * @return
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Return the spellcheck dictionary by its code (usually a 2-letter iso code), or throw a null pointer if not found.
	 * @param code
	 * @throws NullPointerException if the code is not found
	 */
	public static SpellcheckDictionary findByCode(String code) {
		return Preconditions.checkNotNull(codeLookup.get(code));
	}
}
