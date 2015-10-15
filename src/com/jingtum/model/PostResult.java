package com.jingtum.model;

public class PostResult extends JingtumObject {
	Boolean success;
	String client_resource_id;
	String hash;
	String state;
	String result;
	long date;
	double fee;
	long sequence;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getClient_resource_id() {
		return client_resource_id;
	}
	public void setClient_resource_id(String client_resource_id) {
		this.client_resource_id = client_resource_id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	
	

}
