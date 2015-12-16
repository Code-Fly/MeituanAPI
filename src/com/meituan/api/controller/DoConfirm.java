/**
 * 
 */
package com.meituan.api.controller;

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
import com.meituan.app.entity.App;
import com.meituan.app.service.iface.AppService;
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
public class DoConfirm extends BaseController {


	@Autowired
	private OrderService orderService;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/orderConfirm")
	public String doConfirm(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "order_id_list", required = true) String order_id_list) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request,false);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/orderConfirm";
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
			MeituanOrder mOrder = new MeituanOrder();
			mOrder.setApp_status(1);
			MeituanOrderExample example = new MeituanOrderExample();
			example.or().andOrder_idIn(JSONArray.toList(JSONArray.fromObject(order_id_list))).andStatusNotEqualTo(9);
			orderService.updateByExampleSelective(mOrder, example);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
		}
	}

}
