package com.jingtum.model;
/**
 * Order book result class, used in getOrdeBook method
 * @author jzhao
 * @version 1.0
 */
public class OrderBookResult extends JingtumObject{
	String order_book;
	Boolean success;
	Boolean validated;
	OrderBookCollection bids;
	OrderBookCollection asks;	
	/**
	 * Get currency pair in the order. e.g. CNY+jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS/USD+jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS
	 * @return order_book
	 */
	public String getOrderbook() {
		return order_book;
	}
	/**
	 * Return true if the request is successful
	 * @return success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * Return server status
	 * @return validated
	 */
	public Boolean getValidated() {
		return validated;
	}
	/**
	 * Get bid order book collection
	 * @return bids
	 */
	public OrderBookCollection getBids() {
		return bids;
	}
	/**
	 * Get ask order book collection
	 * @return asks
	 */
	public OrderBookCollection getAsks() {
		return asks;
	}
}
