package org.healthonnet.demo.spellcheckclient.data;

import com.google.gson.annotations.SerializedName;

public class ResponseHeader {

	private int status;
	@SerializedName("QTime")
	private long queryTime;
	
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
	@Override
	public String toString() {
		return "ResponseHeader [status=" + status + ", queryTime=" + queryTime
				+ "]";
	}
}
