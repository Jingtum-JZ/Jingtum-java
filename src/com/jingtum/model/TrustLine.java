package com.jingtum.model;

public class TrustLine extends JingtumObject{
	String account;
	String counterparty;
	String currency;
	String limit;
	
	public String getAccount() {
		return account;
	}

	public String getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	
}
