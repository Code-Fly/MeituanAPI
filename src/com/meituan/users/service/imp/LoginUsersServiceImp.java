package com.meituan.users.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meituan.users.entity.LoginUsers;
import com.meituan.users.entity.LoginUsersExample;
import com.meituan.users.mapper.LoginUsersMapper;
import com.meituan.users.service.iface.LoginUsersService;

@Service("loginUsersService")
public class LoginUsersServiceImp implements LoginUsersService {
	@Autowired
	private LoginUsersMapper loginUsersMapper;

	@Override
	public List<LoginUsers> selectByExample(LoginUsersExample example) {
		return loginUsersMapper.selectByExample(example);
	}

	@Override
	public LoginUsers selectByPrimaryKey(Integer user_id) {
		// TODO Auto-generated method stub
		return loginUsersMapper.selectByPrimaryKey(user_id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(LoginUsers record) {
		return loginUsersMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int insertSelective(LoginUsers record) {
		return loginUsersMapper.insertSelective(record);
	}
}
