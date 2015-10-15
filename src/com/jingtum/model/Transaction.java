package com.jingtum.model;

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
	
	public enum TranType{
		sent, received, trusted, trusting,
		convert, offernew, offercancel, offereffect,
		accountset, jingtuming, failed
	}
	
	public enum DirectionType{
		incoming, outgoing, all
	}

	public long getDate() {
		return date;
	}

	public String getHash() {
		return hash;
	}

	public TranType getType() {
		return type;
	}

	public String getFee() {
		return fee;
	}

	public String getResult() {
		return result;
	}

	public String getClient_resource_id() {
		return client_resource_id;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public JingtumCurrency getAmount() {
		return amount;
	}

	public EffectCollection getEffects() {
		return effects;
	}
	
	

}
