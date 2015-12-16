package com.meituan.chargerecord.service.iface;

import java.util.List;

import com.meituan.chargerecord.entity.ChargeRecord;
import com.meituan.chargerecord.entity.ChargeRecordExample;

public interface ChargeRecordService {
	int insertSelective(ChargeRecord record);
	List<ChargeRecord> selectByExample(ChargeRecordExample example);
}
