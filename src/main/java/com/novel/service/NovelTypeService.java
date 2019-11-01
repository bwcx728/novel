package com.novel.service;

import java.util.List;

import com.novel.pojo.NovelType;

/**
 * 小说类型 service
 * @author 清风
 * @date 2019年9月23日 下午8:32:05   
 */
public interface NovelTypeService {
	
	/**
	 * 查询所有类型
	 * @return
	 */
	public List<NovelType> selAll();
	/**
	 * 按id查询 类型
	 * @param id
	 * @return
	 */
	public NovelType selById(int id);
	/**
	 * 按name查询 类型
	 * @param name
	 * @return
	 */
	public NovelType selByName(String name);
}
