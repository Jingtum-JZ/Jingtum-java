package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
public class Payment extends JingtumObject{	
	Boolean success;
	String client_resource_id;
	String hash;
	StateType state;
	String result;
	long date;
	double fee;
	String type;
	String counterparty;
	JingtumCurrency amount;
	EffectCollection effects;		
	
	public enum StateType{
		validated, failed
	}
	/**
	 * @return payment effect effect collection
	 */
	public EffectCollection getEffects() {
		return effects;
	}
	/**
	 * @return payment type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return currency counter party
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * @return payment amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
	/**
	 * @return true if reques is successful
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @return payment resource id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * @return payment hash value
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return state type
	 */
	public StateType getState() {
		return state;
	}
	/**
	 * @return server result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @return payment time, in UNIXTIME
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @return payment transaction fee, in SWT
	 */
	public double getFee() {
		return fee;
	}
}
