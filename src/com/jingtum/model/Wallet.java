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
import java.util.Map;

public class Wallet extends APIResource {
	Boolean success;
	Mywallet wallet;
	String address;
	String secret;
	long ledger;
	Boolean validated;
	BalanceCollection balances;
	
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

	public void setLedger(long ledger) {
		this.ledger = ledger;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public BalanceCollection getBalances() {
		return balances;
	}

	public void setBalances(BalanceCollection balances) {
		this.balances = balances;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getAddress() {
		return this.wallet.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecret() {
		return this.wallet.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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
}
