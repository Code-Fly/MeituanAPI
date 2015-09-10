/**
 * 
 */
package com.base.entity;

/**
 * @author Barrie
 *
 */
public class ApiResp {
	private int status;
	private String info;

	/**
	 * @param status
	 * @param info
	 */
	public ApiResp(int status, String info) {
		super();
		this.status = status;
		this.info = info;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}
