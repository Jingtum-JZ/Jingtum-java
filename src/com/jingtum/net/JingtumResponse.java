package com.jingtum.net;

import java.util.List;
import java.util.Map;

/**
 * Handler Jingtum response 
 */
public class JingtumResponse {

    private int responseCode;
    private String responseBody;
    private Map<String, List<String>> responseHeaders;

    /**
     * @param responseCode
     * @param responseBody
     */
    public JingtumResponse(int responseCode, String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.responseHeaders = null;
    }

    /**
     * @param responseCode
     * @param responseBody
     * @param responseHeaders
     */
    public JingtumResponse(int responseCode, String responseBody, Map<String, List<String>> responseHeaders) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
    }

    /**
     * get http responseCode
     * @return
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     *
     * @param responseCode
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     *
     * @return
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     *
     * @param responseBody
     */
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    /**
     *
     * @return
     */
    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }
}
