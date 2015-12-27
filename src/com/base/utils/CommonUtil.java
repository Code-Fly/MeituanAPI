/**
 * 
 */
package com.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Administrator
 * 
 */
public class CommonUtil {

	/**
	 * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 * 
	 * @param map
	 * @param obj
	 * @throws Exception
	 */
	public static Object transMap2Bean(Map<String, Object> map, Object obj)
			throws Exception {
		BeanUtils.populate(obj, map);
		return obj;
	}
	
	
	public static void main(String[] args) {
		System.out.println(addYear(new Date(),2));
	}
	
	public static boolean isEmpty(String arg){
		if (null == arg || arg.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(String arg){
		return !isEmpty(arg);
	}
	
	public static Date addYear(Date date,int year){
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.add(Calendar.YEAR,+year);
	        return rightNow.getTime();
	}
	
	public static Date addMonth(Date date,int month){
		 	Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.add(Calendar.MONTH,+month);
	        return rightNow.getTime();
	}
	
	public static String date2String(Date date){
		if(null==date){
			return "";
		} else {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);  
		     return sdf.format(date);
		}
		
	} 
	
	
	
}