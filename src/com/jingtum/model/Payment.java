package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * Payment class
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
	/**
	 * Server state:
	 * validated or failed
	 */
	public enum StateType{
		validated, failed
	}
	/**
	 * Get payment effect effect collection
	 * @return effects
	 */
	public EffectCollection getEffects() {
		return effects;
	}
	/**
	 * Get payment type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Get currency counter party
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * Get payment amount
	 * @return amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
	/**
	 * Return true if request is successful
	 * @return success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * Get payment resource id
	 * @return client_resource_id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * Get payment hash value
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * Get state type 
	 * @return state
	 */
	public StateType getState() {
		return state;
	}
	/**
	 * Get server result
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * Get payment time, in UNIXTIME
	 * @return date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * Get payment transaction fee, in SWT
	 * @return fee
	 */
	public double getFee() {
		return fee;
	}
}
