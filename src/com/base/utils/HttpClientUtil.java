/**
 * 
 */
package com.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Barrie
 *
 */
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doPost(String url, Map<String, String> params, String charset) {
		try {
			HttpPost httpPost = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			if (null != params) {
				List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
					valuePairs.add(nameValuePair);
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, charset);
				httpPost.setEntity(formEntity);
			}
			HttpResponse resp = client.execute(httpPost);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			httpPost.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String doPostJson(String url, String jsonStr, String charset) {
		try {
			HttpPost httpPost = new HttpPost(url);
			HttpClient client = new DefaultHttpClient();
			StringEntity reqEntity = new StringEntity(jsonStr);
			reqEntity.setContentType("application/json; charset=utf-8");
			httpPost.setEntity(reqEntity);
			HttpResponse resp = client.execute(httpPost);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			httpPost.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String doGet(String url, Map<String, String> params, String charset) {
		try {
			HttpGet httpGet = null;
			HttpClient client = new DefaultHttpClient();
			if (null != params) {
				List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
					valuePairs.add(nameValuePair);
				}
				String param = URLEncodedUtils.format(valuePairs, charset);
				httpGet = new HttpGet(url + "?" + param);

			} else {
				httpGet = new HttpGet(url);
			}

			HttpResponse resp = client.execute(httpGet);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			httpGet.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String doGet(String url, String params, String charset) {
		try {
			HttpGet httpGet = null;
			HttpClient client = new DefaultHttpClient();
			if (null != params) {
				httpGet = new HttpGet(url + "?" + params);

			} else {
				httpGet = new HttpGet(url);
			}

			HttpResponse resp = client.execute(httpGet);

			HttpEntity entity = resp.getEntity();
			String respContent = EntityUtils.toString(entity, charset).trim();
			httpGet.abort();
			client.getConnectionManager().shutdown();

			return respContent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {

	}
}
