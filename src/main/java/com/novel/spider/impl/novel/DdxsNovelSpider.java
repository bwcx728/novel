package com.novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.novel.pojo.Novel;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * ����С˵��վ���鼮�б���ȡ
 * @author ���
 * @date 2019��9��16�� ����9:43:16   
 */
public class DdxsNovelSpider  extends AbstractNovelSpider {
	public DdxsNovelSpider() {
	}

	@Override
	public List<Novel> getNovel(String url, Integer maxTryTimes) {
		List<Novel> novels= new ArrayList<Novel>();
		try {
			Elements trs = super.getsTr(url,10);
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
				novel.setUrl(tds.first().getElementsByTag("a").first().absUrl("href"));
				//�����½�
				novel.setLastUpdateChapter(tds.get(1).text());
				//�����½�����
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				//����
				novel.setAuthor(tds.get(2).text());
				//����
				novel.setWordage(tds.get(3).text());
				//����ʱ��
				novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));
				//״̬
				novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
				//��վid NovelSiteEnumö����ID
				novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
				Map<String, String> novelInfo = super.getNovelInfo(novel.getUrl());
				//��վ�Ľ���
				novel.setIntroduce(novelInfo.get("introduce"));
				//��վ�ķ���·��
				novel.setCover(novelInfo.get("cover"));
				//��վ������
				novel.setType(novelInfo.get("type"));
				novels.add(novel);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}

}
