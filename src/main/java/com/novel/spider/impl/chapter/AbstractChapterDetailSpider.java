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
 * @author ���
 * @date 2019��9��21�� ����10:03:28   
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {
	
	@Override
	public ChapterDetail getChapterDetail(String url) {
		ChapterDetail detail = new ChapterDetail();
		try {
			//ץȡ��վ����
			String result = super.crawl(url);
			result = result.replace("&nbsp;", "  ").replace("<br/>", "${line}").replace("<br />", "${line}");
			//Jsoup��֧�������ַ�����
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			//ͨ��url��ö�Ӧxml�� <site>
			Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			
			//�ñ�������
			String titleSelector = contexts.get("chapter-datail-title-selector");
			String[] splits =titleSelector.split(",");
			splits = parseSelector(splits);
			//��ͨ��ѡ������ȡ��Ӧ�Ľڵ㣬��ͨ���±��ȡ��Ӧ������
			detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			
			//���½�����
			String contentSelector = contexts.get("chapter-datail-content-selector");
			//first() �ж��Ƿ�Ϊ�գ���Ϊ�շ���get(0)
			detail.setContent(doc.select(contentSelector).first().text());	
			detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "<br/>"));	
		
			//��ǰһ�µ�ַ
			String prevSelector = contexts.get("chapter-datail-prev-selector");
			splits = prevSelector.split(",");
			splits = parseSelector(splits);
			//��ȡ���Ե�ַ
			detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			
			//�ú�һ�µ�ַ
			String nextSelector = contexts.get("chapter-datail-next-selector");
			splits = nextSelector.split(",");
			splits = parseSelector(splits);
			//��ȡ���Ե�ַ
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
