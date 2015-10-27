package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 */
public class Balance extends JingtumObject{
	double value;
	String currency;
	String counterparty;	
	double freezed;	
	/**
	 * Get freezed amount. Certain amount would be freezed after user post an order.
	 * @return freezed
	 */
	public double getFreezed() {
		return freezed;
	}
	/**
	 * Get balance value
	 * @return value
	 */
	public double getValue() {
		return value;
	}	
	/**
	 * Get balance currency unit. eg. SWT, CNY
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * Get balance currency issuer
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
}
