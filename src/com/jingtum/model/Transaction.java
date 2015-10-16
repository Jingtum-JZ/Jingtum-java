package com.jingtum.model;
/** * 
 * @author jzhao
 * @version 1.0
 * @date 2015.10
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
	 * transaction type
	 *
	 */
	public enum TranType{
		sent, received, trusted, trusting,
		convert, offernew, offercancel, offereffect,
		accountset, jingtuming, failed
	}	
	/**
	 * direction type, incoming, outgoing
	 *
	 */
	public enum DirectionType{
		incoming, outgoing, all
	}
	/**
	 * @return date, in UINIXTIME
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @return hash number
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return transaction type
	 */
	public TranType getType() {
		return type;
	}
	/**
	 * @return transaction fee, in SWT
	 */
	public String getFee() {
		return fee;
	}
	/**
	 * @return server result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @return resource id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * @return transaction counter pary
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * @return transaction amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
	/**
	 * @return transaction effect collection
	 */
	public EffectCollection getEffects() {
		return effects;
	}
}
