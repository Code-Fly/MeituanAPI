package com.meituan.refund.mapper;

import com.meituan.refund.entity.Refund;
import com.meituan.refund.entity.RefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int countByExample(RefundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int deleteByExample(RefundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int deleteByPrimaryKey(Integer order_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int insert(Refund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int insertSelective(Refund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	List<Refund> selectByExample(RefundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	Refund selectByPrimaryKey(Integer order_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int updateByExampleSelective(@Param("record") Refund record, @Param("example") RefundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int updateByExample(@Param("record") Refund record, @Param("example") RefundExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int updateByPrimaryKeySelective(Refund record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table refund
	 * @mbggenerated  Wed Dec 02 20:17:12 CST 2015
	 */
	int updateByPrimaryKey(Refund record);
}