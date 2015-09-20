/**
 * 
 */
package com.meituan.refund.service.iface;

import java.util.List;

import com.meituan.refund.entity.Refund;
import com.meituan.refund.entity.RefundExample;

/**
 * @author Administrator
 *
 */
public interface RefundService {
    Refund selectByPrimaryKey(Integer order_id);
    int updateByPrimaryKeySelective(Refund record);
    int insertSelective(Refund record);
    List<Refund> selectByExample(RefundExample example);
}
