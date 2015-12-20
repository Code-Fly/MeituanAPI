/**
 * 
 */
package com.meituan.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web")
public class WebController extends BaseController {
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "/login";
	}
	
	@RequestMapping(value = "/index")
	public String app(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "/app";
	}
}
