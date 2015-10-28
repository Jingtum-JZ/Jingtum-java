package com.jingtum.model;
/**
 * Notification class
 * @author jzhao
 * @version 1.0
 */
public class Notification extends JingtumObject {
	String account;
	String type;
	Transaction.DirectionType direction;
	String state;
	String result;
	String hash;
	long date;
	String previous_hash;
	String next_hash;	
	/**
	 * Get notification related account
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * Get notification type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Get payment direction, incoming or outgoing
	 * @return direction
	 */
	public Transaction.DirectionType getDirection() {
		return direction;
	}
	/**
	 * Get transaction state
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * Get transaction result
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * Get transaction hash value
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * Get transaction time, in UNIXTIME
	 * @return date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * Get previous transaction URL
	 * @return previous_hash
	 */
	public String getPreviousHash() {
		return previous_hash;
	}
	/**
	 * Get next transaction URL
	 * @return next_hash
	 */
	public String getNextHash() {
		return next_hash;
	}
}
