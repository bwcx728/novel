package com.novel.spider.interfaces;

import com.novel.spider.configuration.Configuration;

/**
 * 小说下载接口
 * @author 清风
 * @date 2019年9月24日 下午12:14:49   
 */
public interface INovelDownload {
	/**
	 * 返回下载文本所在的路径
	 * @param url 这个url是指某本小说的章节列表页面
	 * @param config 
	 * @return
	 */
	public String download(String url,Configuration config);
}
