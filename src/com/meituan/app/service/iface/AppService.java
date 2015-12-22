package com.meituan.app.service.iface;

import java.util.List;

import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;

public interface AppService {
	App selectByPrimaryKey(String appid);
	
	List<App>  selectByExample(AppExample example);

	int deleteByPrimaryKey(String appid);
}
