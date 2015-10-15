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

public class Wallet extends APIResource {
	Boolean success;
	Mywallet wallet;
	long ledger;
	Boolean validated;
	BalanceCollection balances;
	PaymentCollection payments;
	OrderCollection orders;
	TrustLineCollection trustlines;
	Notification notification;
	TransactionCollection transactions;
	Transaction transaction;	
	
	private Transaction getTransaction() {
		return transaction;
	}

	private TransactionCollection getMyTransactionCollection(){
		return this.transactions;
	}
	
	private Notification getMyNotification(){
		return notification;
	}
	
	private TrustLineCollection getTrustLinesCollection() {
		return trustlines;
	}

	private OrderCollection getOrdersCollection() {
		return orders;
	}

	private PaymentCollection getPaymentsCollection() {
		return payments;
	}

	public Wallet (String address, String secret){
		wallet = new Mywallet();
		this.wallet.address = address;
		this.wallet.secret = secret;
	}
	
	private class Mywallet {
		String address;
		String secret;
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
        return request(RequestMethod.GET, formatURL("wallet/new"), null, Wallet.class);
    }
    
    public static BalanceCollection balance(String address)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {
       return request(RequestMethod.GET, formatURL(Balance.class,address,""), null, Wallet.class).getBalances();
    }
    
    
    //仅限于同一银关下的两个帐号间支付同种货币
    public PostResult pay(String receiver, JingtumCurrency pay, Boolean validate, String resourceID)
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
    	
    	return request(RequestMethod.POST, formatURL(Payment.class,this.getAddress(),"?validated=" + validate.toString()), params, PostResult.class);
    	
    }
    
    public Payment getPaymentByID(String id)throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Payment.class,this.getAddress(),"/" + id), null, Payment.class);
    }
    
    public PaymentCollection getPayments()throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	 return request(RequestMethod.GET, formatURL(Payment.class,this.getAddress(),""), null, Wallet.class).getPaymentsCollection();
    }
    
    public PostResult putOrder(Order.OrderType orderType, JingtumCurrency pay, JingtumCurrency get, Boolean validate)throws AuthenticationException, InvalidRequestException,
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
    	
    	return request(RequestMethod.POST, formatURL(Order.class,this.getAddress(),"?validated=" + validate.toString()), params, PostResult.class);
    }
    
    public PostResult cancelOrder(long sequence, Boolean validate)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.DELETE, formatURL(Order.class,this.getAddress(),"/" + Long.toString(sequence) + "?validated=" + validate.toString()), params, PostResult.class);
    }
    
    public OrderCollection getOrders()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{    	
    	return request(RequestMethod.GET, formatURL(Order.class,this.getAddress(),""), null, Wallet.class).getOrdersCollection();
    }
    
    public Order getOrderByHash(String hash)throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Order.class,this.getAddress(),"/" + hash), null, Order.class);
    }
    
    public OrderBookResult getOrderBook( JingtumCurrency base, JingtumCurrency counter)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String orderBook = "/" + base.getCurrency() + "+" + base.getCounterparty() + "/" + counter.getCurrency() + "+" + counter.getCounterparty();
    	return request(RequestMethod.GET, formatURL(OrderBook.class,this.getAddress(),orderBook), null, OrderBookResult.class);
    }
    
    public TrustLineCollection getTrustLine()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(TrustLine.class,this.getAddress(),""), null, Wallet.class).getTrustLinesCollection();
    }
    
    public PostResult addTrustLine(TrustLine trustLine, Boolean validate)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	HashMap<String, String> trustline = new HashMap<String, String>();
    	trustline.put("limit", trustLine.getLimit());
    	trustline.put("currency", trustLine.getCurrency());
    	trustline.put("counterparty", trustLine.getCounterparty());
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("trustline", trustline);
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.POST, formatURL(TrustLine.class,this.getAddress(),"?validated=" + validate.toString()), params, PostResult.class);
    }
    
    public Notification getNotification(String ID)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Notification.class,this.getAddress(),"/" + ID), null, Wallet.class).getMyNotification();
    }
    
    public TransactionCollection getTransactions(String destinationAccount, Boolean excludeFailed, Transaction.DirectionType direction)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String param = "";
    	if (destinationAccount != "" || direction != Transaction.DirectionType.all || excludeFailed){
    		param = "?";
    		if(excludeFailed){
    			param = param + "exclude_failed=" + excludeFailed;
    		}
    		
    		if(destinationAccount != ""){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "destination_account=" + destinationAccount;
    		}
    		
    		if(direction != Transaction.DirectionType.all){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "direction=" + direction;
    		}
    	}
    	return request(RequestMethod.GET, formatURL(Transaction.class,this.getAddress(),param), null, Wallet.class).getMyTransactionCollection();
    }
    
    public Transaction getTransactionByHash(String hash)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Transaction.class,this.getAddress(),"/" + hash), null, Wallet.class).getTransaction();
    }
}

