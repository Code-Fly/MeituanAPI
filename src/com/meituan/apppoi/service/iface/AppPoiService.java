/**
 * 
 */
package com.meituan.apppoi.service.iface;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
import com.meituan.apppoi.entity.AppPoiKey;

/**
 * @author Administrator
 *
 */
public interface AppPoiService {
    AppPoi selectByPrimaryKey(String app_poi_code,String appid);
    List<AppPoi> selectByExample(AppPoiExample example);
    List<AppPoi> selectLimt(int beginNum,int endNum,  @Param("example") AppPoiExample example);
	int deleteByPrimaryKey(AppPoiKey key);
	int updateByPrimaryKeySelective(AppPoi record);
 }
