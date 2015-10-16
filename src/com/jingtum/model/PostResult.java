package com.jingtum.model;
/**
 * post request result class
 * @author jzhao
 *
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
	 * @return true if request is successful
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @return resource id
	 */
	public String getClient_resource_id() {
		return client_resource_id;
	}
	/**
	 * @return hash number
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @return server state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return server result, tesSuccess means successful
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @return time, in UNIXTIME
	 */
	public long getDate() {
		return date;
	}
	/**
	 * @return transaction fee, in SWT
	 */
	public double getFee() {
		return fee;
	}
	/**
	 * @return sequence number
	 */
	public long getSequence() {
		return sequence;
	}
}
