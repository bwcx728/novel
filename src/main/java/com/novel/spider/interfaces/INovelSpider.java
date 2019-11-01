package com.novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.novel.pojo.Novel;

/**
 * 
 * 爬取某个站点的小说列表
 * @author 清风
 * @date 2019年9月15日 上午7:39:31   
 */
public interface INovelSpider {
	/**抓取页面的最大尝试次数3 */
	public static final int MAX_TRY_TIMES = 3;
	/**
	 * 
	 * 给我一个小说列表的url，我就给你一堆的小说实体
	 * @param url
	 * @return
	 */
	public List<Novel> getNovel(String url, Integer maxTryTimes);
	
	/**
	 * 判断小说列表中是否还有下一页
	 * @return
	 */
	public boolean hasNext();
	/**
	 * 
	 * 如果小说列表还有下一页，返回下一页url
	 * @return
	 */
	public String next();
	
	/**
	 * 你给我小说列表的第一页url，我给你迭代所有页的小说列表
	 * @param firstPage 第一页url
	 * @return
	 */
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
	
	/**
	 * List<novel> novels = new ArrayList<>();
	 * Iterator<Novel> iterator = novels.iterator();
	 * while(iterator.hasNext()){
	 * 	Novel novel iterator.next();
	 *  System.out.println(novel);
	 * }
	 * */
	/**
	 *	 给我小说详情页url,我给你返回小说封面，小说简介，小说类型
	 * @param url
	 * @return
	 */
	public Map<String,String> getNovelInfo(String url);

	/**
	 * 
	 * @param url 小说列表
	 * @return 总页数
	 */
	public int getPageCount(String url) ;
}
