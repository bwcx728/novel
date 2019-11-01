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
 * @author ���
 * @date 2019��9��24�� ����12:13:09   
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
			//�ж��û��Ƿ�ӵ����ܣ�û��Ϊ�û�����һ��Ĭ�ϵ����
			Bookshelf bookshelf = bookshelfMapper.selByUid(user.getId());
			if(bookshelf==null) {
				bookshelf = new Bookshelf();
				bookshelf.setName("Ĭ�Ϸ���");
				bookshelf.setUid(user.getId());
				bookshelfMapper.insBookshelf(bookshelf);
				//�ٴβ�ѯ������ܴ���user����
				user = usersMapper.selByUsers(users);
				//�����һ�����
				bookshelf = bookshelfMapper.selByUid(user.getId());
			}
			//�����һ�����Id
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
		//��ѯ�޸�ǰ����Ϣ
		Users user = selById(users.getId());
		//��û�޸����룬��֮ǰ�����븳ֵ
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
