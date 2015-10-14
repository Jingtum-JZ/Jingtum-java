package com.jingtum.model;

public class Orders extends JingtumObject{
	Boolean success;
	String hash;
	String state;
	double fee;
	long sequence;
	OrderType type;
	JingtumCurrency taker_gets;
	JingtumCurrency taker_pays;
	Boolean passive;
	Order order;
	String action;
	String direction;
	String validated;
	
	public enum OrderType {
        sell, buy
    }
	
	private class Order{
		String account;
		JingtumCurrency taker_gets;
		JingtumCurrency taker_pays;
		Boolean passive;
		OrderType type;
		long sequence;
		
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public JingtumCurrency getTaker_gets() {
			return taker_gets;
		}
		public void setTaker_gets(JingtumCurrency taker_gets) {
			this.taker_gets = taker_gets;
		}
		public JingtumCurrency getTaker_pays() {
			return taker_pays;
		}
		public void setTaker_pays(JingtumCurrency taker_pays) {
			this.taker_pays = taker_pays;
		}
		public Boolean getPassive() {
			return passive;
		}
		public void setPassive(Boolean passive) {
			this.passive = passive;
		}
		public OrderType getType() {
			return type;
		}
		public void setType(OrderType type) {
			this.type = type;
		}
		public long getSequence() {
			return sequence;
		}
		public void setSequence(long sequence) {
			this.sequence = sequence;
		}
		
	}
		
	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAccount(){
		if (order != null){
			return order.getAccount();
		}
		return null;
	}
	
	public OrderType getType() {
		if (order != null){
			return order.getType();
		}
		return type;
	}
	public void setType(OrderType type) {
		this.type = type;
	}
	public JingtumCurrency getPay() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_gets();
		}
		return taker_gets;
	}
	public void setPay(JingtumCurrency taker_gets) {
		this.taker_gets = taker_gets;
	}
	public JingtumCurrency getReceive() {
		if (this.taker_gets == null && order != null){
			return order.getTaker_pays();
		}		
		return taker_pays;
	}
	public void setReceive(JingtumCurrency taker_pays) {
		this.taker_pays = taker_pays;
	}
	public Boolean getPassive() {
		if (order != null){
			return order.getPassive();
		}
		return passive;
	}
	public void setPassive(Boolean passive) {
		this.passive = passive;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public long getSequence() {
		if (order != null){
			return order.getSequence();
		}
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}	

}
