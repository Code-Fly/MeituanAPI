/**
 * order service
 */
package com.meituan.order.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.entity.MeituanOrderExample;
import com.meituan.order.mapper.MeituanOrderMapper;
import com.meituan.order.service.iface.OrderService;

/**
 * @author Administrator
 *
 */
@Service("orderService")
public class OrderServiceImp implements OrderService {
	
	@Autowired
	MeituanOrderMapper orderMapper;
	
	
	@Override
	public int insertSelective(MeituanOrder record) {
		return orderMapper.insertSelective(record);
	}

	@Override
	public List<MeituanOrder> selectByExample(MeituanOrderExample example) {
		return orderMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(MeituanOrder record,
			MeituanOrderExample example) {
		return orderMapper.updateByExampleSelective(record, example);
	}
	
}
