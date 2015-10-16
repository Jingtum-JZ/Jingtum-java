package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
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
	 * @return current price
	 */
	public JingtumCurrency getPrice() {
		return price;
	}
	/**
	 * @return actual amount get
	 */
	public JingtumCurrency getTaker_gets_funded() {
		return taker_gets_funded;
	}
	/**
	 * @return total amount get
	 */
	public JingtumCurrency getTaker_gets_total() {
		return taker_gets_total;
	}
	/**
	 * @return actual amount pay
	 */
	public JingtumCurrency getTaker_pays_funded() {
		return taker_pays_funded;
	}
	/**
	 * @return total amount pay
	 */
	public JingtumCurrency getTaker_pays_total() {
		return taker_pays_total;
	}
	/**
	 * @return order maker
	 */
	public String getOrder_maker() {
		return order_maker;
	}
	/**
	 * @return transaction sequence
	 */
	public long getSequence() {
		return sequence;
	}
	/**
	 * @return true if transaction is passive
	 */
	public Boolean getPassive() {
		return passive;
	}
	/**
	 * @return true if the order is sell
	 */
	public Boolean getSell() {
		return sell;
	}	
}
