/**
 * interface
 */
package com.meituan.order.service.iface;

import com.meituan.order.entity.MeituanOrder;

/**
 * @author Administrator
 * 美团order接口
 */
public interface OrderService {
	 int insertSelective(MeituanOrder record);
}
