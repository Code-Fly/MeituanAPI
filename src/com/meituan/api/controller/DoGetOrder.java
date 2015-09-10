/**
 * 
 */
package com.meituan.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.meituan.app.service.iface.AppService;
import com.meituan.order.service.iface.OrderService;

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
	public String doGetOrder(HttpServletRequest request) {
		
		return null;
	}
}
