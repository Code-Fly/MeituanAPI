package com.base.controller;

import net.sf.json.JSONObject;

import javax.security.auth.login.FailedLoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.meituan.app.service.iface.AppService;
import com.meituan.apppoi.service.iface.AppPoiService;
import com.meituan.common.MeituanConst.MeituanResponse;


/**
 * 
 * @author zhangqw
 *
 * @param <T>
 */
public abstract class BaseController {
	
	public String SUCCESS = "OPSUCCESS";
	public String FAIL =  "OPFAIL";
	
	@Autowired
	protected AppPoiService appPoiService;
	
	@Autowired
	protected AppService appService;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleUnexpectedServerError(RuntimeException ex) {
		logger.error("内部错误", ex);
		return JSONObject.fromObject(MeituanResponse.RESPONSE_600).toString();
	}

}
