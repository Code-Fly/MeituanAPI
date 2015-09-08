package com.meituan.extras.mapper;

import com.meituan.extras.entity.MeituanOrderExtras;
import com.meituan.extras.entity.MeituanOrderExtrasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeituanOrderExtrasMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int countByExample(MeituanOrderExtrasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int deleteByExample(MeituanOrderExtrasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int deleteByPrimaryKey(Integer actDetailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int insert(MeituanOrderExtras record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int insertSelective(MeituanOrderExtras record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    List<MeituanOrderExtras> selectByExample(MeituanOrderExtrasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    MeituanOrderExtras selectByPrimaryKey(Integer actDetailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int updateByExampleSelective(@Param("record") MeituanOrderExtras record, @Param("example") MeituanOrderExtrasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int updateByExample(@Param("record") MeituanOrderExtras record, @Param("example") MeituanOrderExtrasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int updateByPrimaryKeySelective(MeituanOrderExtras record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meituan_order_extras
     *
     * @mbggenerated Tue Sep 08 17:25:28 CST 2015
     */
    int updateByPrimaryKey(MeituanOrderExtras record);
}