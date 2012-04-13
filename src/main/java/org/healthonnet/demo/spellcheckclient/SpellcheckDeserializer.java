package org.healthonnet.demo.spellcheckclient;

import java.lang.reflect.Type;
import java.util.List;

import org.healthonnet.demo.spellcheckclient.data.Spellcheck;
import org.healthonnet.demo.spellcheckclient.data.Suggestion;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SpellcheckDeserializer implements JsonDeserializer<Spellcheck> {

	public Spellcheck deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject mainObject = json.getAsJsonObject();
		JsonElement suggestionsElement = mainObject.get("suggestions");
		
		JsonArray jsonArray = suggestionsElement.getAsJsonArray();
		List<Suggestion> suggestions = Lists.newArrayList();
		
		// list goes key, values, key, values, etc.
		for (int i = 0; i < jsonArray.size() - 1; i += 2) {
			
			String originalString = jsonArray.get(i).getAsString(); // key
			JsonObject allTheRest = jsonArray.get(i + 1).getAsJsonObject(); //values
			
			Suggestion suggestion = context.deserialize(allTheRest, Suggestion.class);
			suggestion.setOriginalString(originalString);	
			suggestions.add(suggestion);
		}

		Spellcheck spellcheck = new Spellcheck();
		spellcheck.setSuggestions(suggestions);
		return spellcheck;
	}
}
