package com.jingtum.net;
/**
 * @author jzhao
 * @version 1.0
 */
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jingtum.Jingtum;
import com.jingtum.model.EffectCollection;
import com.jingtum.model.JingtumObject;
import com.jingtum.model.OrderBookResult;
import com.jingtum.model.PaymentCollection;
import com.jingtum.model.Wallet;
import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
/**
 * Extends the abstract class when you need request anything from jingtum
 */
public abstract class APIResource extends JingtumObject {
    /**
     * URLEncoder charset
     */
    public static final String CHARSET = "UTF-8";
    /**
     * Http request method:
     * Get, Post and Delete
     */
    protected enum RequestMethod {
        GET, POST, DELETE
    }    
    /**
     * Gson object use to transform json string to Jingtum object
     */
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Wallet.class, new WalletDeserializer())
            .registerTypeAdapter(OrderBookResult.class, new OrderBookResultDeserializer())
            .registerTypeAdapter(PaymentCollection.class, new PaymentCollectionDeserializer())
            .registerTypeAdapter(EffectCollection.class, new EffectCollectionDeserializer())
            .create();    
    /**
     * Based on class name to built the string needed in URL
     * @param clazz
     * @return 
     */
    private static String className(Class<?> clazz) {
        String className = clazz.getSimpleName().toLowerCase().replace("$", " ");
        if (className.equals("orderbook")){
        	className = "order_book";
        }
        else {
        	className = className + "s";
        }
        return className;
    }
    /**
     * Built base URL
     * @return
     */
    protected static String classURL() {
        return String.format("%s/v1/%s", Jingtum.getApiBase(), "accounts");
    }
    
    protected static String formatURL(String param){
    	return String.format("%s/v1/%s", Jingtum.getApiBase(), param);
    }    
    /**
     * @param clazz
     * @param param
     * @return
     * @throws InvalidRequestException
     */
    protected static String formatURL(Class<?> clazz, String param) throws InvalidRequestException {
       return String.format("%s/%s", classURL(), param);
    }    
    /**
     * @param clazz
     * @param parm
     * @return
     * @throws InvalidRequestException
     */
    protected static String formatURL(Class<?> clazz, String address, String parm) throws InvalidRequestException {
        return String.format("%s/%s/%s%s", classURL(), address, className(clazz),parm);
    }    
    /**
     * Get http connection instance
     * @param url
     * @return conn
     * @throws IOException
     */
    private static java.net.HttpURLConnection createJingtumConnection(String url) 
    		throws IOException {
        URL jingtumURL = null;
        jingtumURL = new URL(url);

        HttpsURLConnection conn = (HttpsURLConnection) jingtumURL.openConnection();

        conn.setConnectTimeout(30 * 1000);
        conn.setReadTimeout(80 * 1000);
        conn.setUseCaches(false);

        return conn;
    }    
    /**
     * @param url
     * @param query
     * @return
     */
    private static String formatURL(String url, String query) {
        if (query == null || query.isEmpty()) {
            return url;
        } else {
            String separator = url.contains("?") ? "&" : "?";
            return String.format("%s%s%s", url, separator, query);
        }
    }    
    /**
     * Get a http Get request connection
     * @param url
     * @param query
     * @return conn
     * @throws IOException
     * @throws APIConnectionException
     */
    private static java.net.HttpURLConnection createGetConnection(
            String url, String query) throws IOException, APIConnectionException {
        String getURL = formatURL(url, query);
        java.net.HttpURLConnection conn = createJingtumConnection(getURL);
        conn.setRequestMethod("GET");

        return conn;
    }    
    /**
     * Get a http post/delete connection
     * @param url
     * @param query
     * @return conn
     * @throws IOException
     * @throws APIConnectionException
     */
    private static java.net.HttpURLConnection createPostDeleteConnection(
            String url, String query, APIResource.RequestMethod method) throws IOException, APIConnectionException {
        java.net.HttpURLConnection conn = createJingtumConnection(url);

        conn.setDoOutput(true);
        conn.setRequestMethod(method.toString());
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", String.valueOf(query.getBytes().length));

        OutputStream output = null;
        
        try {
        	
        	output = conn.getOutputStream();
            output.write(query.getBytes());
            output.flush();
            
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return conn;
    }
    
    /**
    * Error class
    */
   private static class Error {
	   String error_type;
	   String error;
	   String message;	   
	@Override
       public String toString() {
           StringBuffer sb = new StringBuffer();
           if (null != error_type && !error_type.isEmpty()) {
               sb.append("Error type: " + error_type + "\n");
           }
           if (null != error && !error.isEmpty()) {
               sb.append("\t Error message: " + error + " " + message +"\n");
           }
           return sb.toString();
       }
   }   
   /**
    * Get the response body, in jason format
    * @param responseStream
    * @return rBody
    * @throws IOException
    */
   private static String getResponseBody(InputStream responseStream)
           throws IOException {
       String rBody = new Scanner(responseStream, CHARSET)
               .useDelimiter("\\A")
               .next(); 
       responseStream.close();
       return rBody;
   }   
   /**
    * @param method
    * @param url
    * @param query
    * @return JingtumResponse
    * @throws APIConnectionException
    */
   private static JingtumResponse makeURLConnectionRequest(
           APIResource.RequestMethod method, String url, String query) 
        		   throws APIConnectionException {
       java.net.HttpURLConnection conn = null;
       try {
           switch (method) {
               case GET:
                   conn = createGetConnection(url, query);
                   break;
               case POST:
                   conn = createPostDeleteConnection(url, query, method);
                   break;
               case DELETE:
            	   conn = createPostDeleteConnection(url,query, method);
            	   break;
               default:
                   throw new APIConnectionException(
                           String.format("Unrecognized HTTP method %s. ", method));
           }
           // trigger the request
           int rCode = conn.getResponseCode();
           String rBody = null;

           if (rCode >= 200 && rCode < 300) {
               rBody = getResponseBody(conn.getInputStream());
           } else {
               rBody = getResponseBody(conn.getErrorStream());
           }
           return new JingtumResponse(rCode, rBody);

       } catch (IOException e) {
           throw new APIConnectionException(
                   String.format(
                           "IOException during API request to Jingtum (%s): %s "
                                   + "Please check your internet connection and try again. If this problem persists,"
                                   + "you should check Jingtum's service status at https://api.jingtum.com,"
                                   + " or let us know at support@jingtum.com.",
                           Jingtum.getApiBase(), e.getMessage()), e);
       } finally {
           if (conn != null) {
               conn.disconnect();
           }
       }
   }   
   /**
    * Build class instance from jason format http response
    * @param method
    * @param url
    * @param params
    * @param clazz
    * @param <T>
    * @return class instance
    * @throws AuthenticationException
    * @throws InvalidRequestException
    * @throws APIConnectionException
    * @throws APIException
    */
   protected static <T> T request(APIResource.RequestMethod method, String url, String params, Class<T> clazz) throws AuthenticationException,
           InvalidRequestException, APIConnectionException,ChannelException,APIException {

       JingtumResponse response;
       try {
           response = makeURLConnectionRequest(method, url, params);
       } catch (ClassCastException ce) {
           throw ce;
       }
       int rCode = response.getResponseCode();
       String rBody = response.getResponseBody();

       if (rCode < 200 || rCode >= 300) {
           handleAPIError(rBody, rCode, params);
       }
       return GSON.fromJson(rBody, clazz);
   }   
   /**
    * Error handling
    * @param rBody
    * @param rCode
    * @throws InvalidRequestException
    * @throws AuthenticationException
    * @throws APIException
    */
   private static void handleAPIError(String rBody, int rCode, String query)
           throws InvalidRequestException, AuthenticationException,
           APIException, ChannelException {
	   APIResource.Error error;
	   
       switch (rCode) {
           case 400:
        	   error = GSON.fromJson(rBody,
                       APIResource.Error.class);
               throw new InvalidRequestException(error.toString(), query, null);
           case 404:
        	   error = GSON.fromJson(rBody,
                       APIResource.Error.class);
               throw new InvalidRequestException(error.toString(), query, null);
           case 402:
        	   error = GSON.fromJson(rBody,
                       APIResource.Error.class);
               throw new ChannelException(error.toString(), query, null);
           case 401:
        	   error = GSON.fromJson(rBody,
                       APIResource.Error.class);
               throw new AuthenticationException(error.toString());
           default:
               throw new APIException(rBody.toString(),null);
       }
   }
}
