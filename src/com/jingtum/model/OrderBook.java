package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * Order book class
 */
public class OrderBook extends JingtumObject {	
	JingtumCurrency price;
	JingtumCurrency taker_gets_funded;
	JingtumCurrency taker_gets_total;
	JingtumCurrency taker_pays_funded;
	JingtumCurrency taker_pays_total;
	String order_maker;
	long sequence;
	Boolean passive;
	Boolean sell;	
	/**
	 * Get current price
	 * @return price
	 */
	public JingtumCurrency getPrice() {
		return price;
	}
	/**
	 * Get actual amount get
	 * @return taker_gets_funded
	 */
	public JingtumCurrency getTaker_gets_funded() {
		return taker_gets_funded;
	}
	/**
	 * Get total amount get
	 * @return taker_gets_total
	 */
	public JingtumCurrency getTaker_gets_total() {
		return taker_gets_total;
	}
	/**
	 * Get actual amount pay
	 * @return taker_pays_funded
	 */
	public JingtumCurrency getTaker_pays_funded() {
		return taker_pays_funded;
	}
	/**
	 * Get total amount pay
	 * @return taker_pays_total
	 */
	public JingtumCurrency getTaker_pays_total() {
		return taker_pays_total;
	}
	/**
	 * Get order maker
	 * @return order_maker
	 */
	public String getOrder_maker() {
		return order_maker;
	}
	/**
	 * Get transaction sequence
	 * @return sequence
	 */
	public long getSequence() {
		return sequence;
	}
	/**
	 * Return true if transaction is passive
	 * @return passive
	 */
	public Boolean getPassive() {
		return passive;
	}
	/**
	 * Return true if the order is sell
	 * @return sell
	 */
	public Boolean getSell() {
		return sell;
	}	
}
