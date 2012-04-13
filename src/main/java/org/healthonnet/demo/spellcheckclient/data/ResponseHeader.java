package org.healthonnet.demo.spellcheckclient.data;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ResponseHeader {

	private int status;
	@SerializedName("QTime")
	private long queryTime;
	private Map<String,String> params;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(long queryTime) {
		this.queryTime = queryTime;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "ResponseHeader [status=" + status + ", queryTime=" + queryTime
				+ ", params=" + params + "]";
	}
}
