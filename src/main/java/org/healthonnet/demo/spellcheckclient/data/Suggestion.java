package org.healthonnet.demo.spellcheckclient.data;

import java.util.List;

public class Suggestion {

	private String originalString;
	private int numFound;
	private int startOffset;
	private int endOffset;
	private List<String> suggestion;
	
	public String getOriginalString() {
		return originalString;
	}
	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}
	public int getNumFound() {
		return numFound;
	}
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}
	public int getStartOffset() {
		return startOffset;
	}
	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}
	public int getEndOffset() {
		return endOffset;
	}
	public void setEndOffset(int endOffset) {
		this.endOffset = endOffset;
	}
	public List<String> getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(List<String> suggestion) {
		this.suggestion = suggestion;
	}
	@Override
	public String toString() {
		return "Suggestion [numFound=" + numFound + ", startOffset="
				+ startOffset + ", endOffset=" + endOffset + ", suggestion="
				+ suggestion + "]";
	}
}
