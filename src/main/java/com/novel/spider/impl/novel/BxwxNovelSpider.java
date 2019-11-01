package com.novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.novel.pojo.Novel;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * ������ѧ��վ���鼮�б���ȡ
 * @author ���
 * @date 2019��9��15�� ����8:16:15   
 */
public class BxwxNovelSpider extends AbstractNovelSpider {
	public BxwxNovelSpider() {}
	@Override
	public List<Novel> getNovel(String url, Integer maxTryTimes) {
		List<Novel> novels= new ArrayList<Novel>();
		try {
			Elements trs = super.getsTr(url,2);
			//ѭ������tr�ڵ㣬��1��ʼ 
			for (int i = 1, size = trs.size(); i < size; i++) {
				//��ȡtr�ڵ�
				Element tr = trs.get(i);
				//��ȡtr�ڵ��µ�td�ڵ㼯��
				Elements tds = tr.getElementsByTag("td");
				//����С˵�Ļ�����Ϣ
				Novel novel = new Novel();
				//����
				novel.setName(tds.first().text());
				String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm", "/").replace("/binfo", "/b");
				novel.setUrl(novelUrl);
				novel.setLastUpdateChapter(tds.get(1).text());
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setAuthor(tds.get(2).text());
				//����
				novel.setWordage(tds.get(3).text());
				novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yyyy-MM-dd"));
				novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
				novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
				novels.add(novel);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}
	
}
