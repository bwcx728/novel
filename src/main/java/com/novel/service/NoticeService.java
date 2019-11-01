package com.novel.service;

import java.util.List;

import com.novel.pojo.Notice;
import com.novel.pojo.PageInfo;

public interface NoticeService {
	
	/**
	 * ��ѯ���й��� limit��ҳ
	 * @return
	 */
	public List<Notice> selPageInfo(PageInfo pageInfo);
	
	/**
	 * ��������
	 * @return
	 */
	public int selCount();
	
	/**
	 * ���һ������
	 * @param notice
	 * @return
	 */
	public int AddNotice(Notice notice);
	/**
	 * ɾ��һ������
	 * @param id
	 * @return
	 */
	public int delNotice(int id);
	
	/**
	 * �޸�һ������
	 * @param notice
	 * @return
	 */
	public int updNotice(Notice notice);
	
	/**
	 * ��ѯһ������
	 * @param id 
	 * @return
	 */
	public Notice findNotice(int id);
	
	/**
	 * 
	 * ��ѯ���·�����ʮ������
	 * @return
	 */
	public List<Notice> findTenNoticeByTime();
}
