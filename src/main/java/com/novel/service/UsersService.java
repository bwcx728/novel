package com.novel.service;


import java.util.List;

import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;


/**
 * 	用户相关的业务逻辑操作
 * @author 清风
 * @date 2019年9月20日 上午11:12:34   
 */
public interface UsersService {
	/** 管理员ID 对应user表中rid字段*/
	public static final int ADMIN_ID = 1;
	/** 用户ID 对应user表中rid字段 */
	public static final int USER_ID = 2;
	/**
	 * 	用户登录 
	 * 	管理员进入后台
	 * 	会员返回前端页面
	 * @param users
	 * @return
	 */
	Users selByUsers(Users users);
	/**
	 * 	查询所有用户
	 * @return
	 */
//	List<Users> selAll();
	
	/**
	 * 查询用户，实现分页
	 * @param pageInfo
	 * @return
	 */
	List<Users> selPageInfo(PageInfo pageInfo);
	
	
	/**
	 * 查询用户数量
	 * @return
	 */
	int selCount();
	
	//用户的CRUD
	int addByUsers(Users users);
	int delById(int id);
	Users selById(int id);
	int updByUsers(Users users);	
	
}
