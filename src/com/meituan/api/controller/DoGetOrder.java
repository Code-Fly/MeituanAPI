/**
 * 
 */
package com.meituan.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.app.service.iface.AppService;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.entity.MeituanOrderExample;
import com.meituan.order.service.iface.OrderService;
import com.meituan.utils.SigUtil;

/**
 * @author Barrie
 *
 */
@Controller
@RequestMapping(value = "/Api")
public class DoGetOrder extends BaseController {

	@Autowired
	private AppService appService;

	@Autowired
	private OrderService orderService;

	@ResponseBody
	@RequestMapping(value = "/doGetOrder")
	public String doGetOrder(HttpServletRequest request, 
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request,false);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/doGetOrder";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app ){
			logger.error("app_id("+app_id+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_702).toString();
		}
		String appSecret = app.getSecret();
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (!sig.equals(md5sum)) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_703).toString();
		} else {
			AppPoi poi = appPoiService.selectByPrimaryKey(app_poi_code);
			if (null == poi) {
				return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
			} else if (poi.getExpiredate().before(new Date())) {
				return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
			} else  {
				MeituanOrderExample example = new MeituanOrderExample();
				example.or().andApp_poi_codeEqualTo(app_poi_code).andApp_statusEqualTo(0).andStatusNotEqualTo(9);
				List<MeituanOrder> oList = orderService.selectByExample(example);
				
				JSONArray resp = JSONArray.fromObject(oList);			
				ApiData ret = new ApiData(resp);
				return JSONObject.fromObject(ret).discard("error").toString();
			}
		
		}
		
	}
}
