/**
 * 
 */
package com.meituan.api.entity;

/**
 * @author Barrie
 *
 */
public class ApiData {

	private Object data;
	private ApiError error;

	/**
	 * @param data
	 */
	public ApiData(Object data) {
		super();
		this.data = data;
	}

	/**
	 * @param data
	 * @param error
	 */
	public ApiData(Object data, ApiError error) {
		super();
		this.data = data;
		this.error = error;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the error
	 */
	public ApiError getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(ApiError error) {
		this.error = error;
	}

}
