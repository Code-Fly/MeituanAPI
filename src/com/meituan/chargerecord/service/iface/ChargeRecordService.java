package com.meituan.chargerecord.service.iface;

import com.meituan.chargerecord.entity.ChargeRecord;

public interface ChargeRecordService {
	int insertSelective(ChargeRecord record);
}
