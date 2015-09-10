/**
 * 
 */
package com.meituan.api.entity;


/**
 * @author Barrie
 *
 */
public class MeituanRespData {
	private String data;
	private MeituanRespError error;

	/**
	 * @param data
	 */
	public MeituanRespData(String data) {
		super();
		this.data = data;
	}

	/**
	 * @param data
	 * @param error
	 */
	public MeituanRespData(String data, MeituanRespError error) {
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
	public MeituanRespError getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(MeituanRespError error) {
		this.error = error;
	}

}
