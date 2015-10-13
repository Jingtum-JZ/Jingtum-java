package com.jingtum.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.jingtum.Jingtum;
import com.jingtum.model.JingtumObject;
import com.jingtum.model.Wallet;
import com.jingtum.exception.APIConnectionException;
import com.jingtum.exception.InvalidRequestException;
import com.jingtum.exception.APIException;
import com.jingtum.exception.AuthenticationException;
import com.jingtum.exception.ChannelException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

/**
 * extends the abstract class when you need request anything from jingtum
 */
public abstract class APIResource extends JingtumObject {
    /**
     * URLEncoder charset
     */
    public static final String CHARSET = "UTF-8";


    /**
     * Http request method
     */
    protected enum RequestMethod {
        GET, POST
    }
    
    /**
     * Gson object use to transform json string to Jingtum object
     */
    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Wallet.class, new WalletDeserializer())
            .create();
    
    /**
     * @param clazz
     * @return
     */
    private static String className(Class<?> clazz) {
        String className = clazz.getSimpleName().toLowerCase().replace("$", " ");
        return className;
    }

    /**
     * @param clazz
     * @return
     */
    protected static String classURL(Class<?> clazz) {
    	String className = className(clazz);
        if (className.equals("wallet")) {
        	return String.format("%s/v1/%s", Jingtum.getApiBase(), className);
        } else {
        	return String.format("%s/v1/%s", Jingtum.getApiBase(), "accounts");
        } 
        
    }

    
    /**
     * @param clazz
     * @param parm
     * @return
     * @throws InvalidRequestException
     */
    protected static String formatURL(Class<?> clazz, String parm) throws InvalidRequestException {
       return String.format("%s/%s", classURL(clazz), parm);
    }
    
    /**
     * @param clazz
     * @param parm
     * @return
     * @throws InvalidRequestException
     */
    protected static String formatURL(Class<?> clazz, String address, String parm) throws InvalidRequestException {
        return String.format("%s/%s/%s%s", classURL(clazz),address, className(clazz),parm);
    }
   
    
    /**
     * @param url
     * @param 
     * @return
     * @throws IOException
     */
    private static java.net.HttpURLConnection createJingtumConnection(
            String url) throws IOException {
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
     * @param url
     * @param query
     * @return
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
     * @param url
     * @param query
     * @return
     * @throws IOException
     * @throws APIConnectionException
     */
    private static java.net.HttpURLConnection createPostConnection(
            String url, String query) throws IOException, APIConnectionException {
        java.net.HttpURLConnection conn = createJingtumConnection(url);

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

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
    *
    */
   private static class Error {
	   String success;
	   String error_type;
	   String error;

       public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError_type() {
		return error_type;
	}

	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
       public String toString() {
           StringBuffer sb = new StringBuffer();
           if (null != error_type && !error_type.isEmpty()) {
               sb.append("Error type: " + error_type + "\n");
           }
           if (null != error && !error.isEmpty()) {
               sb.append("\t Error message: " + error + "\n");
           }

           return sb.toString();
       }
   }
   
   /**
    * @param responseStream
    * @return
    * @throws IOException
    */
   private static String getResponseBody(InputStream responseStream)
           throws IOException {
       String rBody = new Scanner(responseStream, CHARSET)
               .useDelimiter("\\A")
               .next(); //
       responseStream.close();
       return rBody;
   }
   
   /**
    * @param method
    * @param url
    * @param query
    * @return
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
                   conn = createPostConnection(url, query);
                   break;
               default:
                   throw new APIConnectionException(
                           String.format("Unrecognized HTTP method %s. ", method));
           }
           // trigger the request
           int rCode = conn.getResponseCode();
           String rBody = null;
           Map<String, List<String>> headers;

           if (rCode >= 200 && rCode < 300) {
               rBody = getResponseBody(conn.getInputStream());
           } else {
               rBody = getResponseBody(conn.getErrorStream());
           }
           headers = conn.getHeaderFields();
           return new JingtumResponse(rCode, rBody, headers);

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
    * @param method
    * @param url
    * @param params
    * @param clazz
    * @param <T>
    * @return
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
    * 错误处理
    *
    * @param rBody
    * @param rCode
    * @throws InvalidRequestException
    * @throws AuthenticationException
    * @throws APIException
    */
   private static void handleAPIError(String rBody, int rCode, String query)
           throws InvalidRequestException, AuthenticationException,
           APIException, ChannelException {
       APIResource.Error error = GSON.fromJson(rBody,
               APIResource.Error.class);
       switch (rCode) {
           case 400:
               throw new InvalidRequestException(error.toString(), query, null);
           case 404:
               throw new InvalidRequestException(error.toString(), query, null);
           case 402:
               throw new ChannelException(error.toString(), query, null);
           case 401:
               throw new AuthenticationException(error.toString());
           default:
               throw new APIException(error.toString(), null);
       }
   }
}
