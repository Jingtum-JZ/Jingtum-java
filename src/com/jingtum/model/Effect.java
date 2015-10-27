package com.jingtum.model;
/**
 * Effect class, additional information about payments or transactions
 * @author jzhao *
 * @version 1.0
 */
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
	/**
	 * Effect type:
	 *  offer_funded, offer_partially_funded, offer_cancelled, offer_created,
		offer_bought, trust_create_local, trust_create_remote, trust_change_local,
		trust_change_remote, trust_change_balance, balance_change
	 */
	public enum EffectType {
		offer_funded, offer_partially_funded, offer_cancelled, offer_created,
		offer_bought, trust_create_local, trust_create_remote, trust_change_local,
		trust_change_remote, trust_change_balance, balance_change
    }
	/**
	 * Get effect type
	 * @return effect
	 */
	public EffectType getEffect() {
		return effect;
	}
	/**
	 * Get order type, sell or buy
	 * @return type
	 */
	public Order.OrderType getType() {
		return type;
	}
	/**
	 * For offer_funded, offer_cancelled, offer_created, get "get" currency amount
	 * @return gets
	 */
	public JingtumCurrency getGets() {
		return gets;
	}
	/**
	 * For offer_funded, offer_cancelled, offer_created, get pays currency amount
	 * @return pays
	 */
	public JingtumCurrency getPays() {
		return pays;
	}
	/**
	 * Get price
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * For offer_partially_funded
	 * @return true if remaining order cancelled
	 */
	public Boolean getCancelled() {
		return cancelled;
	}
	/**
	 * For offer_partially_funded, get remaining order amount
	 * @return remaining
	 */
	public JingtumCurrency getRemaining() {
		return remaining;
	}
	/**
	 * For offer_bought, get got amount
	 * @return got
	 */
	public JingtumCurrency getGot() {
		return got;
	}
	/**
	 * For offer_bought, get paid amount
	 * @return paid
	 */
	public JingtumCurrency getPaid() {
		return paid;
	}
	/**
	 * For trust_create_local and trust_change_local, get counter party trusted
	 * @return counterparty
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * For trust_create_local, get amount trusted
	 * @return limit
	 */
	public JingtumCurrency getLimit() {
		return limit;
	}
	/**
	 * For trust_change_local, get changed trusted currency unit
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * For trust_change_local, trust_change_remote
	 * @return from
	 */
	public JingtumCurrency getFrom() {
		return from;
	}
	/**
	 * For trust_change_local, trust_change_remote
	 * @return to
	 */
	public JingtumCurrency getTo() {
		return to;
	}
	/**
	 * For trust_change_balance, balance_change
	 * @return amount
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
}
