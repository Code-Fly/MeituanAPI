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
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.MeituanConst;
import com.base.common.MeituanConst.MEITUAN_RETURN_CODE;
import com.base.controller.BaseController;
import com.base.entity.ApiControllerException;
import com.base.utils.CommonUtil;
import com.base.utils.HttpClientUtil;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.api.entity.ApiError;
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
	@RequestMapping(value = "/orderPushCallBack")
	public String orderPushCallBack(HttpServletRequest request) {
		Map<String, Object> params = MapUtil.getParameterMap(request);

		if (null != params && !params.isEmpty() && params.containsKey(MeituanConst.SIG) && params.containsKey(MeituanConst.APP_ID) && params.containsKey(MeituanConst.TIME_STAMP)) {
			String sig = params.get(MeituanConst.SIG).toString();
			params.remove(MeituanConst.SIG);
			String url = PathUtil.getServerUrl(request) + "/Api" + "/orderPushCallBack";
			String md5sum = SigUtil.signRequest(url, params, appService.selectByPrimaryKey(params.get(MeituanConst.APP_ID).toString()).getSecret());
			if (!sig.equals(md5sum)) {
				ApiError err = new ApiError(MEITUAN_RETURN_CODE.CODE_703, "签名验证错误");
				ApiData resp = new ApiData(MeituanConst.RETURN_NG, err);
				logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
				return JSONObject.fromObject(resp).toString();
			} else {
				MeituanOrder meituanOrder = new MeituanOrder();
				try {
					/**
					 * 数据转换后插入数据库
					 */
					meituanOrder = (MeituanOrder) CommonUtil.transMap2Bean(params, meituanOrder);
					orderService.insertSelective(meituanOrder);
				} catch (Exception e) {
					logger.error("数据转换错误.", e);
					/**
					 * 异常 交给Basecontroller处理
					 */
					throw new ApiControllerException(e.getMessage());
				}
				ApiData resp = new ApiData(MeituanConst.RETURN_OK);
				logger.info(params.toString());
				return JSONObject.fromObject(resp).discard("error").toString();
			}
		} else {
			ApiError err = new ApiError(MEITUAN_RETURN_CODE.CODE_701, "缺少参数，数据不完整");
			ApiData resp = new ApiData(MeituanConst.RETURN_NG, err);
			logger.error("缺少参数，数据不完整, params:" + params);
			return JSONObject.fromObject(resp).toString();
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
