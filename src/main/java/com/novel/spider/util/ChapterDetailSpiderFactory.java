package com.novel.spider.util;

import com.novel.spider.impl.chapter.DefualtChapterDetailSpider;
import com.novel.spider.interfaces.IChapterDetailSpider;

/**
 * ����IChapterDetailSpider�Ĺ���
 * @author ���
 * @date 2019��9��14�� ����2:02:58   
 */
public final class ChapterDetailSpiderFactory {
	private ChapterDetailSpiderFactory() {}
	/**
	 * ͨ��������url,����һ��ʵ����IChapterDetailSpider�ӿڵ�ʵ����
	 * @param url �½�����
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
