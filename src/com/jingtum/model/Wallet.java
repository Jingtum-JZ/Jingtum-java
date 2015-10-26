package com.jingtum.model;
/**
 * @author jzhao
 * @version 1.0
 * Wallet class, main entry point
 */
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
	RelationCollection relations;
	/**
	 * Get private RelationCollection instance
	 * @return relations
	 */
	private RelationCollection getMyRelations(){
		return relations;
	}
	/**
	 * get private Transaction instance
	 * @return transaction
	 */
	private Transaction getTransaction() {
		return transaction;
	}
	/**
	 * get private TransactionCollection instance
	 * @return transactions
	 */
	private TransactionCollection getMyTransactionCollection(){
		return this.transactions;
	}	
	/**
	 * get private notification instance
	 * @return notification
	 */
	private Notification getMyNotification(){
		return notification;
	}	
	/**
	 * get private TrustLineCollection instance
	 * @return trustlines
	 */
	private TrustLineCollection getTrustLinesCollection() {
		return trustlines;
	}
	/**
	 * get private Order Collection instance
	 * @return orders
	 */
	private OrderCollection getOrdersCollection() {
		return orders;
	}
	/**
	 * get private PaymentCollection instance
	 * @return payments
	 */
	private PaymentCollection getPaymentsCollection() {
		return payments;
	}
	/**
	 * Create Wallet instance with address and secret key
	 * @param address
	 * @param secret
	 */
	public Wallet (String address, String secret){
		wallet = new Mywallet();
		this.wallet.address = address;
		this.wallet.secret = secret;
	}	
	/**
	 * private wallet class
	 */
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
	/**
	 * Get private balance collection
	 * @return balances
	 */
	private BalanceCollection getBalances() {
		return balances;
	}
	/**
	 * Get wallet address
	 * @return address
	 */
	public String getAddress() {
		return this.wallet.address;
	}
	/**
	 * Get wallet secret key
	 * @return secret
	 */
	public String getSecret() {
		return this.wallet.secret;
	}	
    /**
     * Create a new wallet
     * @return Wallet instance
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
    /**
     * Static method to get the balance collection of a given address
     * @param address
     * @return BalanceCollection instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public static BalanceCollection getBalance(String address)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {
       return request(RequestMethod.GET, formatURL(Balance.class,address,""), null, Wallet.class).getBalances();
    }
    /**
     * Post payment
     * @param receiver payment receiver
     * @param pay payment amount
     * @param validate true if wait for payment result
     * @param resourceID payment resource ID
     * @return PostResult instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
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
    /**
     * Take hash number or resource ID to get payment information 
     * @param id 
     * @return Payment instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public Payment getPaymentByID(String id)throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Payment.class,this.getAddress(),"/" + id), null, Payment.class);
    }    
    /**
     * @return PaymentCollection instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public PaymentCollection getPayments()throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	 return request(RequestMethod.GET, formatURL(Payment.class,this.getAddress(),""), null, Wallet.class).getPaymentsCollection();
    }    
    /**
     * Post a new order request
     * @param orderType buy or sell
     * @param pay 
     * @param get
     * @param validate
     * @return PostRestul instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
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
    
    /**
     * Cancel a posted order given a order sequence number
     * @param sequence
     * @param validate
     * @return PostResult instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public PostResult cancelOrder(long sequence, Boolean validate)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.DELETE, formatURL(Order.class,this.getAddress(),"/" + Long.toString(sequence) + "?validated=" + validate.toString()), params, PostResult.class);
    }    
    /**
     * Get all orders of a wallet
     * @return OrderCollection instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public OrderCollection getOrders()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{    	
    	return request(RequestMethod.GET, formatURL(Order.class,this.getAddress(),""), null, Wallet.class).getOrdersCollection();
    }    
    /**
     * Get order by hash number
     * @param hash
     * @return Order instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public Order getOrderByHash(String hash)throws AuthenticationException, InvalidRequestException,
    		APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Order.class,this.getAddress(),"/" + hash), null, Order.class);
    }    
    /**
     * Get order book
     * @param base
     * @param counter
     * @return
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public OrderBookResult getOrderBook( JingtumCurrency base, JingtumCurrency counter)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String orderBook = "/" + base.getCurrency() + "+" + base.getCounterparty() + "/" + counter.getCurrency() + "+" + counter.getCounterparty();
    	return request(RequestMethod.GET, formatURL(OrderBook.class,this.getAddress(),orderBook), null, OrderBookResult.class);
    }    
    /**
     * Get all trust lines
     * @return TrustLineCollection instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public TrustLineCollection getTrustLine()throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(TrustLine.class,this.getAddress(),""), null, Wallet.class).getTrustLinesCollection();
    }    
    /**
     * Add a new trust line
     * @param trustLine
     * @param validate
     * @return PostResult instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
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
    /**
     * Get notification
     * @param ID
     * @return Notification instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public Notification getNotification(String ID)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Notification.class,this.getAddress(),"/" + ID), null, Wallet.class).getMyNotification();
    }    
    /**
     * Get transaction info
     * @param destinationAccount
     * @param excludeFailed
     * @param direction
     * @return TransactionCollection info
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public TransactionCollection getTransactions(String destinationAccount, Boolean excludeFailed, Transaction.DirectionType direction)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String param = "";
    	if ((destinationAccount != null && !destinationAccount.equals(""))|| direction != Transaction.DirectionType.all || excludeFailed){
    		param = "?";
    		if(excludeFailed){
    			param = param + "exclude_failed=" + excludeFailed;
    		}    		
    		if(destinationAccount != null && !destinationAccount.equals("")){
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
    /**
     * Get transaction by hash number
     * @param hash
     * @return Transaction instance
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public Transaction getTransactionByHash(String hash)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	return request(RequestMethod.GET, formatURL(Transaction.class,this.getAddress(),"/" + hash), null, Wallet.class).getTransaction();
    }
    /**
     * Add relation
     * @param type
     * @param counterParty
     * @param amount optional, only apply when type is authorize
     * @param validate
     * @return PostResult
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public PostResult addRelation(Relation.RelationType type, String counterParty, JingtumCurrency amount, Boolean validate)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("type", type);
    	content.put("counterparty", counterParty);
    	if(amount !=null){
    		content.put("amount", amount);
    	}
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.POST, formatURL(Relation.class,this.getAddress(),"?validated=" + validate.toString()), params, PostResult.class);
    } 
    /**
     * Delete relation
     * @param type
     * @param counterParty
     * @param currency
     * @param validate
     * @return PostResult
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public PostResult deleteRelation(Relation.RelationType type, String counterParty, JingtumCurrency currency, Boolean validate)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	
    	HashMap<String, Object> content = new HashMap<String, Object>();
    	content.put("secret", this.getSecret());
    	content.put("type", type);
    	content.put("counterparty", counterParty);
    	if(currency != null){
    		String myCurrency = currency.getCurrency()+"+"+currency.getIssuer();
    		content.put("currency", myCurrency);
    	}
    	
    	String params = GSON.toJson(content); 
    	
    	return request(RequestMethod.DELETE, formatURL(Relation.class,this.getAddress(),"?validated=" + validate.toString()), params, PostResult.class);
    } 
    /**
     * Get relations
     * @param type optional
     * @param counterParty optional
     * @param currency optional
     * @return RelationCollection
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     * @throws ChannelException
     */
    public RelationCollection getRelations(Relation.RelationType type, String counterParty, JingtumCurrency currency)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String param = "";
    	if ((counterParty != null && !counterParty.equals(""))|| type != Relation.RelationType.all || currency != null){
    		param = "?";		
    		if(counterParty != null && !counterParty.equals("")){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "counterparty=" + counterParty;
    		}    		
    		if(type != Relation.RelationType.all){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "type=" + type;
    		}
    		if(currency != null){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "currency=" + currency.getCurrency() + currency.getIssuer();
    		}
    	}
    	return request(RequestMethod.GET, formatURL(Relation.class,this.getAddress(),param), null, Wallet.class).getMyRelations();
    } 
    public RelationCollection getCounterpartyRelations(Relation.RelationType type, String address, JingtumCurrency currency)throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, ChannelException{
    	String param = "";
    	if ((address != null && !address.equals(""))|| type != Relation.RelationType.all || currency != null){
    		param = "?";		
    		if(address != null && !address.equals("")){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "counterparty=" + address;
    		}    		
    		if(type != Relation.RelationType.all){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "type=" + type;
    		}
    		if(currency != null){
    			if (!param.equals("?")){
    				param = param + "&";
    			}
    			param = param + "currency=" + currency.getCurrency() + currency.getIssuer();
    		}
    	}
    	return request(RequestMethod.GET, formatURL("counterparties/"+this.getAddress()+"/relations"+param), null, Wallet.class).getMyRelations();
    } 
    
}