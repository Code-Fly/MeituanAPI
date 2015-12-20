/**
 * 
 */
package com.meituan.api.controller;

import java.util.List;

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
	public String loginOp(@RequestParam(value = "opNm", required = true) String opNm,
			@RequestParam(value = "opPwd", required = true) String opPwd) {
		LoginUsersExample loginUsersExample = new LoginUsersExample();
		loginUsersExample.or().andLogin_idEqualTo(opNm);
		List<LoginUsers> tabMasterOps = loginUsersService.selectByExample(loginUsersExample);
		if (null == tabMasterOps || 0 == tabMasterOps.size()) {
			return "";
		} else {
			if (opPwd.equals(tabMasterOps.get(0).getLogin_pass())) {
				return tabMasterOps.get(0).getUser_id().toString();
			} else {
				return "";
			}
		}
	}
}
