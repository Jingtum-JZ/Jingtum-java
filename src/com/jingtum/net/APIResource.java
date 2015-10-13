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
import java.io.UnsupportedEncodingException;
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
        try {
            return String.format("%s/%s", classURL(clazz), urlEncode(parm));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to " + CHARSET, null, e);
        }
    }
    
    /**
     * @param clazz
     * @param parm
     * @return
     * @throws InvalidRequestException
     */
    protected static String formatURL(Class<?> clazz, String address, String parm) throws InvalidRequestException {
        try {
            return String.format("%s/%s/%s/%s", classURL(clazz),address, className(clazz),urlEncode(parm));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to " + CHARSET, null, e);
        }
    }

    /**
     * @param clazz
     * @param id
     * @return
     * @throws InvalidRequestException
     */
    protected static String instanceURL(Class<?> clazz, String id) throws InvalidRequestException {
        try {
            return String.format("%s/%s", classURL(clazz), urlEncode(id));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to " + CHARSET, null, e);
        }
    }
    
    /**
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String urlEncode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        } else {
            return URLEncoder.encode(str, CHARSET);
        }
    }
    
    /**
     * @param k
     * @param v
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String urlEncodePair(String k, String v)
            throws UnsupportedEncodingException {
        return String.format("%s=%s", urlEncode(k), urlEncode(v));
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
        conn.setRequestProperty("Content-Type", String.format(
                "application/x-www-form-urlencoded;charset=%s", CHARSET));

        OutputStream output = null;
        try {
            output = conn.getOutputStream();
            output.write(query.getBytes(CHARSET));
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return conn;
    }
    
    /**
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidRequestException
     */
    private static String createQuery(Map<String, Object> params)
            throws UnsupportedEncodingException, InvalidRequestException {
        Map<String, String> flatParams = flattenParams(params);
        StringBuilder queryStringBuffer = new StringBuilder();
        for (Map.Entry<String, String> entry : flatParams.entrySet()) {
            if (queryStringBuffer.length() > 0) {
                queryStringBuffer.append("&");
            }
            queryStringBuffer.append(urlEncodePair(entry.getKey(),
                    entry.getValue()));
        }
        return queryStringBuffer.toString();
    }
    
    /**
     * @param params
     * @return
     * @throws InvalidRequestException
     */
    private static Map<String, String> flattenParams(Map<String, Object> params)
            throws InvalidRequestException {
        if (params == null) {
            return new HashMap<String, String>();
        }
        Map<String, String> flatParams = new HashMap<String, String>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map<?, ?>) {
                Map<String, Object> flatNestedMap = new HashMap<String, Object>();
                Map<?, ?> nestedMap = (Map<?, ?>) value;
                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    flatNestedMap.put(
                            String.format("%s[%s]", key, nestedEntry.getKey()),
                            nestedEntry.getValue());
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if (value instanceof ArrayList<?>) {
                ArrayList<?> ar = (ArrayList<?>) value;
                Map<String, Object> flatNestedMap = new HashMap<String, Object>();
                int size = ar.size();
                for (int i = 0; i < size; i++) {
                    flatNestedMap.put(String.format("%s[%d]", key, i), ar.get(i));
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if ("".equals(value)) {
                throw new InvalidRequestException("You cannot set '" + key + "' to an empty string. " +
                        "We interpret empty strings as null in requests. " +
                        "You may set '" + key + "' to null to delete the property.",
                        key, null);
            } else if (value == null) {
                flatParams.put(key, "");
            } else {
                flatParams.put(key, value.toString());
            }
        }
        return flatParams;
    }
    
/*    // represents Errors returned as JSON
    private static class ErrorContainer {
        private APIResource.Error error;
    }*/
    
/*    *//**
    *
    *//*
   private static class Error {
       String type;

       String message;

       String code;

       String param;

       @Override
       public String toString() {
           StringBuffer sb = new StringBuffer();
           if (null != type && !type.isEmpty()) {
               sb.append("Error type: " + type + "\n");
           }
           if (null != message && !message.isEmpty()) {
               sb.append("\t Error message: " + message + "\n");
           }
           if (null != code && !code.isEmpty()) {
               sb.append("\t Error code: " + code + "\n");
           }

           return sb.toString();
       }
   }*/
   
   /**
    * @param responseStream
    * @return
    * @throws IOException
    */
   private static String getResponseBody(InputStream responseStream)
           throws IOException {
       //\A is the beginning of
       // the stream boundary
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
   protected static <T> T request(APIResource.RequestMethod method, String url, Map<String, Object> params, Class<T> clazz) throws AuthenticationException,
           InvalidRequestException, APIConnectionException,ChannelException,APIException {

       String query;

       try {
           query = createQuery(params);
       } catch (UnsupportedEncodingException e) {
           throw new InvalidRequestException("Unable to encode parameters to " + CHARSET, null, e);
       }

       JingtumResponse response;
       try {
           response = makeURLConnectionRequest(method, url, query);
       } catch (ClassCastException ce) {
           throw ce;
       }
       int rCode = response.getResponseCode();
       String rBody = response.getResponseBody();

       if (rCode < 200 || rCode >= 300) {
           handleAPIError(rBody, rCode);
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
   private static void handleAPIError(String rBody, int rCode)
           throws InvalidRequestException, AuthenticationException,
           APIException, ChannelException {
/*       APIResource.Error error = GSON.fromJson(rBody,
               APIResource.ErrorContainer.class);*/
       switch (rCode) {
           case 400:
               throw new InvalidRequestException(rBody, null, null);
           case 404:
               throw new InvalidRequestException(rBody, null, null);
           case 402:
               throw new ChannelException(rBody, null, null);
           case 401:
               throw new AuthenticationException(rBody);
           default:
               throw new APIException(rBody, null);
       }
   }
}
