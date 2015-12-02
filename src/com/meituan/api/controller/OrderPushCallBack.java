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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.exception.ApiControllerException;
import com.base.utils.CommonUtil;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.app.service.iface.AppService;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.common.MeituanConst.OrderStatus;
import com.meituan.order.entity.MeituanOrder;
import com.meituan.order.service.iface.OrderService;
import com.meituan.refund.entity.Refund;
import com.meituan.refund.entity.RefundExample;
import com.meituan.refund.service.iface.RefundService;
import com.meituan.utils.SigUtil;

/**
 * @author zhangqw
 * 供美团回调
 */
@Controller
@RequestMapping(value = "/Api")
public class OrderPushCallBack extends BaseController {

	@Autowired
	private AppService appService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RefundService refundService;

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

		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/orderPushCallback";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app){
			logger.error("app_id("+app_id+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_702).toString();
		}
		String appSecret = app.getSecret();
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (!sig.equals(md5sum)) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_703).toString();
		} else {
			MeituanOrder meituanOrder = new MeituanOrder();
			try {
				meituanOrder = (MeituanOrder) CommonUtil.transMap2Bean(params, meituanOrder);
			} catch (Exception e) {				
				logger.error("数据转换错误");
				throw new ApiControllerException("数据转换错误",e);
			}
			orderService.insertSelective(meituanOrder);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
		}
	}
	
	/**
	 * 用户或客服取消订单API
	 * @param request
	 * @param sig
	 * @param app_id
	 * @param timestamp
	 * @param order_id
	 * @param reason_code
	 * @param reason
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/cancelOrder")
	public String cancelOrder(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "order_id", required = true) Integer order_id,
			@RequestParam(value = "reason_code", required = false) String reason_code,
			@RequestParam(value = "reason", required = false) String reason){
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api/cancelOrder";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app){
			logger.error("app_id("+app_id+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_702).toString();
		}
		String appSecret = app.getSecret();
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (!sig.equals(md5sum)) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_703).toString();
		} else {
			MeituanOrder meituanOrder = new MeituanOrder();
			meituanOrder.setOrder_id(order_id);
			meituanOrder.setStatus(OrderStatus.CANCELLED);
			orderService.updateByPrimaryKeySelective(meituanOrder);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
		}
		
	}
	
	/**
	 * 申请退款
	 * @param request
	 * @param sig
	 * @param app_id
	 * @param timestamp
	 * @param order_id
	 * @param notify_type  通知类型，apply：发起退款
						agree：确认退款
						reject：驳回退款
						cancelRefund：用户取消退款申请
						cancelRefundComplaint :取消退款申诉 
	 * @param reason
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/refund")
	public String refund(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "order_id", required = true) Integer order_id,
			@RequestParam(value = "notify_type", required = true) String notify_type,
			@RequestParam(value = "reason", required = false) String reason){
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api/refund";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app){
			logger.error("app_id("+app_id+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_702).toString();
		}
		String appSecret = app.getSecret();
		String md5sum = SigUtil.sign(url, params, appSecret, "MD5");
		if (!sig.equals(md5sum)) {
			logger.error("签名验证错误, sig:" + sig + ", md5sum:" + md5sum);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_703).toString();
		} else {
			Refund refund = new Refund();
			refund.setOrder_id(order_id);
			refund.setNotify_type(notify_type);
			refund.setReason(reason);
			refund.setApp_poi_code(orderService.selectByPrimaryKey(order_id).getApp_poi_code());
			if(null == refundService.selectByPrimaryKey(order_id))
			{
				refundService.insertSelective(refund);
			} 
			else 
			{
				refundService.updateByPrimaryKeySelective(refund);
			}
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
		}
		
	}
	
	/**
	 * 获取退款信息
	 * @param request
	 * @param sig
	 * @param app_id
	 * @param timestamp
	 * @param notify_type  通知类型，apply：发起退款
						agree：确认退款
						reject：驳回退款
						cancelRefund：用户取消退款申请
						cancelRefundComplaint :取消退款申诉 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/getRefund")
	public String getRefund(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "notify_type", required = true) String notify_type,
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code){
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api/getRefund";
		App app = appService.selectByPrimaryKey(app_id);
		if(null == app){
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
			}  else  {
				RefundExample refundExample = new RefundExample();
				refundExample.or().andNotify_typeEqualTo(notify_type).andApp_poi_codeEqualTo(app_poi_code);
				List<Refund> refunds = refundService.selectByExample(refundExample);
				JSONArray resp = JSONArray.fromObject(refunds);			
				ApiData ret = new ApiData(resp);
				return JSONObject.fromObject(ret).discard("error").toString();
			}
		}
		
	}
}
