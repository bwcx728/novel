package com.novel.spider.impl.chapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * @author 清风
 * @date 2019年9月14日 下午12:13:15   
 */
public class BxwxChapterSpider extends AbstractChapterSpider {
	public List<Chapter> getsChapter(String url){
		System.out.println("BxwxChapterSpider");
		List<Chapter> chapters = super.getChapters(url);
		Collections.sort(chapters,new Comparator<Chapter>() {
			@Override
			public int compare(Chapter o1, Chapter o2) {
				String o1Url = o1.getUrl();
				String o2Url = o2.getUrl();
				String o1Index = o1Url.substring(o1Url.lastIndexOf('/')+1,o1Url.lastIndexOf('.'));
				String o2Index = o1Url.substring(o2Url.lastIndexOf('/')+1,o2Url.lastIndexOf('.'));
				System.out.println(o1Index+"---"+o2Index);
				return o1Index.compareTo(o2Index);
			}
		});
		return chapters;
	}
}
