package com.jingtum.model;
/**
 * order book result class, used in getOrdeBook method
 * @author jzhao
 *
 */
public class OrderBookResult extends JingtumObject{
	String order_book;
	Boolean success;
	Boolean validated;
	OrderBookCollection bids;
	OrderBookCollection asks;	
	/**
	 * @return currency pair in the order. e.g. CNY+jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS/USD+jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS
	 */
	public String getOrderbook() {
		return order_book;
	}
	/**
	 * @return true if the request is successful
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @return server status
	 */
	public Boolean getValidated() {
		return validated;
	}
	/**
	 * @return bid order book collection
	 */
	public OrderBookCollection getBids() {
		return bids;
	}
	/**
	 * @return ask order book collection
	 */
	public OrderBookCollection getAsks() {
		return asks;
	}
}
