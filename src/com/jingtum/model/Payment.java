package com.jingtum.model;

public class Payment extends JingtumObject{
	
	Boolean success;
	String client_resource_id;
	String hash;
	String state;
	String result;
	long date;
	double fee;
	String error_type;
	String error;
	String message;
	String type;
	String counterparty;
	JingtumCurrency amount;
	EffectCollection effects;	
	
	public EffectCollection getEffects() {
		return effects;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	public JingtumCurrency getAmount() {
		return amount;
	}
	public void setAmount(JingtumCurrency amount) {
		this.amount = amount;
	}
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
	public String getError_type() {
		return error_type;
	}
	public void setError_type(String error_type) {
		this.error_type = error_type;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
