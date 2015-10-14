package com.jingtum.model;

public class OrderBookResult extends JingtumObject{
	String order_book;
	String success;
	String validated;
	OrderBookCollection bids;
	OrderBookCollection asks;
	
	public String getOrderbook() {
		return order_book;
	}
	public void setOrderbook(String orderbook) {
		this.order_book = orderbook;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getValidated() {
		return validated;
	}
	public void setValidated(String validated) {
		this.validated = validated;
	}
	public OrderBookCollection getBids() {
		return bids;
	}
	public void setBids(OrderBookCollection bids) {
		this.bids = bids;
	}
	public OrderBookCollection getAsks() {
		return asks;
	}
	public void setAsks(OrderBookCollection asks) {
		this.asks = asks;
	}
	
	

}
