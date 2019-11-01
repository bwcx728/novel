package com.novel.spider.util;

import java.util.ArrayList;
import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * �½ڴ���
 * @author ���
 * @date 2019��9��18�� ����7:18:44   
 */
public class ChapterHandle {
	
	/**
	 *  ʵ������һ��
	 * @param ChapterList �½��б�
	 * @return
	 */
	public static List<List<Chapter>> getChapterLists(List<Chapter> chapterList) {
		// ʵ������һ��
		List<List<Chapter>> lists1 = new ArrayList<>();
		List<Chapter> lists2 = new ArrayList<>();
		for (int i = 0; i < chapterList.size();i++) {
			if(i == chapterList.size()-1) {
				//�ж��Ƿ���������
				if(lists2.size() == 3) {
					lists1.add(lists2);
					lists2 = new ArrayList<>();
				}
				lists2.add(chapterList.get(i));
				lists1.add(lists2);
				break;
			}else if(i % 3 == 0 && i != 0) {
				lists1.add(lists2);
				lists2 = new ArrayList<>();
			}
			lists2.add(chapterList.get(i));
		}
		
		return lists1;
	}
	
	/**
	 * �������12�� 3��һ��
	 * @param chapterList
	 * @return
	 */
	public  static List<List<Chapter>> getNewChapters(List<Chapter> chapterList){
		//Ĭ��Ϊ����12��
		int chapterSize = 12;
		List<Chapter> newChapterList = new ArrayList<>();
		for (int i = chapterSize; i > 0; i--) {
//			System.out.println(chapterList.size() - i+"---"+chapterList.size());
			newChapterList.add(chapterList.get(chapterList.size() - i));
		}
		return getChapterLists(newChapterList);
	}
	
	/**
	 * ���½��б���url���б���,ȡxx.html
	 * ��������С˵�½�url  https://www.23wx.cc/du/97/97330/
	 * ����С˵�½�����url  https://www.23wx.cc/du/97/97330/19730236.html  
	 * ��ȡ 19730236.html  
	 * @param chapterList
	 * @return
	 */
	public static List<Chapter> subUrl(List<Chapter> chapterList){
		for (Chapter chapter : chapterList) {
			String url = chapter.getUrl();
			url = url.substring(url.lastIndexOf("/")+1);
			chapter.setUrl(url);
		}
		return chapterList;
	}
	
	/**
	 * 
	 * ���½�����url���н�ȡ
	 * @param url
	 * @return
	 */
	public static String subUrl(String url) {
		//�ж��Ƿ���Ҫ��ȡ�ַ���
		if(url.length() - url.lastIndexOf("/") > 0) {
			return url.substring(url.lastIndexOf("/")+1);
		}
		return url;
	}
}
