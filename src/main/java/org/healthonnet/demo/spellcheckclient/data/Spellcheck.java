package org.healthonnet.demo.spellcheckclient.data;

import java.util.List;

public class Spellcheck {

	private List<Suggestion> suggestions;

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	@Override
	public String toString() {
		return "Spellcheck [suggestions=" + suggestions + "]";
	}
}
