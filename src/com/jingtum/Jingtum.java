package com.jingtum;
/**
 * @author jzhao
 * @version 1.0
 * @date 2015.10
 */
public abstract class Jingtum
{
	private static final String LIVE_API_BASE = "https://tapi.jingtum.com:443";
	private static volatile String apiBase = LIVE_API_BASE;
    /**
     * @return API server address
     */
	public static String getApiBase() {
		return apiBase;
	}
    /**
     * set API server
     * @param apiBase
     */
    public static void setApiBase(String apiBase) {
        Jingtum.apiBase = apiBase;
    }
}
