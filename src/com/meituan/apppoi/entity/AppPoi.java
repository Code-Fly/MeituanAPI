package com.meituan.apppoi.entity;

import java.util.Date;

public class AppPoi extends AppPoiKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.userid
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private Integer userid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.wm_poi_name
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private String wm_poi_name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.wm_poi_address
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private String wm_poi_address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.wm_poi_phone
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private String wm_poi_phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.amount
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private Float amount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.price
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private Float price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.descption
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private String descption;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column app_poi.expiredate
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	private Date expiredate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.userid
	 * @return  the value of app_poi.userid
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.userid
	 * @param userid  the value for app_poi.userid
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.wm_poi_name
	 * @return  the value of app_poi.wm_poi_name
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public String getWm_poi_name() {
		return wm_poi_name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.wm_poi_name
	 * @param wm_poi_name  the value for app_poi.wm_poi_name
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setWm_poi_name(String wm_poi_name) {
		this.wm_poi_name = wm_poi_name == null ? null : wm_poi_name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.wm_poi_address
	 * @return  the value of app_poi.wm_poi_address
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public String getWm_poi_address() {
		return wm_poi_address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.wm_poi_address
	 * @param wm_poi_address  the value for app_poi.wm_poi_address
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setWm_poi_address(String wm_poi_address) {
		this.wm_poi_address = wm_poi_address == null ? null : wm_poi_address.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.wm_poi_phone
	 * @return  the value of app_poi.wm_poi_phone
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public String getWm_poi_phone() {
		return wm_poi_phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.wm_poi_phone
	 * @param wm_poi_phone  the value for app_poi.wm_poi_phone
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setWm_poi_phone(String wm_poi_phone) {
		this.wm_poi_phone = wm_poi_phone == null ? null : wm_poi_phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.amount
	 * @return  the value of app_poi.amount
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.amount
	 * @param amount  the value for app_poi.amount
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.price
	 * @return  the value of app_poi.price
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.price
	 * @param price  the value for app_poi.price
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.descption
	 * @return  the value of app_poi.descption
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public String getDescption() {
		return descption;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.descption
	 * @param descption  the value for app_poi.descption
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setDescption(String descption) {
		this.descption = descption == null ? null : descption.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column app_poi.expiredate
	 * @return  the value of app_poi.expiredate
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public Date getExpiredate() {
		return expiredate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column app_poi.expiredate
	 * @param expiredate  the value for app_poi.expiredate
	 * @mbggenerated  Wed Dec 23 22:56:44 CST 2015
	 */
	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
}