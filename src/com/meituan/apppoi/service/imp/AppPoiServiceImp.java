/**
 * 
 */
package com.meituan.apppoi.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiKey;
import com.meituan.apppoi.mapper.AppPoiMapper;
import com.meituan.apppoi.service.iface.AppPoiService;

/**
 * @author Administrator
 *
 */
@Service("appPoiService")
public class AppPoiServiceImp implements AppPoiService {
	
	@Autowired
	private AppPoiMapper apppoiMapper;
	
	@Override
	public AppPoi selectByPrimaryKey(String app_poi_code,String appid) {
		AppPoiKey key = new AppPoiKey();
		key.setApp_poi_code(app_poi_code);
		key.setAppid(appid);
		return apppoiMapper.selectByPrimaryKey(key);
	}

}
