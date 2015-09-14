/**
 * 
 */
package com.meituan.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.exception.ApiControllerException;
import com.base.utils.CommonUtil;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.api.entity.ApiError;
import com.meituan.app.service.iface.AppService;
import com.meituan.common.MeituanConst;
import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.service.iface.OrderService;
import com.meituan.utils.SigUtil;

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
	@RequestMapping(value = "/orderPushCallback")
	public String orderPushCallBack(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "order_id", required = true) String order_id,
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code) {

		Map<String, Object> params = MapUtil.decodeParameterMap(MapUtil.getParameterMap(request));
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/orderPushCallback";
		String appSecret = appService.selectByPrimaryKey(app_id).getSecret();
		if(null == appSecret || appSecret.isEmpty()){
			ApiError err = new ApiError(MeituanConst.CODE_702, "app_id不存在");
			ApiData ret = new ApiData(MeituanConst.RETURN_NG, err);
			logger.error("app_id不存在");
			return JSONObject.fromObject(ret).toString();
		}
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (!sig.equals(md5sum)) {
			ApiError err = new ApiError(MeituanConst.CODE_703, "签名验证错误");
			ApiData ret = new ApiData(MeituanConst.RETURN_NG, err);
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(ret).toString();
		} else {
			MeituanOrder meituanOrder = new MeituanOrder();
			try {
				meituanOrder = (MeituanOrder) CommonUtil.transMap2Bean(params, meituanOrder);
			} catch (Exception e) {				
				logger.error("数据转换错误");
				throw new ApiControllerException("数据转换错误",e);
			}
			orderService.insertSelective(meituanOrder);
			ApiData ret = new ApiData(MeituanConst.RETURN_OK);
			return JSONObject.fromObject(ret).discard("error").toString();
		}
	}
}
