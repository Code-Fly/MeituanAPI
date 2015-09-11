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
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
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
	@RequestMapping(value = "/doGetOrder")
	public String doGetOrder(HttpServletRequest request, 
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "mid", required = true) String mid) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/doGetOrder";
		String md5sum = SigUtil.sign(url, params, appService.selectByPrimaryKey(app_id).getSecret(), "MD5");
		if (!sig.equals(md5sum)) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(ApiData.REP_ERROR_703).toString();
		} else {
			MeituanOrderExample example = new MeituanOrderExample();
			example.or().andAppPoiCodeEqualTo(mid).andAppStatusEqualTo(0);
			List<MeituanOrder> oList = orderService.selectByExample(example);
			JSONArray resp = JSONArray.fromObject(oList);
			return resp.toString();
		}
		
	}
}
