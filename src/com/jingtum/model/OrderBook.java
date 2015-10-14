package com.jingtum.model;

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
	
	public JingtumCurrency getPrice() {
		return price;
	}
	public void setPrice(JingtumCurrency price) {
		this.price = price;
	}
	public JingtumCurrency getTaker_gets_funded() {
		return taker_gets_funded;
	}
	public void setTaker_gets_funded(JingtumCurrency taker_gets_funded) {
		this.taker_gets_funded = taker_gets_funded;
	}
	public JingtumCurrency getTaker_gets_total() {
		return taker_gets_total;
	}
	public void setTaker_gets_total(JingtumCurrency taker_gets_total) {
		this.taker_gets_total = taker_gets_total;
	}
	public JingtumCurrency getTaker_pays_funded() {
		return taker_pays_funded;
	}
	public void setTaker_pays_funded(JingtumCurrency taker_pays_funded) {
		this.taker_pays_funded = taker_pays_funded;
	}
	public JingtumCurrency getTaker_pays_total() {
		return taker_pays_total;
	}
	public void setTaker_pays_total(JingtumCurrency taker_pays_total) {
		this.taker_pays_total = taker_pays_total;
	}
	public String getOrder_maker() {
		return order_maker;
	}
	public void setOrder_maker(String order_maker) {
		this.order_maker = order_maker;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public Boolean getPassive() {
		return passive;
	}
	public void setPassive(Boolean passive) {
		this.passive = passive;
	}
	public Boolean getSell() {
		return sell;
	}
	public void setSell(Boolean sell) {
		this.sell = sell;
	}
	
	
}
