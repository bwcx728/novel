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
 * 顶点小说网站的书籍列表爬取
 * @author 清风
 * @date 2019年9月16日 上午9:43:16   
 */
public class DdxsNovelSpider  extends AbstractNovelSpider {
	public DdxsNovelSpider() {
	}

	@Override
	public List<Novel> getNovel(String url, Integer maxTryTimes) {
		List<Novel> novels= new ArrayList<Novel>();
		try {
			Elements trs = super.getsTr(url,10);
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
				novel.setUrl(tds.first().getElementsByTag("a").first().absUrl("href"));
				//最新章节
				novel.setLastUpdateChapter(tds.get(1).text());
				//最新章节链接
				novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				//作者
				novel.setAuthor(tds.get(2).text());
				//字数
				novel.setWordage(tds.get(3).text());
				//更新时间
				novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));
				//状态
				novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));
				//网站id NovelSiteEnum枚举中ID
				novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
				Map<String, String> novelInfo = super.getNovelInfo(novel.getUrl());
				//网站的介绍
				novel.setIntroduce(novelInfo.get("introduce"));
				//网站的封面路径
				novel.setCover(novelInfo.get("cover"));
				//网站的类型
				novel.setType(novelInfo.get("type"));
				novels.add(novel);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return novels;
	}

}
