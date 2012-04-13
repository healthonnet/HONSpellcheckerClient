package org.healthonnet.demo.spellcheckclient.data;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

	private String originalString;
	private int numFound;
	private int startOffset;
	private int endOffset;
	@SerializedName("suggestion")
	private List<SuggestedCorrection> suggestedCorrections;
	
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
	public List<SuggestedCorrection> getSuggestedCorrections() {
		return suggestedCorrections;
	}
	public void setSuggestedCorrections(
			List<SuggestedCorrection> suggestedCorrections) {
		this.suggestedCorrections = suggestedCorrections;
	}
	@Override
	public String toString() {
		return "Suggestion [numFound=" + numFound + ", startOffset="
				+ startOffset + ", endOffset=" + endOffset + ", suggestion="
				+ suggestedCorrections + "]";
	}
}
