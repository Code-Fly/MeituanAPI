/**
 * 
 */
package com.base.utils;

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

}
