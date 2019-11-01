package com.novel.spider.util;

import com.novel.spider.impl.novel.BxwxNovelSpider;
import com.novel.spider.impl.novel.DdxsNovelSpider;
import com.novel.spider.interfaces.INovelSpider;

/**
 * 小说章节爬虫工厂
 * @author 清风
 * @date 2019年9月24日 下午12:15:08   
 */
public final class NovelSpiderFactory {

	private NovelSpiderFactory() {}
	public static INovelSpider getNovelSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch(novelSiteEnum) {
		case BiXiaWenXue:return new BxwxNovelSpider();
		case DingDianXiaoShuo:return new DdxsNovelSpider();
		default:throw new RuntimeException(url+"暂时不被支持");
		}
	}
}
