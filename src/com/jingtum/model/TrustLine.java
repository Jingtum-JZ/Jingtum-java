package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * Trust line class
 */
public class TrustLine extends JingtumObject{
	String account;
	String counterparty;
	String currency;
	String limit;	
	/**
	 * Get current account
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * Get trusted counter party
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * Get trusted currency unit
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * Get trusted amount limit
	 * @return limit
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
	 * Set trust line currency
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * Set trust line limit
	 * @param limit
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}		
}
