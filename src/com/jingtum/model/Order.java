package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 */
public class Order extends JingtumObject{
	Boolean success;
	String hash;
	double fee;
	long sequence;
	OrderType type;
	JingtumCurrency taker_gets;
	JingtumCurrency taker_pays;
	Boolean passive;
	MyOrder order;
	String action;
	Transaction.DirectionType direction;
	String validated;	
	/**
	 * Order type, sell or buy	 *
	 */
	public enum OrderType {
        sell, buy
    }	
	/**
	 * During to the jason structure returned from Http request, need a private order structure here
	 *
	 */
	private class MyOrder{
		String account;
		JingtumCurrency taker_gets;
		JingtumCurrency taker_pays;
		Boolean passive;
		OrderType type;
		long sequence;		
		public String getAccount() {
			return account;
		}
		public JingtumCurrency getTaker_gets() {
			return taker_gets;
		}
		public JingtumCurrency getTaker_pays() {
			return taker_pays;
		}
		public Boolean getPassive() {
			return passive;
		}
		public OrderType getType() {
			return type;
		}
		public long getSequence() {
			return sequence;
		}		
	}		
	/**
	 * Get server state
	 * @return validated
	 */
	public String getValidated() {
		return validated;
	}
	/**
	 * Get action type
	 * @return action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * Get direction type, incoming or outgoing
	 * @return direction
	 */
	public Transaction.DirectionType getDirection() {
		return direction;
	}
	/**
	 * Get account
	 * @return account
	 */
	public String getAccount(){
		if (order != null){
			return order.getAccount();
		}
		return null;
	}	
	/**
	 * Get order type
	 * @return type
	 */
	public OrderType getType() {
		if (order != null){
			return order.getType();
		}
		return type;
	}
	/**
	 * Get amount pays in the order
	 * @return taker_gets
	 */
	public JingtumCurrency getPay() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_gets();
		}
		return taker_gets;
	}
	/**
	 * Get amount receive
	 * @return taker_pays
	 */
	public JingtumCurrency getReceive() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_pays();
		}		
		return taker_pays;
	}
	/**
	 * Return true if it is passive transaction
	 * @return passive
	 */
	public Boolean getPassive() {
		if (order != null){
			return order.getPassive();
		}
		return passive;
	}
	/**
	 * Return true if the request is successful
	 * @return success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * Get transaction hash value
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * Get transaction fee, in SWT
	 * @return fee
	 */
	public double getFee() {
		return fee;
	}
	/**
	 * Get transaction sequence number
	 * @return sequence
	 */
	public long getSequence() {
		if (order != null){
			return order.getSequence();
		}
		return sequence;
	}
}
