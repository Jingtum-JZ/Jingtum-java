package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
public class TrustLine extends JingtumObject{
	String account;
	String counterparty;
	String currency;
	String limit;	
	/**
	 * @return current account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @return trusted counter party
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * @return trusted currency unit
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @return trusted amount limit
	 */
	public String getLimit() {
		return limit;
	}
	/**
	 * Set trust line counter party
	 * @param counterparty
	 */
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	/**
	 * set trust line currency
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * set trust line limit
	 * @param limit
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}		
}
