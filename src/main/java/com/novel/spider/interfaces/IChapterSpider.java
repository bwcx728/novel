package com.novel.spider.interfaces;

import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * 	С˵�½��б�url�ӿ�
 * @author ���
 * @date 2019��7��8�� ����7:26:18   
 */
public interface IChapterSpider {
	
	/**
	 * 	����һ��������url���ӣ����Ǿ͸��㷵�������½��б�
	 * @param url
	 * @return
	 */
	public List<Chapter> getChapters(String url);
	
}
