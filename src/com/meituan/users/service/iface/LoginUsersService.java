package com.meituan.users.service.iface;

import java.util.List;

import com.meituan.users.entity.LoginUsers;
import com.meituan.users.entity.LoginUsersExample;

public interface LoginUsersService {
	List<LoginUsers> selectByExample(LoginUsersExample example);
	LoginUsers selectByPrimaryKey(Integer user_id);
	int updateByPrimaryKeySelective(LoginUsers record);
	int insertSelective(LoginUsers record);
}
