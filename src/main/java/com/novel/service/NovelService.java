package com.novel.service;

import java.util.List;

import com.novel.pojo.Novel;
import com.novel.pojo.PageInfo;


/**
 * 	
 * @author 清风
 * @date 2019年9月18日 下午5:52:54   
 */
public interface NovelService {
	/**
	 * 	批量插入小说信息
	 * 
	 * @return
	 */
	public int batchInsertNovel(List<Novel> novels);

	/**
	 * 根据type查询小说
	 * @return
	 */
	public List<Novel> findNovelByType(String type);
	/**
	 * 查询所有小说信息
	 * 
	 * @return
	 */
	List<Novel> findAllNovel();
	
	/**
	 *	 分页显示小说列表
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<Novel> selByPageInfo(PageInfo pageInfo);
	/**
	 * 	查询小说总数
	 * 
	 * @return
	 */
	public int selCount();
	
	/**
	 * 	插入小说
	 * @param novel
	 * @return
	 */
	public int insert(Novel novel);
	
	/**
	 * 	根据ID删除小说
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	
	/**
	 * 根据ID查询小说
	 * @param id
	 * @return
	 */
	public Novel findById(int id);
	
	/**
	 * 	批量删除小说
	 * 
	 * @param checkId 小说id集合
	 * @return
	 */
	public int batchDeleteNovel(Integer[] checkId);
	
	/**	条件查询后小说总数量
	 * @param novel
	 * @return
	 */
	public int fuzzyQueryCount(PageInfo pageInfo);
}
