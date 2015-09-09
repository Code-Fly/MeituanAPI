package com.meituan.app.mapper;

import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int countByExample(AppExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int deleteByExample(AppExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int deleteByPrimaryKey(String appid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int insert(App record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int insertSelective(App record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	List<App> selectByExample(AppExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	App selectByPrimaryKey(String appid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int updateByExample(@Param("record") App record, @Param("example") AppExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int updateByPrimaryKeySelective(App record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table app
	 * @mbggenerated  Wed Sep 09 22:01:04 CST 2015
	 */
	int updateByPrimaryKey(App record);
}