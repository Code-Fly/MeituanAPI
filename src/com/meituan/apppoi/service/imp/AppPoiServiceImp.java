/**
 * 
 */
package com.meituan.apppoi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
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
	
	/*@Override
	public AppPoi selectByPrimaryKey(String app_poi_code,String appid) {
		AppPoiKey key = new AppPoiKey();
		key.setApp_poi_code(app_poi_code);
		key.setAppid(appid);
		return apppoiMapper.selectByPrimaryKey(key);
	}*/
	
	
	@Override
	public List<AppPoi> selectByExample(String app_poi_code,String appid) {
		AppPoiExample example = new AppPoiExample();
		example.or().andApp_poi_codeEqualTo(app_poi_code).andAppidEqualTo(appid);
		return apppoiMapper.selectByExample(example);
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
	public int deleteByExample(String app_poi_code,String appid) {
		AppPoiExample example = new AppPoiExample();
		example.or().andApp_poi_codeEqualTo(app_poi_code).andAppidEqualTo(appid);
		return apppoiMapper.deleteByExample(example);
	}
	
	@Override
	public int updateByExampleSelective(AppPoi record, AppPoiExample example) {
		return apppoiMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int insertSelective(AppPoi record) {
		return apppoiMapper.insertSelective(record);
	}
	
	@Override
	public AppPoi selectByPrimaryKey(int poi_id) {
		return apppoiMapper.selectByPrimaryKey(poi_id);
	}
}
