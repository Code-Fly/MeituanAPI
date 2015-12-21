/**
 * 
 */
package com.meituan.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.meituan.users.entity.LoginUsers;
import com.meituan.users.entity.LoginUsersExample;
import com.meituan.users.service.iface.LoginUsersService;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/Api")
public class UsersController extends BaseController {
	@Autowired
	private LoginUsersService loginUsersService;
	/**
	 * 登陆用户名和密码校验，错误返回error，正确返回对应的opID
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public String loginOp(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		LoginUsersExample loginUsersExample = new LoginUsersExample();
		loginUsersExample.or().andLogin_idEqualTo(userName);
		List<LoginUsers> tabMasterOps = loginUsersService.selectByExample(loginUsersExample);
		if (null == tabMasterOps || 0 == tabMasterOps.size()) {
			return "";
		} else {
			if (password.equals(tabMasterOps.get(0).getLogin_pass())) {
				return tabMasterOps.get(0).getUser_id().toString();
			} else {
				return "";
			}
		}
	}
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/web/index")
	public String loginOp(@RequestParam(value = "userId", required = true) Integer userId,
			HttpServletRequest request) {
		request.setAttribute("user", loginUsersService.selectByPrimaryKey(userId));
		return "/app";
	}
}
