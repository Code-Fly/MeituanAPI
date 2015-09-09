package com.meituan.app.service.iface;

import com.meituan.app.entity.App;

public interface AppService {
	App selectByPrimaryKey(String appid);
}
