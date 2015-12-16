package com.base.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.base.utils.JsonDateValueProcessor;
import com.meituan.api.entity.ApiData;
/**
 * 
 * @author zhangquanwei
 *
 */
public class JsonUtil {
	
	public static String json2Sting(ApiData o){
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		//JavaBean转JSON  
		if(null == o.getError()){
			return JSONObject.fromObject(o, jsonConfig).discard("error").toString();
		}  else {
			return JSONObject.fromObject(o, jsonConfig).toString();
		}
	}
	
	public static String jsonArray2Sting(Collection<?> o){
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		//Array 转JSON  
		return JSONArray.fromObject(o, jsonConfig).toString();
	}
	
	public static String jsonMap2Sting(List<HashMap<String, Object>> list){
		StringBuffer  buffer = new StringBuffer();
		buffer.append("[");
		for(int i=0;i<list.size();i++){
			buffer.append(mapToJson(list.get(i)));
			if(i != list.size() -1 )
				buffer.append(",");
		}
		buffer.append("]");
		//Array 转JSON  
		return buffer.toString();
	}
	
	 /**
     * 
    * map转换json.
    * <br>详细说明
    * @param map 集合
    * @return
    * @return String json字符串
    * @throws
    * @author slj
     */
    public static String mapToJson(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        String key = "";
        Object value;
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = (String) it.next();
            value = map.get(key);
            if(value instanceof Date){  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);  
                jsonBuffer.append(key + ":" +"\""+ sdf.format(value)+"\"");
            } else {
            	 jsonBuffer.append(key + ":" +"\""+ value+"\"");
            }
           
            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }
}
