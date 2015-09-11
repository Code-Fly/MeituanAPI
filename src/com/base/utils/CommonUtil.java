/**
 * 
 */
package com.base.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.meituan.api.entity.MeituanRespData;
import com.meituan.app.entity.App;
import com.meituan.app.service.iface.AppService;
import com.meituan.utils.SigUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class CommonUtil {
	
	 public  static String RETURN_TRUE = "true";
	 private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	 private static AppService appService;

	    @Autowired(required = true)
	    public void setTaskService(AppService appService) {
	    	CommonUtil.appService = appService;
	    }
	
	
	 /**
	  * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	  *@param map
	  *@param obj
	  *@throws Exception 
	  */
    public static  Object transMap2Bean(Map<String, Object> map, Object obj) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }

        }
        return obj;
        }
        
        
      /**
       * 校验签名
       * @param request
       * @param path
       * @param sig
       * @param appId
       * @return
       */
       public static String  sigIsOk(HttpServletRequest request,String path,String sig,String appId) {
    	   Map<String, Object> params = MapUtil.getParameterMap(request);
   		params.remove("sig");
   		String url = PathUtil.getServerUrl(request) + path;
   		App app = appService.selectByPrimaryKey(appId);
   		if(null == app){
   			return JSONObject.fromObject(MeituanRespData.REP_ERROR_702).toString();
   		}
   		String md5sum = SigUtil.sign(url, params, appService.selectByPrimaryKey(appId).getSecret(), "MD5");
   		if (!sig.equals(md5sum)) {
   			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanRespData.REP_ERROR_703).toString();
		} else{
			return RETURN_TRUE;
		}
       }

    }

