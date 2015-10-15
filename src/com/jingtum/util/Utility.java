package com.jingtum.util;

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
	
	public Boolean isConnected()throws AuthenticationException, InvalidRequestException,
		APIConnectionException, APIException, ChannelException{
			return request(RequestMethod.GET, formatURL("server/connected"), null, Utility.class).getConnected();
	}
	
	public String getUuid()throws AuthenticationException, InvalidRequestException,
		APIConnectionException, APIException, ChannelException{
			return request(RequestMethod.GET, formatURL("uuid"), null, Utility.class).getMyuuid();
}
}
