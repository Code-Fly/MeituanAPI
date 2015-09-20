/**
 * 
 */
package com.meituan.refund.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.refund.entity.Refund;
import com.meituan.refund.entity.RefundExample;
import com.meituan.refund.mapper.RefundMapper;
import com.meituan.refund.service.iface.RefundService;

/**
 * @author Administrator
 *
 */
@Service("refundService")
public class RefundServiceImp implements RefundService {
	
	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	public Refund selectByPrimaryKey(Integer order_id) {
		return refundMapper.selectByPrimaryKey(order_id);
	}

	@Override
	public int updateByPrimaryKeySelective(Refund record) {
		return refundMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(Refund record) {
		return refundMapper.insertSelective(record);
	}

	@Override
	public List<Refund> selectByExample(RefundExample example) {
		return refundMapper.selectByExample(example);
	}
	
	

}
