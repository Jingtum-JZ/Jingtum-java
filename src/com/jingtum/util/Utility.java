package com.jingtum.util;
/**
 * @author jzhao
 * @version 1.0
 */
import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.net.APIResource;
public class Utility extends APIResource{
	Boolean success;
	Boolean connected;
	String uuid;	
	private Boolean getConnected(){
		return this.connected;
	}
	private String getMyuuid(){
		return this.uuid;
	}	
	/**
	 * Return true if API server is connected
	 * @return 
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 * @throws ChannelException
	 */
	public Boolean isConnected()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
		return request(RequestMethod.GET, formatURL("server/connected"), null, Utility.class).getConnected();
	}
	/**
	 * Get system generated uuid
	 * @return generated uuid 
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 * @throws ChannelException
	 */
	public String getUuid()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
		return request(RequestMethod.GET, formatURL("uuid"), null, Utility.class).getMyuuid();
	}
}
