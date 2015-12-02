/**
 * 
 */
package com.meituan.apppoi.service.iface;


import com.meituan.apppoi.entity.AppPoi;

/**
 * @author Administrator
 *
 */
public interface AppPoiService {
    AppPoi selectByPrimaryKey(String app_poi_code,String appid);
}
