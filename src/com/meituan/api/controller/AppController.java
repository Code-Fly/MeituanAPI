/**
 * 
 */
package com.meituan.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.base.utils.JsonUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.utils.SigUtil;

import net.sf.json.JSONObject;

/**
 * @author VM
 *
 */
@Controller
@RequestMapping(value = "/Api")
public class AppController extends BaseController {
	
	/**
	 * 获取门店信息
	 * @param request
	 * @param sig
	 * @param app_id
	 * @param timestamp
	 * @param app_poi_code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getApp")
	public String getApp(HttpServletRequest request, 
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api/getApp";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app){
			logger.error("app_id("+app_id+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_702).toString();
		}
		String appSecret = app.getSecret();
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (false) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_703).toString();
		}
		ApiData appData = new ApiData(app.getPrice());
		return JsonUtil.json2Sting(appData);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryApps")
	public String getApp(HttpServletRequest request, 
			@RequestParam(value = "userId", required = true) int userId) {
		AppExample example = new AppExample();
		example.or().andUseridEqualTo(userId);
		List<App> apps = appService.selectByExample(example);
		ApiData ret = new ApiData(JsonUtil.jsonArray2Sting(apps));
		return JsonUtil.json2Sting(ret);
	}
}
