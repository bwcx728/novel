package com.novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.novel.pojo.Novel;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * 笔下文学网站的书籍列表爬取
 * @author 清风
 * @date 2019年9月15日 上午8:16:15   
 */
public class BxwxNovelSpider extends AbstractNovelSpider {
	public BxwxNovelSpider() {}
	@Override
	public List<Novel> getNovel(String url, Integer maxTryTimes) {
		List<Novel> novels= new ArrayList<Novel>();
		try {
			Elements trs = super.getsTr(url,2);
			//循环遍历tr节点，从1开始 
			for (int i = 1, size = trs.size(); i < size; i++) {
				//获取tr节点
				Element tr = trs.get(i);
				//获取tr节点下的td节点集合
				Elements tds = tr.getElementsByTag("td");
				//存入小说的基本信息
				Novel novel = new Novel();
				//书名
				novel.setName(tds.first().text());
				String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm", "/").replace("/binfo", "/b");
				novel.setUrl(novelUrl);
				novel.setLastUpdateChapter(tds.get(1).text());
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setAuthor(tds.get(2).text());
				//字数
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
