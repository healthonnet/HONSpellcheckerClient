package org.healthonnet.demo.spellcheckclient.data;

import java.util.List;

public class Spellcheck {

	private List<Suggestion> suggestions;
	private boolean correctlySpelled;

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	
	public boolean isCorrectlySpelled() {
		return correctlySpelled;
	}

	public void setCorrectlySpelled(boolean correctlySpelled) {
		this.correctlySpelled = correctlySpelled;
	}

	@Override
	public String toString() {
		return "Spellcheck [suggestions=" + suggestions + ", correctlySpelled="
				+ correctlySpelled + "]";
	}
}
