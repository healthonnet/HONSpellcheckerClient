package org.healthonnet.spellchecker.client.data;

public class SpellcheckResponse {

	private ResponseHeader responseHeader;
	private Spellcheck spellcheck;
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public Spellcheck getSpellcheck() {
		return spellcheck;
	}
	public void setSpellcheck(Spellcheck spellcheck) {
		this.spellcheck = spellcheck;
	}
	@Override
	public String toString() {
		return "SpellcheckResponse [responseHeader=" + responseHeader
				+ ", spellcheck=" + spellcheck + "]";
	}
}
