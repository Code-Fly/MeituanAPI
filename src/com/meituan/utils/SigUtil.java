/**
 * 
 */
package com.meituan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author zhangqw
 * @version 2015年9月9日
 *
 * @description 系统工具类
 */

public class SigUtil {

	/**
	 * 获取签名
	 * 
	 * @param serverUrl
	 *            请求url（必填）
	 * @param params
	 *            参数集合
	 * @param app_id
	 *            应用ID（必填）
	 * @param appSecret
	 *            应用密钥（必填）
	 * @param timestamp
	 *            时间戳（必填）
	 * @return
	 */
	public static String signRequest(String serverUrl, Map<String, String> params, String appSecret) {
		List<NameValuePair> nameValueParams = new ArrayList<NameValuePair>();
		// nameValueParams.add(new BasicNameValuePair("app_id", app_id));
		if (params != null && !params.isEmpty()) {
			for (String paramName : params.keySet()) {
				nameValueParams.add(new BasicNameValuePair(paramName, params.get(paramName)));
			}
		}
		String paramForSig = getSortedParam(nameValueParams);
		return signRequest(serverUrl, paramForSig, appSecret);
	}

	/**
	 * 获取签名
	 * 
	 * @param serverUrl
	 *            请求url（必填）
	 * @param paramContent
	 *            已排序的参数内容（必填）
	 * @param appSecret
	 *            应用密钥（必填）
	 * @return
	 */
	public static String signRequest(String serverUrl, String paramContent, String appSecret) {
		return md5sum(serverUrl + "?" + paramContent + appSecret);
	}

	/**
	 * 针对参数排序
	 * 
	 * @param params
	 *            参数集合
	 * @param timestamp
	 *            时间戳
	 * @return
	 */
	private static String getSortedParam(List<NameValuePair> params) {
		List<String> keyList = new ArrayList<String>();
		// params.add(new BasicNameValuePair("timestamp", timestamp + ""));
		Collections.sort(params, new Comparator<NameValuePair>() {
			public int compare(NameValuePair nameValuePair1, NameValuePair nameValuePair2) {
				return nameValuePair1.getName().compareTo(nameValuePair2.getName());
			}
		});
		for (int i = 0; i < params.size(); i++) {
			String value = params.get(i).getValue() == null ? "" : params.get(i).getValue();
			String keyValue = params.get(i).getName() + "=" + value;
			keyList.add(keyValue);
		}
		return StringUtils.join(keyList, "&");
	}

	/**
	 * 使用MD5加密
	 * 
	 * @param src
	 * @return
	 */
	private static String md5sum(String src) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byte2hex(md.digest(src.getBytes()));
	}

	/**
	 * 二进制转化为大写的十六进制
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer buf = new StringBuffer();
		int i;

		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

}
