package com.base.controller;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.base.common.MeituanConst;
import com.base.common.MeituanConst.MEITUAN_RETURN_CODE;
import com.meituan.api.entity.ApiData;
import com.meituan.api.entity.ApiError;

/**
 * 
 * @author zhangqw
 *
 * @param <T>
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleUnexpectedServerError(RuntimeException ex) {
		ApiError err = new ApiError(MEITUAN_RETURN_CODE.CODE_600, "内部错误");
		ApiData resp = new ApiData(MeituanConst.RETURN_NG, err);
		return JSONObject.fromObject(resp).toString();		
	}

}
