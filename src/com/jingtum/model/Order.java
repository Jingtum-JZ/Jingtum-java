package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
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
	 * Order type, sell or buy
	 *
	 */
	public enum OrderType {
        sell, buy
    }	
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
	 * @return server state
	 */
	public String getValidated() {
		return validated;
	}
	/**
	 * @return order action type
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @return direction type, incoming or outgoing
	 */
	public Transaction.DirectionType getDirection() {
		return direction;
	}
	/**
	 * @return order account
	 */
	public String getAccount(){
		if (order != null){
			return order.getAccount();
		}
		return null;
	}	
	/**
	 * @return order type
	 */
	public OrderType getType() {
		if (order != null){
			return order.getType();
		}
		return type;
	}
	/**
	 * @return amount pays in the order
	 */
	public JingtumCurrency getPay() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_gets();
		}
		return taker_gets;
	}
	/**
	 * @return amount receive
	 */
	public JingtumCurrency getReceive() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_pays();
		}		
		return taker_pays;
	}
	/**
	 * @return passive transaction or not
	 */
	public Boolean getPassive() {
		if (order != null){
			return order.getPassive();
		}
		return passive;
	}
	/**
	 * @return request status
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @return transaction hash value
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return transaction fee, in SWT
	 */
	public double getFee() {
		return fee;
	}
	/**
	 * @return transaction sequence number
	 */
	public long getSequence() {
		if (order != null){
			return order.getSequence();
		}
		return sequence;
	}
}
