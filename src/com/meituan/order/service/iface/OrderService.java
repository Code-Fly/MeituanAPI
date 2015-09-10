/**
 * interface
 */
package com.meituan.order.service.iface;

import java.util.List;

import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.entity.MeituanOrderExample;

/**
 * @author Administrator
 * 美团order接口
 */
public interface OrderService {
	 int insertSelective(MeituanOrder record);
	 List<MeituanOrder> selectByExample(MeituanOrderExample example);
}
