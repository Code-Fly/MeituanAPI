/**
 * interface
 */
package com.meituan.order.service.iface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.entity.MeituanOrderExample;

/**
 * @author Administrator
 * 美团order接口
 */
public interface OrderService {
	 MeituanOrder selectByPrimaryKey(Integer  order_id);
	 int insertSelective(MeituanOrder record);
	 List<MeituanOrder> selectByExample(MeituanOrderExample example);
	 int updateByExampleSelective(@Param("record") MeituanOrder record, @Param("example") MeituanOrderExample example);
	 int updateByPrimaryKeySelective(MeituanOrder order);
}
