package com.meituan.app.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.app.entity.App;
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

}
