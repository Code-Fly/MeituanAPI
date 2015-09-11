/**
 * 
 */
package com.meituan.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.CommonUtil;
import com.base.utils.HttpClientUtil;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.MeituanRespData;
import com.meituan.app.entity.App;
import com.meituan.app.service.iface.AppService;
import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.service.iface.OrderService;
import com.meituan.utils.SigUtil;
import com.meituan.utils.TimeUtil;

/**
 * @author zhangqw
 * 
 */
@Controller
@RequestMapping(value = "/Api")
public class OrderPushCallBack extends BaseController {

	@Autowired
	private AppService appService;

	@Autowired
	private OrderService orderService;

	@ResponseBody
	@RequestMapping(value = "/order_push_callback")
	public String orderPushCallBack(HttpServletRequest request, 
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp) {
		String result = CommonUtil.sigIsOk(request, "/Api/order_push_callback", sig, app_id);
		if(result.equals(CommonUtil.RETURN_TRUE)){
			Map<String, Object> params = MapUtil.getParameterMap(request);
			params.remove("sig");
			MeituanOrder meituanOrder = new MeituanOrder();
			try {
				/**
				 * 数据转换后插入数据库
				 */
				meituanOrder = (MeituanOrder) CommonUtil.transMap2Bean(params, meituanOrder);
			} catch (Exception e) {
				logger.error("数据转换错误.", e);
				return JSONObject.fromObject(MeituanRespData.REP_ERROR_600).toString();
			}
			orderService.insertSelective(meituanOrder);
			logger.info(params.toString());
			return JSONObject.fromObject(MeituanRespData.REP_OK).discard("error").toString();
		}
		else {
			return result;
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "/test")
	public String test() {
		String url = "http://waimaiopen.meituan.com/api/v1/poi/getids";
		int timestamp = TimeUtil.unixtime();

		Map<String, String> params = new HashMap<String, String>();
		// params.put("app_id", Path.MEITUAN_APP_ID);
		params.put("timestamp", Integer.toString(timestamp));

		// String md5sum = SigUtil.signRequest(url, params,
		// Path.MEITUAN_APP_SECRET);
		// params.put("sig", md5sum);
		logger.info(params.toString());
		String resp = HttpClientUtil.doGet(url, params, "UTF-8");
		if (null == resp) {
			logger.error("fail to post");
		}
		return resp;
	}
}
