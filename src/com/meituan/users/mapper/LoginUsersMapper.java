package com.meituan.users.mapper;

import com.meituan.users.entity.LoginUsers;
import com.meituan.users.entity.LoginUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginUsersMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int countByExample(LoginUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int deleteByExample(LoginUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int deleteByPrimaryKey(Integer user_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int insert(LoginUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int insertSelective(LoginUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	List<LoginUsers> selectByExample(LoginUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	LoginUsers selectByPrimaryKey(Integer user_id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int updateByExampleSelective(@Param("record") LoginUsers record, @Param("example") LoginUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int updateByExample(@Param("record") LoginUsers record, @Param("example") LoginUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int updateByPrimaryKeySelective(LoginUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table login_users
	 * @mbggenerated  Mon Dec 21 19:49:29 CST 2015
	 */
	int updateByPrimaryKey(LoginUsers record);
}