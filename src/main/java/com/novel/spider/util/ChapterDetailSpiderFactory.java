package com.novel.spider.util;

import com.novel.spider.impl.chapter.DefualtChapterDetailSpider;
import com.novel.spider.interfaces.IChapterDetailSpider;

/**
 * 生产IChapterDetailSpider的工厂
 * @author 清风
 * @date 2019年9月14日 下午2:02:58   
 */
public final class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory() {}
	/**
	 * 通过给定的url,返回一个实现了IChapterDetailSpider接口的实现类
	 * @param url 章节内容
	 * @return
	 */
	public static IChapterDetailSpider getChapterDetailSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterDetailSpider spider = null;
		switch(novelSiteEnum) {
		case BiXiaWenXue:
		case DingDianXiaoShuo:
		case BiQuGe:
			spider = new DefualtChapterDetailSpider();break;
		}
		return spider;
	}
}
