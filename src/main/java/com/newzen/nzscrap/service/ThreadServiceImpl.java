package com.newzen.nzscrap.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class ThreadServiceImpl implements ThreadService {

	@Override
	@Async("testExcutor")
	public Future<String> sendScrap(String inJsonList, String reqCd) {
		System.out.println("#########Thread : " + Thread.currentThread().getName() + " // " + reqCd);
		
		String url = "https://183.111.102.219:9402/rest/ext";
		String method = "POST";
		String contentType = "application/x-www-form-urlencoded";

		HashMap<String, Object> params = new HashMap<>();
		params.put("inJsonList", inJsonList);
		params.put("reqCd", reqCd);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new AsyncResult<String>(sendMessage(url, method, contentType, params));
	}

	private String sendMessage(String strUrl, String method, String contentType, HashMap<String, Object> params) {
		String result = "";

		URL url;
		HttpURLConnection conn = null;
		BufferedReader rd = null;

		try {
			disableSslVerification();

			url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(method);
			conn.setRequestProperty("Content-type", contentType);

			if (params != null) {
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, Object> param : params.entrySet()) {
					if (sb.length() != 0) {
						sb.append('&');
					}
					sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
					sb.append("=");
					sb.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
				}

				byte[] postDataBytes = sb.toString().getBytes("UTF-8");

				conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
				conn.setDoOutput(true);
				conn.getOutputStream().write(postDataBytes);
			}

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			rd.close();

			result = sb.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.disconnect();
		}

		return result;
	}
	
	// ################ SSL Security Exception 방지 코드
	private void disableSslVerification() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

		} };

		try {
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
