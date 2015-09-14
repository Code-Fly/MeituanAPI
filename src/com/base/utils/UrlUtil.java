/**
 * 
 */
package com.base.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author Barrie
 *
 */
public class UrlUtil {

	public static String encode(String source, String chartset) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, chartset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String decode(String source, String chartset) {
		String result = source;
		try {
			result = java.net.URLDecoder.decode(source, chartset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
