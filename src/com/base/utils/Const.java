/**
 * 
 */
package com.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Barrie
 *
 */
public class Const {
	private static final String meituanConf = "meituan.properties";
	// 第三方用户唯一凭证
	public static final String MEITUAN_APP_ID = ConfigUtil.getProperty(meituanConf, "appId");
	// 第三方用户唯一凭证密钥
	public static final String MEITUAN_APP_SECRET = ConfigUtil.getProperty(meituanConf, "appSecret");

	public static String getServerPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		path = "/" + path.substring(1, path.indexOf("/classes"));
		return path;
	}

	public static String getServerUrl(HttpServletRequest request) {
		String path = request.getContextPath();
		int port = request.getServerPort();
		String basePath = null;
		if (80 == port) {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		}

		return basePath;
	}
}
