/**
 * 
 */
package com.base.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class CommonUtil {
	
//	 public  static String TURE = "true";
//	 public  static String FALSE = "false";
	 private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
//	 private static AppService appService;
//
//	    @Autowired(required = true)
//	    public void setTaskService(AppService appService) {
//	    	CommonUtil.appService = appService;
//	    }
//	
	
	 /**
	  * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	  *@param map
	  *@param obj
	  *@throws Exception 
	  */
    public static  Object transMap2Bean(Map<String, Object> map, Object obj) throws Exception {
    	BeanUtils.populate(obj, map);
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
//       public static String  sigIsOk(HttpServletRequest request,String path,String sig,String appId) {
//    	   Map<String, Object> params = MapUtil.getParameterMap(request);
//   		params.remove("sig");
//   		String url = PathUtil.getServerUrl(request) + path;
//   		App app = appService.selectByPrimaryKey(appId);
//   		if(null == app){
//   			return JSONObject.fromObject(ApiData.REP_ERROR_702).toString();
//   		}
//   		String md5sum = SigUtil.sign(url, params, appService.selectByPrimaryKey(appId).getSecret(), "MD5");
//   		if (!sig.equals(md5sum)) {
//   			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
//			return JSONObject.fromObject(ApiData.REP_ERROR_703).toString();
//		} else{
//			return TRUE;
//		}
//       }

    }