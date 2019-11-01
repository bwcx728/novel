package com.novel.service;


import java.util.List;

import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;


/**
 * 	�û���ص�ҵ���߼�����
 * @author ���
 * @date 2019��9��20�� ����11:12:34   
 */
public interface UsersService {
	/** ����ԱID ��Ӧuser����rid�ֶ�*/
	public static final int ADMIN_ID = 1;
	/** �û�ID ��Ӧuser����rid�ֶ� */
	public static final int USER_ID = 2;
	/**
	 * 	�û���¼ 
	 * 	����Ա�����̨
	 * 	��Ա����ǰ��ҳ��
	 * @param users
	 * @return
	 */
	Users selByUsers(Users users);
	/**
	 * 	��ѯ�����û�
	 * @return
	 */
//	List<Users> selAll();
	
	/**
	 * ��ѯ�û���ʵ�ַ�ҳ
	 * @param pageInfo
	 * @return
	 */
	List<Users> selPageInfo(PageInfo pageInfo);
	
	
	/**
	 * ��ѯ�û�����
	 * @return
	 */
	int selCount();
	
	//�û���CRUD
	int addByUsers(Users users);
	int delById(int id);
	Users selById(int id);
	int updByUsers(Users users);	
	
}
