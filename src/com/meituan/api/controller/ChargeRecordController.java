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
import com.meituan.apppoi.entity.AppPoiExample;
import com.meituan.apppoi.service.iface.AppPoiService;
import com.meituan.chargerecord.entity.ChargeRecord;
import com.meituan.chargerecord.entity.ChargeRecordExample;
import com.meituan.chargerecord.service.iface.ChargeRecordService;
import com.meituan.common.MeituanConst.MeituanResponse;
import com.meituan.users.service.iface.LoginUsersService;
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
	
	@Autowired
	private LoginUsersService loginUsersService;
	
	
	@Autowired
	private AppPoiService appPoiService;
	
	@ResponseBody
	@RequestMapping(value = "/charge")
	public String charge(HttpServletRequest request,
			@RequestParam(value = "poi_id", required = true) int poi_id
			) {
		Map<String, Object> params = MapUtil.getParameterMap(request,true);
		AppPoi apppoi = appPoiService.selectByPrimaryKey(poi_id);
		if(null == apppoi){
			logger.error("门店("+apppoi+")不存在");
			return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
		}
			ChargeRecord chargeRecord = new ChargeRecord();
			try {
				chargeRecord = (ChargeRecord) CommonUtil.transMap2Bean(params, chargeRecord);
				chargeRecord.setPoi_name(apppoi.getWm_poi_name());
			} catch (Exception e) {				
				logger.error("数据转换错误");
				throw new ApiControllerException("数据转换错误",e);
			}
			chargeRecordService.insertSelective(chargeRecord);
			return JSONObject.fromObject(MeituanResponse.RESPONSE_OK).discard("error").toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getChargeRecords")
	public String getChargeRecords(HttpServletRequest request, 
			@RequestParam(value = "poi_id", required = true) int poi_id,
			@RequestParam(value = "qsrq", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date qsrq,
			@RequestParam(value = "jsrq", required = true) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date jsrq) {
			AppPoi poi = appPoiService.selectByPrimaryKey(poi_id);
			if (null == poi) {
				return JSONObject.fromObject(MeituanResponse.RESPONSE_803).toString();
			} else  {	
				ChargeRecordExample example = new ChargeRecordExample();
				example.or().andPoi_idEqualTo(poi_id).andCzsjBetween(qsrq, jsrq);
				List<ChargeRecord> oList = chargeRecordService.selectByExample(example);
				ApiData ret = new ApiData(JsonUtil.jsonArray2Sting(oList));
				return JsonUtil.json2Sting(ret);
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
			@RequestParam(value = "poi_name", required = false,defaultValue="") String poi_name,
			@RequestParam(value = "startTime", required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date  startTime,
			@RequestParam(value = "endTime", required = false)@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime) {
		ChargeRecordExample recordExample = new ChargeRecordExample();
		ChargeRecordExample.Criteria criteria = recordExample.createCriteria();
		
		if (CommonUtil.isNotEmpty(poi_name)) {
			criteria.andPoi_nameLike(poi_name+"%");
		}
		if (null != startTime) {
			criteria.andCzsjGreaterThanOrEqualTo(startTime);
		} 
		if (null != endTime) {
			criteria.andCzsjLessThanOrEqualTo(endTime);
		}
		// 统计差额
		float chae = 0;
		request.getSession().setAttribute("userId",userId);
		request.getSession().setAttribute("startTime",CommonUtil.date2String(startTime));
		request.getSession().setAttribute("endTime",CommonUtil.date2String(endTime));
		request.getSession().setAttribute("poi_name",poi_name);
		int beginNum = (page-1)*20;
		int endNum = page * 20;
		List<ChargeRecord> chargeAllRecords = chargeRecordService.selectByExample(recordExample);
		AppPoiExample poiExample = new AppPoiExample();
		poiExample.or().andUseridEqualTo(userId);
		List<AppPoi> pois = appPoiService.selectByExample(poiExample);
		List<ChargeRecord> userChargeRecord = new ArrayList<>();
		List<ChargeRecord> pageRecords = new ArrayList<>();
		if (chargeAllRecords.size() > 0 ) {
			for(ChargeRecord recode:chargeAllRecords){
				for(AppPoi poi:pois){
					if(recode.getPoi_id() == poi.getPoi_id()){
						userChargeRecord.add(recode);
						chae+=(recode.getCzje()-loginUsersService.selectByPrimaryKey(appPoiService.selectByPrimaryKey(recode.getPoi_id()).getUserid()).getNfdj()*recode.getCzns());
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
			request.getSession().setAttribute("chae",chae);
			String list = JsonUtil.jsonArray2Sting(pageRecords);
			return "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+page+",\"list\":" + list + ",\"chae\":"+chae+"}";
		}  else {
			return "NODATA";
		}
	
	}
	
}
