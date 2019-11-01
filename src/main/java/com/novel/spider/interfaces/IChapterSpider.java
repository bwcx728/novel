package com.novel.spider.interfaces;

import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * 	小说章节列表url接口
 * @author 清风
 * @date 2019年7月8日 下午7:26:18   
 */
public interface IChapterSpider {
	
	/**
	 * 	给我一个完整的url链接，我们就给你返回所有章节列表
	 * @param url
	 * @return
	 */
	public List<Chapter> getChapters(String url);
	
}
