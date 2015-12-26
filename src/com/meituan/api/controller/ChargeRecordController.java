/**
 * 
 */
package com.meituan.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.exception.ApiControllerException;
import com.base.utils.CommonUtil;
import com.base.utils.JsonUtil;
import com.base.utils.MapUtil;
import com.base.utils.PathUtil;
import com.meituan.api.entity.ApiData;
import com.meituan.app.entity.App;
import com.meituan.app.entity.AppExample;
import com.meituan.apppoi.entity.AppPoi;
import com.meituan.chargerecord.entity.ChargeRecord;
import com.meituan.chargerecord.entity.ChargeRecordExample;
import com.meituan.chargerecord.service.iface.ChargeRecordService;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.utils.SigUtil;

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
		if (false) {
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
			@RequestParam(value = "qsrq", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date qsrq,
			@RequestParam(value = "jsrq", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date jsrq) {
		
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
		if (false) {
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
				ApiData ret = new ApiData(JsonUtil.jsonArray2Sting(oList));
				return JsonUtil.json2Sting(ret);
			}
		}
		
	}
	
	
	

	@RequestMapping(value = "/chargeList")
	public String poiList(HttpServletRequest request) {
		return "/chargeRecordList";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param userId
	 * @param page 第几页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/web/chargeList")
	public String poiList(HttpServletRequest request, 
			// system params
			@RequestParam(value = "userId", required = false,defaultValue="1") int userId, 
			@RequestParam(value = "pageId", required = false,defaultValue="1") int page,
			@RequestParam(value = "app_id", required = false,defaultValue="") String app_id,
			@RequestParam(value = "app_poi_code", required = false,defaultValue="") String app_poi_code,
			@RequestParam(value = "startTime", required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date  startTime,
			@RequestParam(value = "endTime", required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime) {
		ChargeRecordExample recordExample = new ChargeRecordExample();
		ChargeRecordExample.Criteria criteria = recordExample.createCriteria();
		if (CommonUtil.isNotEmpty(app_poi_code)) {
			criteria.andApp_poi_codeEqualTo(app_poi_code);
		}
		if (CommonUtil.isNotEmpty(app_id)) {
			criteria.andApp_idEqualTo(app_id);
		}
		if (null != startTime) {
			criteria.andCzsjGreaterThanOrEqualTo(startTime);
		} 
		if (null != endTime) {
			criteria.andCzsjLessThanOrEqualTo(endTime);
		}
		request.getSession().setAttribute("userId",userId);
		request.getSession().setAttribute("startTime",startTime);
		request.getSession().setAttribute("endTime",endTime);
		request.getSession().setAttribute("app_poi_code",app_poi_code);
		request.getSession().setAttribute("app_id",app_id);
		int beginNum = (page-1)*20;
		int endNum = page * 20;
		List<ChargeRecord> chargeAllRecords = chargeRecordService.selectByExample(recordExample);
		AppExample appExample = new AppExample();
		appExample.or().andUseridEqualTo(userId);
		List<App> apps = appService.selectByExample(appExample);
		List<ChargeRecord> userChargeRecord = new ArrayList<>();
		List<ChargeRecord> pageRecords = new ArrayList<>();
		
		if (chargeAllRecords.size() > 0 ) {
			for(ChargeRecord recode:chargeAllRecords){
				for(App app:apps){
					if(recode.getApp_id().equals(app.getAppid())){
						userChargeRecord.add(recode);
					}
				}
			}
			int recordSize = userChargeRecord.size();
			if (recordSize < beginNum) {
				pageRecords = userChargeRecord;
			} else if (recordSize >  beginNum && recordSize < endNum ) {
				pageRecords = userChargeRecord.subList(beginNum, recordSize);
			} else {
				pageRecords = userChargeRecord.subList(beginNum, endNum);
			}
			int rowCount=0;
			if(recordSize%pageSize!=0)
		      {
		        rowCount = recordSize / pageSize + 1;
		      }
		      else
		      {
		        rowCount = recordSize / pageSize;
		    }
			String list = JsonUtil.jsonArray2Sting(pageRecords);
			return "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+page+",\"list\":" + list + "}";
		}  else {
			return "NODATA";
		}
	
	}
	
}
