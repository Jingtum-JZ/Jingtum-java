package com.jingtum;
/**
 * @author jzhao
 * @version 1.0
 */
public abstract class Jingtum
{
	private static final String LIVE_API_BASE = "https://tapi.jingtum.com:443";
	private static volatile String apiBase = LIVE_API_BASE;
    /**
     * Get API server address
     * @return apiBase
     */
	public static String getApiBase() {
		return apiBase;
	}
    /**
     * Set API server
     * @param apiBase
     */
    public static void setApiBase(String apiBase) {
        Jingtum.apiBase = apiBase;
    }
}
