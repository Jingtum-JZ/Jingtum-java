package com.jingtum.net;
/**
 * @author jzhao
 * @version 1.0
 */
public class JingtumResponse {
    private int responseCode;
    private String responseBody;
    /**
     * @param responseCode
     * @param responseBody
     */
    public JingtumResponse(int responseCode, String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }
    /**
     * @return http responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }
    /**
     * @return http responseBody
     */
    public String getResponseBody() {
        return responseBody;
    }
}
