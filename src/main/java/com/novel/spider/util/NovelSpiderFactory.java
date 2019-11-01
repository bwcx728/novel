package com.novel.spider.util;

import com.novel.spider.impl.novel.BxwxNovelSpider;
import com.novel.spider.impl.novel.DdxsNovelSpider;
import com.novel.spider.interfaces.INovelSpider;

/**
 * С˵�½����湤��
 * @author ���
 * @date 2019��9��24�� ����12:15:08   
 */
public final class NovelSpiderFactory {

	private NovelSpiderFactory() {}
	public static INovelSpider getNovelSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		switch(novelSiteEnum) {
		case BiXiaWenXue:return new BxwxNovelSpider();
		case DingDianXiaoShuo:return new DdxsNovelSpider();
		default:throw new RuntimeException(url+"��ʱ����֧��");
		}
	}
}
