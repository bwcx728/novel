package com.novel.spider.util;

import com.novel.spider.impl.chapter.BxwxChapterSpider;
import com.novel.spider.impl.chapter.DefaultChapterSpider;
import com.novel.spider.interfaces.IChapterSpider;

/**
 * ����IChapterSpider����
 * @author ���
 * @date 2019��9��14�� ����1:52:17   
 */
public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}
		/**
		 * ͨ��������url,����һ��ʵ����IChapterSpider�ӿڵ�ʵ����
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
