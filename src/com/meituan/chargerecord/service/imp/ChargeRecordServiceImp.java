package com.meituan.chargerecord.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.utils.CommonUtil;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.mapper.AppPoiMapper;
import com.meituan.chargerecord.entity.ChargeRecord;
import com.meituan.chargerecord.entity.ChargeRecordExample;
import com.meituan.chargerecord.mapper.ChargeRecordMapper;
import com.meituan.chargerecord.service.iface.ChargeRecordService;

@Service("chargeRecordService")
public class ChargeRecordServiceImp implements ChargeRecordService {
	
	@Autowired
	private ChargeRecordMapper chargeRecordMapper;
	
	@Autowired
	private AppPoiMapper appPoiMapper;
	
	
	@Override
	public int insertSelective(ChargeRecord record) {
		chargeRecordMapper.insertSelective(record);
		AppPoi appPoi = new AppPoi();
		appPoi.setApp_poi_code(record.getApp_poi_code());
		appPoi.setAppid(record.getApp_id());
		appPoi.setExpiredate(CommonUtil.addYear(appPoiMapper.selectByPrimaryKey(appPoi).getExpiredate(), record.getCzns()));
		return appPoiMapper.updateByPrimaryKeySelective(appPoi);
	}


	@Override
	public List<ChargeRecord> selectByExample(ChargeRecordExample example) {
		return chargeRecordMapper.selectByExample(example);
	}

}
