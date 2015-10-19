package com.jingtum.model;
/**
 * Post request result class
 * @author jzhao
 * @version 1.0
 */
public class PostResult extends JingtumObject {
	Boolean success;
	String client_resource_id;
	String hash;
	String state;
	String result;
	long date;
	double fee;
	long sequence;
	/**
	 * Get true if request is successful
	 * @return success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * Get resource id
	 * @return client_resource_id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * Get hash number
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * Get server state
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * Get server result, tesSuccess means successful
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * Get time, in UNIXTIME
	 * @return date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * Get transaction fee, in SWT
	 * @return fee
	 */
	public double getFee() {
		return fee;
	}
	/**
	 * Get sequence number
	 * @return sequence
	 */
	public long getSequence() {
		return sequence;
	}
}
