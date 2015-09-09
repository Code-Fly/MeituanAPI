/**
 * 
 */
package com.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Barrie
 *
 */
public class PathUtil {
	
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
