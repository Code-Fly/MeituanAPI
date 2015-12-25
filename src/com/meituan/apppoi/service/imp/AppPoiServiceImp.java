/**
 * 
 */
package com.meituan.apppoi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
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

	@Override
	public List<AppPoi> selectByExample(AppPoiExample example) {
		return apppoiMapper.selectByExample(example);
	}

	@Override
	public List<AppPoi> selectLimt(int beginNum, int endNum, AppPoiExample example) {
		return apppoiMapper.selectLimt(beginNum, endNum, example);
	}

	@Override
	public int deleteByPrimaryKey(AppPoiKey key) {
		return apppoiMapper.deleteByPrimaryKey(key);
	}
	
	@Override
	public int updateByPrimaryKeySelective(AppPoi record) {
		return apppoiMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int insertSelective(AppPoi record) {
		return apppoiMapper.insertSelective(record);
	}

}
