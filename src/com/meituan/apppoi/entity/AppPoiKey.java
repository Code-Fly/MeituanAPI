package com.meituan.apppoi.entity;

public class AppPoiKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.app_poi_code
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	private String app_poi_code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.appid
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	private String appid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.app_poi_code
	 * @return  the value of app_poi.app_poi_code
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	public String getApp_poi_code() {
		return app_poi_code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.app_poi_code
	 * @param app_poi_code  the value for app_poi.app_poi_code
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	public void setApp_poi_code(String app_poi_code) {
		this.app_poi_code = app_poi_code == null ? null : app_poi_code.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.appid
	 * @return  the value of app_poi.appid
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.appid
	 * @param appid  the value for app_poi.appid
	 * @mbggenerated  Sun Dec 20 13:53:02 CST 2015
	 */
	public void setAppid(String appid) {
		this.appid = appid == null ? null : appid.trim();
	}
}