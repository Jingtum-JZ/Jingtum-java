package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
public class Balance extends JingtumObject{
	double value;
	String currency;
	String counterparty;	
	/**
	 * get balance value
	 * @return 
	 */
	public double getValue() {
		return value;
	}	
	/**
	 * get balance currency unit
	 * @return 
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * get balance counterparty
	 * @return 
	 */
	public String getCounterparty() {
		return counterparty;
	}
}
