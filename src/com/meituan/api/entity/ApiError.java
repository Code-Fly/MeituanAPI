/**
 * 
 */
package com.meituan.api.entity;

/**
 * @author Barrie
 *
 */
public class ApiError {

	private int code;
	private String msg;

	/**
	 * @param code
	 * @param msg
	 */
	public ApiError(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
