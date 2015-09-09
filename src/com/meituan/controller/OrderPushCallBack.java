/**
 * 
 */
package com.meituan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.utils.Const;
import com.base.utils.HttpClientUtil;
import com.base.utils.MapUtil;
import com.meituan.utils.SigUtil;
import com.meituan.utils.TimeUtil;

/**
 * @author zhangqw
 * 
 */
@Controller
@RequestMapping(value = "/Api")
public class OrderPushCallBack extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/orderPushCallBack")
	public String orderPushCallBack(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, String> params = MapUtil.getParameterMap(request);
		JSONObject resp = new JSONObject();
		if (null != params && !params.isEmpty() && params.containsKey("sig") && params.containsKey("app_id") && params.containsKey("timestamp")) {
			String sig = params.get("sig");
			params.remove("sig");
			String url = Const.getServerUrl(request) + "/Api" + "/orderPushCallBack";
			String md5sum = SigUtil.signRequest(url, params, Const.MEITUAN_APP_SECRET);
			if (!sig.equals(md5sum)) {
				resp.put("code", 703);
				resp.put("msg", "签名验证错误");
				logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			} else {				
				resp.put("data", "ok");
				logger.info(params.toString());
			}
		} else {
			resp.put("code", 701);
			resp.put("msg", "缺少参数，数据不完整");
			logger.error("缺少参数，数据不完整, params:" + params);
		}
		return resp.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/test")
	public String test() {
		String url = "http://waimaiopen.meituan.com/api/v1/poi/getids";
		int timestamp = TimeUtil.unixtime();

		Map<String, String> params = new HashMap<String, String>();
		params.put("app_id", Const.MEITUAN_APP_ID);
		params.put("timestamp", Integer.toString(timestamp));

		String md5sum = SigUtil.signRequest(url, params, Const.MEITUAN_APP_SECRET);
		params.put("sig", md5sum);
		logger.info(params.toString());
		String resp = HttpClientUtil.doGet(url, params, "UTF-8");
		if (null == resp) {
			logger.error("fail to post");
		}
		return resp;
	}
}
