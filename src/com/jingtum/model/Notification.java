package com.jingtum.model;

public class Notification extends JingtumObject {
	String account;
	String type;
	String direction;
	String state;
	String result;
	String hash;
	long date;
	String previous_hash;
	String next_hash;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getPrevious_hash() {
		return previous_hash;
	}
	public void setPrevious_hash(String previous_hash) {
		this.previous_hash = previous_hash;
	}
	public String getNext_hash() {
		return next_hash;
	}
	public void setNext_hash(String next_hash) {
		this.next_hash = next_hash;
	}
	
	

}
