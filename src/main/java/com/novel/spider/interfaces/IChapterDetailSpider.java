package com.novel.spider.interfaces;

import com.novel.spider.entitys.ChapterDetail;

/**
 * �½���������ӿ�
 * @author ���
 * @date 2019��9��15�� ����12:14:26   
 */
public interface IChapterDetailSpider {
	
	/**
	 * 	����һ��url���Ҿ͸���һ����Ӧ��վ���½�����ʵ��
	 * @param url
	 * @return
	 */
	public ChapterDetail getChapterDetail(String url);
}
