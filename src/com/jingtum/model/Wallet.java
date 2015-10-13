package com.jingtum.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.LongSerializationPolicy;

import com.jingtum.net.APIResource;
import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Wallet extends APIResource {
	Boolean success;
	Mywallet wallet;
	long ledger;
	Boolean validated;
	BalanceCollection balances;
	
	public Wallet (String address, String secret){
		wallet = new Mywallet();
		this.wallet.address = address;
		this.wallet.secret = secret;
	}
	
	private class Mywallet {
		String address;
		String secret;
		
		private String getAddress() {
			return address;
		}

		private void setAddress(String address) {
			this.address = address;
		}

		private String getSecret() {
			return secret;
		}

		private void setSecret(String secret) {
			this.secret = secret;
		}
	}
	
    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().
            setPrettyPrinting().
            serializeNulls().
            disableHtmlEscaping().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
            setLongSerializationPolicy(LongSerializationPolicy.STRING).
            registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            }).
            create();
    
	public long getLedger() {
		return ledger;
	}

	public Boolean getValidated() {
		return validated;
	}

	public BalanceCollection getBalances() {
		return balances;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getAddress() {
		return this.wallet.address;
	}


	public String getSecret() {
		return this.wallet.secret;
	}
	
    /**
     * 创建 Wallet
     *
     * @return
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public static Wallet create()
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {
        return request(RequestMethod.GET, formatURL(Wallet.class,"new"), null, Wallet.class);
    }
    
    public static BalanceCollection balance(String address)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {

        Wallet wallet = request(RequestMethod.GET, formatURL(Balances.class,address,""), null, Wallet.class);
        
        return wallet.getBalances();
    }
    
    
    //仅限于同一银关下的两个帐号间支付同种货币
    public Payments pay(String receiver, double amount, String currency, String issuer, Boolean validate, String resourceID)
    		throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, String> destination_amount = new HashMap<String, String>();  
    	destination_amount.put("currency", currency);
    	destination_amount.put("value", Double.toString(amount));    	
    	destination_amount.put("issuer",issuer); 
    	
    	HashMap<String, Object> payment = new HashMap<String, Object>();  
    	payment.put("source_account", this.getAddress());
    	payment.put("destination_account", receiver);
    	payment.put("destination_amount", destination_amount);
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("client_resource_id", resourceID);
    	content.put("payment", payment);
    	
    	String result = GSON.toJson(content); 
    	
    	return request(RequestMethod.POST, formatURL(Payments.class,this.getAddress(),"?validated=" + validate.toString()), result, Payments.class);
    	
    }
}
