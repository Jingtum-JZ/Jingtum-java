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
	PaymentsCollection payments;
	OrdersCollection orders;
	OrderBookResult orderBookResult;
		
	private OrderBookResult getOrderBookResult() {
		return orderBookResult;
	}

/*	public void setOrderBookResult(OrderBookResult orderBookResult) {
		this.orderBookResult = orderBookResult;
	}*/

/*	public void setSuccess(Boolean success) {
		this.success = success;
	}*/

/*	public void setValidated(Boolean validated) {
		this.validated = validated;
	}*/

	private OrdersCollection getOrdersCollection() {
		return orders;
	}

/*	private void setOrdersCollection(OrdersCollection orders) {
		this.orders = orders;
	}*/

	private PaymentsCollection getPaymentsCollection() {
		return payments;
	}

/*	private void setPaymentsCollection(PaymentsCollection paymentsCollection) {
		this.payments = paymentsCollection;
	}*/

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

	private BalanceCollection getBalances() {
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
    public Payments pay(String receiver, JingtumCurrency pay, Boolean validate, String resourceID)
    		throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, String> destination_amount = new HashMap<String, String>();  
    	destination_amount.put("currency", pay.getCurrency());
    	destination_amount.put("value", Double.toString(pay.getValue()));    	
    	destination_amount.put("issuer",pay.getCounterparty()); 
    	
    	HashMap<String, Object> payment = new HashMap<String, Object>();  
    	payment.put("source_account", this.getAddress());
    	payment.put("destination_account", receiver);
    	payment.put("destination_amount", destination_amount);
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("client_resource_id", resourceID);
    	content.put("payment", payment);
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.POST, formatURL(Payments.class,this.getAddress(),"?validated=" + validate.toString()), params, Payments.class);
    	
    }
    
    public Payments getPaymentByID(String id)throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Payments.class,this.getAddress(),"/" + id), null, Payments.class);
    }
    
    public PaymentsCollection getPayments()throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	 Wallet wallet = request(RequestMethod.GET, formatURL(Payments.class,this.getAddress(),""), null, Wallet.class);
    	 return wallet.getPaymentsCollection();
    }
    
    public Orders putOrder(Orders.OrderType orderType, JingtumCurrency pay, JingtumCurrency get, Boolean validate)throws AuthenticationException, InvalidRequestException,
	APIConnectionException, APIException, ChannelException{

    	HashMap<String, String> taker_pays = new HashMap<String, String>(); 
    	taker_pays.put("currency", get.getCurrency());
    	taker_pays.put("counterparty", get.getCounterparty());
    	taker_pays.put("value", Double.toString(get.getValue()));
    	
    	HashMap<String, String> taker_gets = new HashMap<String, String>();
    	taker_gets.put("currency", pay.getCurrency());
    	taker_gets.put("counterparty", pay.getCounterparty());
    	taker_gets.put("value", Double.toString(pay.getValue()));
    	
    	HashMap<String, Object> order = new HashMap<String, Object>();
    	order.put("type", orderType);
    	order.put("taker_pays", taker_pays);
    	order.put("taker_gets", taker_gets);
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("order", order);
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.POST, formatURL(Orders.class,this.getAddress(),"?validated=" + validate.toString()), params, Orders.class);
    }
    
    public Orders cancelOrder(long sequence, Boolean validate)throws AuthenticationException, InvalidRequestException,
	APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.DELETE, formatURL(Orders.class,this.getAddress(),"/" + Long.toString(sequence) + "?validated=" + validate.toString()), params, Orders.class);
    }
    
    public OrdersCollection getOrders()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	Wallet wallet = request(RequestMethod.GET, formatURL(Orders.class,this.getAddress(),""), null, Wallet.class);
    	return wallet.getOrdersCollection();
    }
    
    public Orders getOrderByHash(String hash)throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Orders.class,this.getAddress(),"/" + hash), null, Orders.class);
    }
    
    public OrderBookResult getOrderBook( JingtumCurrency base, JingtumCurrency counter)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String orderBook = "/" + base.getCurrency() + "+" + base.getCounterparty() + "/" + counter.getCurrency() + "+" + counter.getCounterparty();
    	return request(RequestMethod.GET, formatURL(OrderBook.class,this.getAddress(),orderBook), null, OrderBookResult.class);
    }
}

