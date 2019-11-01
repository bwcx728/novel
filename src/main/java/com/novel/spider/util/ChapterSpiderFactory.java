package com.novel.spider.util;

import com.novel.spider.impl.chapter.BxwxChapterSpider;
import com.novel.spider.impl.chapter.DefaultChapterSpider;
import com.novel.spider.interfaces.IChapterSpider;

/**
 * 生产IChapterSpider工厂
 * @author 清风
 * @date 2019年9月14日 下午1:52:17   
 */
public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}
		/**
		 * 通过给定的url,返回一个实现了IChapterSpider接口的实现类
		 * @param url
		 * @return
		 */
		public static IChapterSpider getChapterSpider(String url) {
			NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
			IChapterSpider chapterSpider = null;
			switch(novelSiteEnum) {
			case BiXiaWenXue:
				chapterSpider = new BxwxChapterSpider();break;
			case DingDianXiaoShuo:
			case BiQuGe:
				chapterSpider = new DefaultChapterSpider();break;
			}
			return chapterSpider;
		}
}
