/**
 * 
 */
package com.meituan.apppoi.service.iface;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;

/**
 * @author Administrator
 *
 */
public interface AppPoiService {
    List<AppPoi> selectByExample(AppPoiExample example);
    List<AppPoi> selectLimt(int beginNum,int endNum,  @Param("example") AppPoiExample example);
	int insertSelective(AppPoi record);
	List<AppPoi> selectByExample(String app_poi_code, String appid);
	int deleteByExample(String app_poi_code, String appid);
	int updateByExampleSelective(AppPoi record, AppPoiExample example);
	AppPoi selectByPrimaryKey(int poi_id);
 }
