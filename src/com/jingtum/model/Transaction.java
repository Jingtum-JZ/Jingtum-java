package com.jingtum.model;
/** * 
 * @author jzhao
 * @version 1.0
 */
public class Transaction extends JingtumObject{
	long date;
	String hash;
	TranType type;
	String fee;
	String result;
	String client_resource_id;
	String counterparty;
	JingtumCurrency amount;
	EffectCollection effects;
	/**
	 * Transaction type:
	 *  sent, received, trusted, trusting,
	 *	convert, offernew, offercancel, offereffect,
	 *	accountset, jingtuming, failed
	 *
	 */
	public enum TranType{
		sent, received, trusted, trusting,
		convert, offernew, offercancel, offereffect,
		accountset, jingtuming, failed
	}	
	/**
	 * Direction type:
	 * incoming, outgoing
	 *
	 */
	public enum DirectionType{
		incoming, outgoing, all
	}
	/**
	 * Get date, in UNIXTIME
	 * @return date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * Get hash number
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * Get transaction type
	 * @return type
	 */
	public TranType getType() {
		return type;
	}
	/**
	 * Get transaction fee, in SWT
	 * @return fee
	 */
	public String getFee() {
		return fee;
	}
	/**
	 * Get server result
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * Get resource id
	 * @return client_resource_id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * Get transaction counter party
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * Get transaction amount
	 * @return amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
	/**
	 * Get transaction effect collection
	 * @return effects
	 */
	public EffectCollection getEffects() {
		return effects;
	}
}
