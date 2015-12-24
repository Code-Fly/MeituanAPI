/**
 * 
 */
package com.meituan.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
import com.meituan.apppoi.service.iface.AppPoiService;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.utils.SigUtil;

import net.sf.json.JSONObject;

/**
 * @author VM
 *
 */
@Controller
@RequestMapping(value = "/Api")
public class AppPoiController extends BaseController {
	
	@Autowired
	private AppPoiService appPoiService;
	
	
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
	@RequestMapping(value = "/getAppPoi")
	public String getAppPoi(HttpServletRequest request, 
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api/getAppPoi";
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
		AppPoi poi = appPoiService.selectByPrimaryKey(app_poi_code, app_id);
		ApiData appData =  new ApiData(poi.getExpiredate());
		return JsonUtil.json2Sting(appData);
	}
	
	
	@RequestMapping(value = "/poiList")
	public String poiList(HttpServletRequest request) {
		return "/poiList";
	}
	/**
	 * 
	 * @param request
	 * @param userId
	 * @param page 第几页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/web/poiList")
	public String poiList(HttpServletRequest request, 
			// system params
			@RequestParam(value = "userId", required = false,defaultValue="1") int userId, 
			@RequestParam(value = "pageId", required = false,defaultValue="1") int page) {
		int pageSize = 20;
		AppPoiExample poiExample = new AppPoiExample();
		poiExample.or().andUseridEqualTo(userId);
		int beginNum = (page-1)*20;
		int endNum = page * 20;
		List<AppPoi> pois = appPoiService.selectByExample(poiExample);
		List<AppPoi> pagePois = new ArrayList<>();
		int poiSize = pois.size();
		if (pois.size() > 0 ) {
			if (poiSize < beginNum) {
				pagePois = pois;
			} else if (poiSize >  beginNum && poiSize < endNum ) {
				pagePois = pois.subList(beginNum, poiSize);
			} else {
				pagePois = pois.subList(beginNum, endNum);
			}
			int rowCount=0;
			if(poiSize%pageSize!=0)
		      {
		        rowCount = poiSize / pageSize + 1;
		      }
		      else
		      {
		        rowCount = poiSize / pageSize;
		    }
			String list = JsonUtil.jsonArray2Sting(pagePois);
			return "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+page+",\"list\":" + list + "}";
		}  else {
			return "NODATA";
		}
	
	}

}
