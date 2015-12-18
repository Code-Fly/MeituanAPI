/**
 * 
 */
package com.meituan.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.meituan.app.entity.App;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.chargerecord.entity.ChargeRecord;
import com.meituan.chargerecord.entity.ChargeRecordExample;
import com.meituan.chargerecord.service.iface.ChargeRecordService;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.utils.SigUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/Api")
public class ChargeRecordController extends BaseController {
	
	@Autowired
	private ChargeRecordService chargeRecordService;
	
	@ResponseBody
	@RequestMapping(value = "/charge")
	public String charge(HttpServletRequest request,
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code) {
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/charge";
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
			ChargeRecord chargeRecord = new ChargeRecord();
			try {
				chargeRecord = (ChargeRecord) CommonUtil.transMap2Bean(params, chargeRecord);
			} catch (Exception e) {				
				logger.error("数据转换错误");
				throw new ApiControllerException("数据转换错误",e);
			}
			chargeRecordService.insertSelective(chargeRecord);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getChargeRecords")
	public String getChargeRecords(HttpServletRequest request, 
			// system params
			@RequestParam(value = "sig", required = true) String sig, 
			@RequestParam(value = "app_id", required = true) String app_id,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			// application params
			@RequestParam(value = "app_poi_code", required = true) String app_poi_code,
			@RequestParam(value = "qsrq", required = true) Date qsrq,
			@RequestParam(value = "jsrq", required = true) Date jsrq) {
		
		Map<String, Object> params = MapUtil.getParameterMap(request,false);
		params.remove("sig");
		String url = PathUtil.getServerUrl(request) + "/Api" + "/getChargeRecords";
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
			AppPoi poi = appPoiService.selectByPrimaryKey(app_poi_code,app_id);
			if (null == poi) {
				return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
			} else  {	
				ChargeRecordExample example = new ChargeRecordExample();
				example.or().andApp_idEqualTo(app_id).andApp_poi_codeEqualTo(app_poi_code).andCzsjBetween(qsrq, jsrq);
				List<ChargeRecord> oList = chargeRecordService.selectByExample(example);
				JSONArray resp = JSONArray.fromObject(oList);			
				ApiData ret = new ApiData(resp);
				return JSONObject.fromObject(ret).discard("error").toString();
			}
		}
		
	}
	
		
}
