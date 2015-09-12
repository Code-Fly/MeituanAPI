package com.meituan.order.mapper;

import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.entity.MeituanOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeituanOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int countByExample(MeituanOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int deleteByExample(MeituanOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int deleteByPrimaryKey(Integer order_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int insert(MeituanOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int insertSelective(MeituanOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	List<MeituanOrder> selectByExample(MeituanOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	MeituanOrder selectByPrimaryKey(Integer order_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int updateByExampleSelective(@Param("record") MeituanOrder record, @Param("example") MeituanOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int updateByExample(@Param("record") MeituanOrder record, @Param("example") MeituanOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int updateByPrimaryKeySelective(MeituanOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table meituan_order
	 * @mbggenerated  Sat Sep 12 23:04:53 CST 2015
	 */
	int updateByPrimaryKey(MeituanOrder record);
}