package com.novel.service;

import java.util.List;

import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;

public interface BookshelfService {
	/**
	 * 查询用户的所有书架
	 * @param uid 用户id
	 * @return
	 */
	public List<Bookshelf> findAllBookshelf(int uid);
	
	/**
	 * 	按照指定的id查询书架
	 * @param uid 用户id
	 * @return
	 */
	public Bookshelf findBookshelfByBid(int uid,int bid);
	
	
	/**
	 * 查询书架的所有小说
	 * @param bid 书架id
	 * @return
	 */
	public List<Novel> findAllNovel(int bid);
	
	public int insBookshelf(Bookshelf bookshelf);
	public int delBookshelf(int id);
	public Bookshelf selById(int id);
	public int updBookshelf(Bookshelf bookshelf);
	public Bookshelf selByUid(int uid);
	
	/**
	 * 	添加小说到书架
	 * @param bid
	 * @param nid
	 * @return
	 */
	public int addNovelByBid(int bid,int nid);
	
	/**
	 * 	从书架中删除小说
	 * @param bid
	 * @param nid
	 * @return
	 */
	public int delNovelByBid(int bid,int nid);
	/**
	 * 	查询小说是否在用户的书架中
	 * @param nid
	 * @param uid
	 * @return
	 */
	public int findNovelIsBookshelf(int nid,int uid);
}
