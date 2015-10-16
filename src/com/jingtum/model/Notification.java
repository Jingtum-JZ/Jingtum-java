package com.jingtum.model;
/**
 * Notification class
 * @author jzhao
 *
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
	 * @return notification related account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @return notification type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return payment direction, incoming or outgoing
	 */
	public Transaction.DirectionType getDirection() {
		return direction;
	}
	/**
	 * @return transaction state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return transaction result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @return transaction hash value
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return transaction UNIXTIME
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @return previous transaction URL
	 */
	public String getPrevious_hash() {
		return previous_hash;
	}
	/**
	 * @return next transaction URL
	 */
	public String getNext_hash() {
		return next_hash;
	}
}
