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
import com.meituan.api.entity.ApiData;
import com.meituan.api.entity.ApiError;
import com.meituan.app.service.iface.AppService;
import com.meituan.common.MeituanConst;
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
	private AppService appService;

	@Autowired
	private OrderService orderService;

	@ResponseBody
	@RequestMapping(value = "/orderConfirm")
	public String doConfirm(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "order_id_list", required = true) String order_id_list) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/orderConfirm";
		String md5sum = SigUtil.sign(url, params, appService.selectByPrimaryKey(app_id).getSecret(), "MD5");
		if (!sig.equals(md5sum)) {
			ApiError err = new ApiError(MeituanConst.CODE_703, "签名验证错误");
			ApiData ret = new ApiData(MeituanConst.RETURN_NG, err);
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(ret).toString();
		} else {
			MeituanOrder mOrder = new MeituanOrder();
			mOrder.setAppStatus(1);
			MeituanOrderExample example = new MeituanOrderExample();
			example.or().andOrderIdIn(JSONArray.toList(JSONArray.fromObject(order_id_list)));
			orderService.updateByExampleSelective(mOrder, example);
			
			ApiData ret = new ApiData(MeituanConst.RETURN_OK);
			return JSONObject.fromObject(ret).discard("error").toString();
		}
	}

}
