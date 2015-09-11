/**
 * 
 */
package com.meituan.api.entity;


/**
 * @author Barrie
 *
 */
public enum ApiError {
	
	
	ERROR_600(600,"内部错误"),
	ERROR_702(702,"app_id不存在"),
	ERROR_703(703,"签名验证错误 ");
	
	
	private int code;
	private String msg;

	/**
	 * @param code
	 * @param msg
	 */
	private ApiError(int code, String msg) {
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
