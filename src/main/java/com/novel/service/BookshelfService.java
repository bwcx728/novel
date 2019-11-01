package com.novel.service;

import java.util.List;

import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;

public interface BookshelfService {
	/**
	 * ��ѯ�û����������
	 * @param uid �û�id
	 * @return
	 */
	public List<Bookshelf> findAllBookshelf(int uid);
	
	/**
	 * 	����ָ����id��ѯ���
	 * @param uid �û�id
	 * @return
	 */
	public Bookshelf findBookshelfByBid(int uid,int bid);
	
	
	/**
	 * ��ѯ��ܵ�����С˵
	 * @param bid ���id
	 * @return
	 */
	public List<Novel> findAllNovel(int bid);
	
	public int insBookshelf(Bookshelf bookshelf);
	public int delBookshelf(int id);
	public Bookshelf selById(int id);
	public int updBookshelf(Bookshelf bookshelf);
	public Bookshelf selByUid(int uid);
	
	/**
	 * 	���С˵�����
	 * @param bid
	 * @param nid
	 * @return
	 */
	public int addNovelByBid(int bid,int nid);
	
	/**
	 * 	�������ɾ��С˵
	 * @param bid
	 * @param nid
	 * @return
	 */
	public int delNovelByBid(int bid,int nid);
	/**
	 * 	��ѯС˵�Ƿ����û��������
	 * @param nid
	 * @param uid
	 * @return
	 */
	public int findNovelIsBookshelf(int nid,int uid);
}
