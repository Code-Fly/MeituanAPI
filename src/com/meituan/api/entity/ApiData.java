/**
 * 
 */
package com.meituan.api.entity;


/**
 * @author Barrie
 *
 */
public class ApiData {
	private String data;
	private ApiError error;

	/**
	 * @param data
	 */
	public ApiData(String data) {
		super();
		this.data = data;
	}

	/**
	 * @param data
	 * @param error
	 */
	public ApiData(String data, ApiError error) {
		super();
		this.data = data;
		this.error = error;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
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
