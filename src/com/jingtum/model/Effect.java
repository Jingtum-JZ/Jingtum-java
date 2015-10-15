package com.jingtum.model;

public class Effect extends JingtumObject{
	
	EffectType effect;
	Order.OrderType type;
	JingtumCurrency gets;
	JingtumCurrency pays;
	String price;
	Boolean cancelled;
	JingtumCurrency remaining;
	JingtumCurrency got;
	JingtumCurrency paid;
	String counterparty;
	JingtumCurrency limit;
	String currency;
	JingtumCurrency from;
	JingtumCurrency to;
	JingtumCurrency amount;	
	
	public enum EffectType {
		offer_funded, offer_partially_funded, offer_cancelled, offer_created,
		offer_bought, trust_create_local, trust_create_remote, trust_change_local,
		trust_change_remote, trust_change_balance, balance_change
    }

	public EffectType getEffect() {
		return effect;
	}

	public Order.OrderType getType() {
		return type;
	}

	public JingtumCurrency getGets() {
		return gets;
	}

	public JingtumCurrency getPays() {
		return pays;
	}

	public String getPrice() {
		return price;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public JingtumCurrency getRemaining() {
		return remaining;
	}

	public JingtumCurrency getGot() {
		return got;
	}

	public JingtumCurrency getPaid() {
		return paid;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public JingtumCurrency getLimit() {
		return limit;
	}

	public String getCurrency() {
		return currency;
	}

	public JingtumCurrency getFrom() {
		return from;
	}

	public JingtumCurrency getTo() {
		return to;
	}

	public JingtumCurrency getAmount() {
		return amount;
	}
	
	

}
