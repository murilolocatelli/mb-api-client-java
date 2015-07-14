/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;

/**
 * Class base for HTTPS API.
 */
public abstract class AbstractApiService {

	private static final String DOMAIN = "https://www.mercadobitcoin.net";

	protected enum HttpMethod {
		GET,
		POST
	}
	
	protected final boolean usingHttps() {
		return DOMAIN.toUpperCase().startsWith("HTTPS");
	}
	
	/**
	 * Starts a SSL connection for HTTPS Requests
	 * @throws MercadoBitcoinException Generic exception to point any error with the execution.
	 */
	public AbstractApiService() throws MercadoBitcoinException {
		try {
			if (usingHttps()) {
				setSslContext();
			}
		} catch (KeyManagementException e) {
            throw new MercadoBitcoinException("Internal error: Invalid SSL Connection.");
		} catch (NoSuchAlgorithmException e) {
            throw new MercadoBitcoinException("Internal error: Invalid SSL Algorithm.");
		}
	}
	
	protected String getDomain() {
		return DOMAIN + getApiPath();
	}
	
	public abstract String getApiPath();
	
	private final void setSslContext() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return new java.security.cert.X509Certificate[0];
	            }
	
	            public void checkClientTrusted(
	                    java.security.cert.X509Certificate[] certs,
	                    String authType) {
	            }
	
	            public void checkServerTrusted(
	                    java.security.cert.X509Certificate[] certs,
	                    String authType) {
	            }
			}
		};
		
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
	}
	
	protected static final String encodeHexString(byte[] bytes) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	protected static final long generateTonce() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
	}
    
}