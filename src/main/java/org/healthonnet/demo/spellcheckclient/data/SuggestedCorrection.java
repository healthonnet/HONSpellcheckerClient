package org.healthonnet.demo.spellcheckclient.data;

public class SuggestedCorrection {

	private String word;
	private long freq;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public long getFreq() {
		return freq;
	}
	public void setFreq(long freq) {
		this.freq = freq;
	}
	@Override
	public String toString() {
		return "SuggestedCorrection [word=" + word + ", freq=" + freq + "]";
	}
}
