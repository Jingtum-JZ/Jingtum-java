package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
public class JingtumCurrency extends JingtumObject{
	double value;
	String currency;
	String counterparty;
	String issuer;	
	/**
	 * @param issuer currency issuer, used in post payment
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * @return currency value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value currency value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	/**
	 * @return currency unit
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency currency unit
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return counter party
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * @param counterparty
	 */
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	/**
	 * @return currency issuer
	 */
	public String getIssuer() {
		return issuer;
	}
}

