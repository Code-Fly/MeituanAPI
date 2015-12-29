/**
 * 
 */
package com.meituan.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.base.utils.CommonUtil;
import com.base.utils.JsonUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.apppoi.entity.AppPoiExample;
import com.meituan.apppoi.entity.AppPoiKey;
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
			@RequestParam(value = "poi_id", required = true) int poi_id) {
		AppPoi poi = appPoiService.selectByPrimaryKey(poi_id);
		if(null == poi){
			return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
		} else {
			ApiData appData =  new ApiData(poi);
			return JsonUtil.json2Sting(appData);
		}
		
	}
	
	
	@RequestMapping(value = "/poiList")
	public String poiList(HttpServletRequest request) {
		 request.getSession().setAttribute("wm_poi_name","");
		 request.getSession().setAttribute("wm_poi_code","");
		 request.getSession().setAttribute("wm_poi_phone","");
		return "/poiList";
	}
	
	
/**
 * 
 * @param request
 * @param app_id
 * @param poi_code
 * @return
 */
	@ResponseBody
	@RequestMapping(value = "/web/deletePoi")
	public String deletePoi(HttpServletRequest request, 
			// system params
			@RequestParam(value = "app_id", required = true) String app_id, 
			@RequestParam(value = "poi_code", required = true) String poi_code) {
		appPoiService.deleteByExample(poi_code, app_id);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param app_id
	 * @param poi_code
	 * @return
	 */
		@ResponseBody
		@RequestMapping(value = "/web/updatePoi")
		public String updatePoi(HttpServletRequest request, 
				@RequestParam(value = "app_id", required = true) String app_id, 
				@RequestParam(value = "poi_code", required = true) String poi_code,
				@RequestParam(value = "name", required = true) String name,
				@RequestParam(value = "phone", required = false,defaultValue="")  String phone,
				@RequestParam(value = "address", required = false,defaultValue="") String address) {
			AppPoiExample example = new AppPoiExample();
			example.or().andAppidEqualTo(app_id).andApp_poi_codeEqualTo(poi_code);
			AppPoi appPoi = new AppPoi();
			appPoi.setAppid(app_id);
			appPoi.setApp_poi_code(poi_code);
			appPoi.setWm_poi_name(name);
			appPoi.setWm_poi_phone(phone);
			appPoi.setWm_poi_address(address);
			appPoiService.updateByExampleSelective(appPoi, example);
			return SUCCESS;
		}
		
		
		/**
		 * 
		 * @param request
		 * @param app_id
		 * @param poi_code
		 * @return
		 */
			@ResponseBody
			@RequestMapping(value = "/web/addPoi")
			public String addPoi(HttpServletRequest request, 
					@RequestParam(value = "userId", required = true) int userId, 
					@RequestParam(value = "appid", required = true) String appid, 
					@RequestParam(value = "app_poi_code", required = true) String app_poi_code,
					@RequestParam(value = "wm_poi_name", required = true) String wm_poi_name,
					@RequestParam(value = "wm_poi_phone", required = false)  String wm_poi_phone,
					@RequestParam(value = "wm_poi_address", required = false) String wm_poi_address,
					@RequestParam(value = "descption", required = false) String descption,
					@RequestParam(value = "price", required = false) Float price) {
				AppPoi appPoi = new AppPoi();
				appPoi.setUserid(userId);
				appPoi.setAppid(appid);
				appPoi.setApp_poi_code(app_poi_code);
				appPoi.setWm_poi_name(wm_poi_name);
				appPoi.setWm_poi_phone(wm_poi_phone);
				appPoi.setWm_poi_address(wm_poi_address);
				appPoi.setExpiredate(CommonUtil.addMonth(new Date(),2));
				appPoi.setDescption(descption);
				appPoi.setPrice(price);
				appPoiService.insertSelective(appPoi);
				return SUCCESS;
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
			@RequestParam(value = "pageId", required = false,defaultValue="1") int page,
			@RequestParam(value = "wm_poi_name", required = false,defaultValue="") String poi_name,
			@RequestParam(value = "app_poi_code", required = false,defaultValue="") String poi_code,
			@RequestParam(value = "wm_poi_phone", required = false,defaultValue="") String poi_phone) {
		AppPoiExample poiExample = new AppPoiExample();
		AppPoiExample.Criteria criteria = poiExample.createCriteria();
		if(userId!=1){
			criteria.andUseridEqualTo(userId);
		}
		if (CommonUtil.isNotEmpty(poi_name)) {
			criteria.andWm_poi_nameLike(poi_name+"%");
		}
		if (CommonUtil.isNotEmpty(poi_code)) {
			criteria.andApp_poi_codeEqualTo(poi_code);
		}
		if (CommonUtil.isNotEmpty(poi_phone)) {
			criteria.andWm_poi_phoneLike(poi_phone+"%");
		}
		 request.getSession().setAttribute("userId",userId);
		 request.getSession().setAttribute("wm_poi_name",poi_name);
		 request.getSession().setAttribute("app_poi_code",poi_code);
		 request.getSession().setAttribute("wm_poi_phone",poi_phone);
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
