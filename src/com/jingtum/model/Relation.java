package com.jingtum.model;

/**
 * @author jzhao
 * @version 1.0
 * Relation class
 */
public class Relation extends JingtumObject{
	String account;
	RelationType type;
	String counterparty;
	JingtumCurrency amount;	
	/**
	 * @author jzhao
	 * Relation type:
	 * authorize, friend, subscribe, all
	 */
	public enum RelationType {
		authorize, friend, subscribe, all
    }
	/**
	 * Get current account.
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * Get relation type
	 * @return type
	 */
	public RelationType getType() {
		return type;
	}
	/**
	 * Get relation counter party
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * Get amount
	 * @return amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
	
}
