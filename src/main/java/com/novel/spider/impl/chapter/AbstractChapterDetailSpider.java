package com.novel.spider.impl.chapter;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.novel.spider.entitys.ChapterDetail;
import com.novel.spider.impl.AbstractSpider;
import com.novel.spider.interfaces.IChapterDetailSpider;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * @author 清风
 * @date 2019年9月21日 上午10:03:28   
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {
	
	@Override
	public ChapterDetail getChapterDetail(String url) {
		ChapterDetail detail = new ChapterDetail();
		try {
			//抓取网站内容
			String result = super.crawl(url);
			result = result.replace("&nbsp;", "  ").replace("<br/>", "${line}").replace("<br />", "${line}");
			//Jsoup不支持特殊字符解析
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			//通过url获得对应xml中 <site>
			Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			
			//拿标题内容
			String titleSelector = contexts.get("chapter-datail-title-selector");
			String[] splits =titleSelector.split(",");
			splits = parseSelector(splits);
			//先通过选择器获取对应的节点，在通过下标获取对应的内容
			detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//拿章节内容
			String contentSelector = contexts.get("chapter-datail-content-selector");
			//first() 判断是否为空，不为空返回get(0)
			detail.setContent(doc.select(contentSelector).first().text());	
			detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "<br/>"));	
		
			//拿前一章地址
			String prevSelector = contexts.get("chapter-datail-prev-selector");
			splits = prevSelector.split(",");
			splits = parseSelector(splits);
			//获取绝对地址
			detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//拿后一章地址
			String nextSelector = contexts.get("chapter-datail-next-selector");
			splits = nextSelector.split(",");
			splits = parseSelector(splits);
			//获取绝对地址
			detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detail;
	}
	private String[] parseSelector(String[] splits) {
		String[] newSplits = new String[2];
		if(splits.length == 1) {
			newSplits[0] = splits[0];
			newSplits[1] = "0";
			return newSplits;
		}else {
			return splits;
		}
		
	}
}
