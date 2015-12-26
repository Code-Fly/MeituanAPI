package com.meituan.app.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;
import com.meituan.app.mapper.AppMapper;
import com.meituan.app.service.iface.AppService;

@Service("appService")
public class AppServiceImp implements AppService {
	@Autowired
	private AppMapper appMapper;

	@Override
	public App selectByPrimaryKey(String appid) {
		return appMapper.selectByPrimaryKey(appid);
	}

	@Override
	public List<App> selectByExample(AppExample example) {
		return appMapper.selectByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(String appid) {
		return appMapper.deleteByPrimaryKey(appid);
	}
	
	
	@Override
	public int updateByPrimaryKeySelective(App record) {
		return appMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int insertSelective(App record) {
		return appMapper.insertSelective(record);
	}
}
