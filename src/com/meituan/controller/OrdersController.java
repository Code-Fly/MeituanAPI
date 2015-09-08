/**
 * 
 */
package com.meituan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhangqw
 * 
 */
@Controller
@RequestMapping(value = "/order")
public class OrdersController extends BaseController {
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST,headers = "Content-Type=text/html")
	public String getOrderCommentsByTecId(@RequestParam("app_id") String app_id,@RequestParam("timestamp") String timestamp,@RequestParam("sig") String sig,@RequestBody String body) {
		System.out.println("body:"+body);
		logger.error("body:"+body);
		return "{\"data\" : \"ok\"}";
	}
	
	
}
