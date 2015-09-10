/**
 * 
 */
package com.meituan.api.controller;

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
import com.base.entity.ApiResp;
import com.base.utils.MapUtil;
import com.meituan.app.service.iface.AppService;
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
	@RequestMapping(value = "/do_get_order")
	public String doGetOrder(HttpServletRequest request, 
			// system params
			@RequestParam(value = "msid", required = true) String msid,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String none,
			@RequestParam(value = "signtype", required = true) String signtype, 
			@RequestParam(value = "msg_sign", required = true) String msg_sign,
			//application params
			@RequestParam(value = "id", required = true) String id) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request);
		params.remove("msg_sign");
		String sha1sum = SigUtil.sign(null, params, appService.selectByPrimaryKey(params.get("msid").toString()).getSecret(), "SHA-1");
		if (!msg_sign.equals(sha1sum)) {
			ApiResp resp = new ApiResp(0, "签名验证错误");
			logger.error("签名验证错误, sig:" + msg_sign + ", sha1sum:" + sha1sum);
			return JSONObject.fromObject(resp).toString();
		} else {
			MeituanOrderExample example = new MeituanOrderExample();
			example.or().andAppPoiCodeEqualTo(id).andAppStatusEqualTo(0);
			List<MeituanOrder> oList = orderService.selectByExample(example);
			JSONArray resp = JSONArray.fromObject(oList);
			return resp.toString();
		}
		
	}
}
