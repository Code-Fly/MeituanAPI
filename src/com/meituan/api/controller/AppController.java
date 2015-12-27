/**
 * 
 */
package com.meituan.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.css.ElementCSSInlineStyle;

import com.base.controller.BaseController;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.base.utils.CommonUtil;
import com.base.utils.JsonUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
import com.meituan.apppoi.service.iface.AppPoiService;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.utils.SigUtil;
import com.thoughtworks.xstream.mapper.Mapper.Null;

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
	
	@RequestMapping(value = "/web/appList")
	public String appList(HttpServletRequest request, 
			@RequestParam(value = "userId", required = true) int userId) {
		AppExample example = new AppExample();
		if(1!= userId){
			example.or().andUseridEqualTo(userId);
		}
		List<App> apps = appService.selectByExample(example);
		request.setAttribute("apps", apps);
		return "/appList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/web/deleteApp")
	public String deleteApp(HttpServletRequest request, 
			@RequestParam(value = "app_id", required = true) String app_id) {
		AppPoiExample example = new AppPoiExample();
		example.or().andAppidEqualTo(app_id);
		List<AppPoi> pois = appPoiService.selectByExample(example);
		if (pois.size()>0) {
			return "HASPOI";
		} else {
			appService.deleteByPrimaryKey(app_id);
			return SUCCESS;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/web/updateApp")
	public String updateApp(HttpServletRequest request, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "price", required = false,defaultValue="0") Float price,
			@RequestParam(value = "descption", required = false, defaultValue=" ") String descption,
			@RequestParam(value = "secret", required = true) String secret) {
		App app = new App();
		app.setAppid(app_id);
		app.setDescption(descption);
		app.setPrice(price);
		app.setSecret(secret);
		appService.updateByPrimaryKeySelective(app);
		return SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value = "/web/addApp")
	public String addApp(HttpServletRequest request, 
			@RequestParam(value = "appid", required = true) String app_id,
			@RequestParam(value = "price", required = false,defaultValue="0") Float price,
			@RequestParam(value = "descption", required = false) String descption,
			@RequestParam(value = "secret", required = true) String secret,
			@RequestParam(value = "userid", required = true) int userId) {
		 if(null != appService.selectByPrimaryKey(app_id)){
			 return "HASAPP";
		 } else {
			 App app = new App();
			 app.setAppid(app_id);
			 app.setDescption(descption);
			 app.setPrice(price);
			 app.setSecret(secret);
			 app.setUserid(userId);
			 app.setExpiredate(CommonUtil.addMonth(new Date(),2));
			 appService.insertSelective(app);
		 }
		return SUCCESS;
	}
}
