package com.novel.spider.util;

import java.util.ArrayList;
import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * 章节处理
 * @author 清风
 * @date 2019年9月18日 下午7:18:44   
 */
public class ChapterHandle {
	
	/**
	 *  实现三章一行
	 * @param ChapterList 章节列表
	 * @return
	 */
	public static List<List<Chapter>> getChapterLists(List<Chapter> chapterList) {
		// 实现三章一行
		List<List<Chapter>> lists1 = new ArrayList<>();
		List<Chapter> lists2 = new ArrayList<>();
		for (int i = 0; i < chapterList.size();i++) {
			if(i == chapterList.size()-1) {
				//判断是否满足三章
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
	 * 获得最新12章 3章一行
	 * @param chapterList
	 * @return
	 */
	public  static List<List<Chapter>> getNewChapters(List<Chapter> chapterList){
		//默认为最新12章
		int chapterSize = 12;
		List<Chapter> newChapterList = new ArrayList<>();
		for (int i = chapterSize; i > 0; i--) {
//			System.out.println(chapterList.size() - i+"---"+chapterList.size());
			newChapterList.add(chapterList.get(chapterList.size() - i));
		}
		return getChapterLists(newChapterList);
	}
	
	/**
	 * 对章节列表中url进行编码,取xx.html
	 * 例：顶点小说章节url  https://www.23wx.cc/du/97/97330/
	 * 顶点小说章节内容url  https://www.23wx.cc/du/97/97330/19730236.html  
	 * 截取 19730236.html  
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
	 * 对章节详情url进行截取
	 * @param url
	 * @return
	 */
	public static String subUrl(String url) {
		//判断是否需要截取字符串
		if(url.length() - url.lastIndexOf("/") > 0) {
			return url.substring(url.lastIndexOf("/")+1);
		}
		return url;
	}
}
