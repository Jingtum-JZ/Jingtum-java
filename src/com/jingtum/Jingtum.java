package com.jingtum;

/**
 * Jingtum Base class
 */
public abstract class Jingtum
{
    /**
     * jingtum api url
     */
	public static final String LIVE_API_BASE = "https://tapi.jingtum.com:443";

	private static volatile boolean verifySSL = true;
	private static volatile String apiBase = LIVE_API_BASE;

    /**
     * get SSL state
     * @return  true is set SSL ,false is not set SSL
     */
	public static boolean getVerifySSL() {
		return verifySSL;
	}

    /**
     * get api url
     * @return String  api url
     */
	public static String getApiBase() {
		return apiBase;
	}

    /**
     * set api url
     * @param apiBase
     */
    public static void setApiBase(String apiBase) {
        Jingtum.apiBase = apiBase;
    }
}
