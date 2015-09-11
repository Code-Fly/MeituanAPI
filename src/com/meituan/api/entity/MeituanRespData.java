/**
 * 
 */
package com.meituan.api.entity;

import com.meituan.common.MeituanConst;


/**
 * @author Barrie
 *
 */
public enum MeituanRespData {
	
	REP_OK(MeituanConst.RETURN_OK),
	REP_ERROR_600(MeituanConst.RETURN_NG,MeituanRespError.ERROR_600),
	REP_ERROR_702(MeituanConst.RETURN_NG,MeituanRespError.ERROR_702),
	REP_ERROR_703(MeituanConst.RETURN_NG,MeituanRespError.ERROR_703);
	
	private String data;
	private MeituanRespError error;

	
	/**
	 * @param data
	 */
	private MeituanRespData(String data) {
		this.data = data;
	}

	/**
	 * @param data
	 * @param error
	 */
	private MeituanRespData(String data, MeituanRespError error) {
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
