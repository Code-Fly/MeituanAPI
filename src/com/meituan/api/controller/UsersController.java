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
import com.meituan.common.MeituanConst;
import com.meituan.common.MeituanConst.UserStatus;
import com.meituan.users.entity.LoginUsers;
import com.meituan.users.entity.LoginUsersExample;
import com.meituan.users.service.iface.LoginUsersService;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/web")
public class UsersController extends BaseController {
	@Autowired
	private LoginUsersService loginUsersService;
	/**
	 * 登陆用户名和密码校验，错误返回error，正确返回对应的opID
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/to_login")
	public String loginOp(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password) {
		LoginUsersExample loginUsersExample = new LoginUsersExample();
		loginUsersExample.or().andLogin_idEqualTo(userName);
		List<LoginUsers> tabMasterOps = loginUsersService.selectByExample(loginUsersExample);
		if (null == tabMasterOps || 0 == tabMasterOps.size()) {
			return "NOUSER";
		} else {
			if (MeituanConst.UserStatus.STOP == tabMasterOps.get(0).getStatus()) {
				return "USERSTOP";
			}
			else if (password.equals(tabMasterOps.get(0).getLogin_pass())) {
				return tabMasterOps.get(0).getUser_id().toString();
			} else {
				return "PWDWRONG";
			}
		}
	}
	
	/**
	 * 用户自行修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePwd")
	public String updatePwd(@RequestParam(value = "userId", required = true) int userId,
			@RequestParam(value = "password", required = true) String password) {
		LoginUsers record = new LoginUsers();
		record.setLogin_pass(password);
		record.setUser_id(userId);
		loginUsersService.updateByPrimaryKeySelective(record);
		return SUCCESS;
	}
	
	
	/**
	 * admin 停用 0，启用 1，重置密码2
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUser")
	public String updateUser(@RequestParam(value = "user_id", required = true) int user_id,
			@RequestParam(value = "type", required = true) int type) {
		LoginUsers record = new LoginUsers();
		if(0 == type){
			record.setStatus(UserStatus.STOP);
		} else if (2==type){
			record.setLogin_pass(UserStatus.DEFAULT_PWD);
		} else {
			record.setStatus(UserStatus.START);
		}
		record.setUser_id(user_id);
		loginUsersService.updateByPrimaryKeySelective(record);
		return SUCCESS;
	}
	
	
	/**
	 * admin 停用 0，启用 1，重置密码2
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser")
	public String addUser(@RequestParam(value = "login_id", required = true) String login_id,
			@RequestParam(value = "descption", required = true) String descption,
			@RequestParam(value = "nfdj", required = true) Float nfdj) {
		LoginUsersExample loginUsersExample = new LoginUsersExample();
		loginUsersExample.or().andLogin_idEqualTo(login_id);
		List<LoginUsers> tabMasterOps = loginUsersService.selectByExample(loginUsersExample);
		if (tabMasterOps.size()>0) {
			return "HASUSER";
		} else {
			LoginUsers user = new LoginUsers();
			user.setDescption(descption);
			user.setLogin_id(login_id);
			user.setNfdj(nfdj);
			loginUsersService.insertSelective(user);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList")
	public String userList(HttpServletRequest request) {
		List<LoginUsers> users = loginUsersService.selectByExample(null);
		request.setAttribute("users", users);
		return "/userList";
	}
	
	
	
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String loginOp(@RequestParam(value = "userId", required = true) Integer userId,
			HttpServletRequest request) {
		request.setAttribute("user", loginUsersService.selectByPrimaryKey(userId));
		return "/userInfo";
	}
}
