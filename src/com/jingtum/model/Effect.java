package com.jingtum.model;
/**
 * effect class, additional information about payments or transactions
 * @author jzhao
 *
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
	 * effect type 
	 */
	public enum EffectType {
		offer_funded, offer_partially_funded, offer_cancelled, offer_created,
		offer_bought, trust_create_local, trust_create_remote, trust_change_local,
		trust_change_remote, trust_change_balance, balance_change
    }
	/**
	 * effect type
	 * @return
	 */
	public EffectType getEffect() {
		return effect;
	}
	/**
	 * order type, sell or buy
	 * @return
	 */
	public Order.OrderType getType() {
		return type;
	}
	/**
	 * for offer_funded, offer_cancelled, offer_created
	 * @return
	 */
	public JingtumCurrency getGets() {
		return gets;
	}
	/**
	 * for offer_funded, offer_cancelled, offer_created
	 * @return
	 */
	public JingtumCurrency getPays() {
		return pays;
	}
	public String getPrice() {
		return price;
	}
	/**
	 * for offer_partially_funded
	 * @return true if remaining order cancelled
	 */
	public Boolean getCancelled() {
		return cancelled;
	}
	/**
	 * for offer_partially_funded, get remaining order amount
	 * @return
	 */
	public JingtumCurrency getRemaining() {
		return remaining;
	}
	/**
	 * for offer_bought
	 * @return
	 */
	public JingtumCurrency getGot() {
		return got;
	}
	/**
	 * for offer_bought
	 * @return
	 */
	public JingtumCurrency getPaid() {
		return paid;
	}
	/**
	 * for trust_create_local and trust_change_local
	 * @return counter party trusted
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * for trust_create_local 
	 * @return amount trusted
	 */
	public JingtumCurrency getLimit() {
		return limit;
	}
	/**
	 * for trust_change_local
	 * @return changed trusted currency unit
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * for trust_change_local, trust_change_remote
	 * @return 
	 */
	public JingtumCurrency getFrom() {
		return from;
	}
	/**
	 * for trust_change_local, trust_change_remote
	 * @return
	 */
	public JingtumCurrency getTo() {
		return to;
	}
	/**
	 * trust_change_balance, balance_change
	 * @return
	 */
	public JingtumCurrency getAmount() {
		return amount;
	}
}
