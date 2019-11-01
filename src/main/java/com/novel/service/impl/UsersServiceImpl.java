package com.novel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.mapper.BookshelfMapper;
import com.novel.mapper.UsersMapper;
import com.novel.pojo.Bookshelf;
import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;
import com.novel.service.UsersService;
import com.novel.util.PageUtil;

/**
 * @author 清风
 * @date 2019年9月24日 下午12:13:09   
 */
@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private BookshelfMapper bookshelfMapper;
	@Override
	public Users selByUsers(Users users) {
		Users user = usersMapper.selByUsers(users);
		if(user!=null) {
			//判断用户是否拥有书架，没有为用户创建一个默认的书架
			Bookshelf bookshelf = bookshelfMapper.selByUid(user.getId());
			if(bookshelf==null) {
				bookshelf = new Bookshelf();
				bookshelf.setName("默认分组");
				bookshelf.setUid(user.getId());
				bookshelfMapper.insBookshelf(bookshelf);
				//再次查询，将书架存入user对象
				user = usersMapper.selByUsers(users);
				//查出第一个书架
				bookshelf = bookshelfMapper.selByUid(user.getId());
			}
			//存入第一个书架Id
			user.setBookshelfId(bookshelf.getId());
			return user;
		}
		return null;
	}
	@Override
	public int addByUsers(Users users) {
		return usersMapper.insByUsers(users);
	}
	@Override
	public int delById(int id) {
		return usersMapper.delById(id);
	}
	
	@Override
	public Users selById(int id) {
		return usersMapper.selById(id);
	}
	@Override
	public int updByUsers(Users users) {
		//查询修改前的信息
		Users user = selById(users.getId());
		//若没修改密码，将之前的密码赋值
		if(users.getPassword()==null||users.getPassword().equals("")) {
			users.setPassword(user.getPassword());
		}
		return usersMapper.updByUsers(users);
	}

//	@Override
//	public List<Users> selAll() {
//		return usersMapper.selAll();
//	}
	@Override
	public int selCount() {
		return usersMapper.selCount();
	}
	@Override
	public List<Users> selPageInfo(PageInfo pageInfo) {
		pageInfo = PageUtil.getPageInfo(pageInfo);
		return usersMapper.selPageInfo(pageInfo);
	}
}
