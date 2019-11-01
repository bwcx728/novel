package com.novel.spider.interfaces;

import com.novel.spider.entitys.ChapterDetail;

/**
 * 章节内容爬虫接口
 * @author 清风
 * @date 2019年9月15日 下午12:14:26   
 */
public interface IChapterDetailSpider {
	
	/**
	 * 	给我一个url，我就给你一个对应网站的章节内容实体
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
}
