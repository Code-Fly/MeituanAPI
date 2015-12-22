/**
 * 
 */
package com.meituan.apppoi.service.iface;


import java.util.List;

import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;

/**
 * @author Administrator
 *
 */
public interface AppPoiService {
    AppPoi selectByPrimaryKey(String app_poi_code,String appid);
    List<AppPoi> selectByExample(AppPoiExample example);
}
