package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * Currency class
 */
public class JingtumCurrency extends JingtumObject{
	double value;
	String currency;
	String counterparty;
	String issuer;	
	/**
	 * Currency issuer, used in post payment
	 * @param issuer 
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * Get currency value
	 * @return value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Set currency value
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * Get currency unit
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * Set currency unit
	 * @param currency 
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * Get counter party
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * Set counter party
	 * @param counterparty
	 */
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	/**
	 * Get currency issuer
	 * @return issuer
	 */
	public String getIssuer() {
		return issuer;
	}
}

