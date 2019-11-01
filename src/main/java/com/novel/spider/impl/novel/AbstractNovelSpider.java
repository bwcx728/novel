package com.novel.spider.impl.novel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.novel.pojo.Novel;
import com.novel.spider.impl.AbstractSpider;
import com.novel.spider.interfaces.INovelSpider;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * һ�������С˵�б�ץȡ���Ѿ�ʵ���˽���trԪ�صķ���
 * @author ���
 * @date 2019��9��15�� ����7:59:23   
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {
	protected Element nextPageElement;
	/** ��һҳ��url */
	protected String nextPage;
	/**
	 * Ĭ�ϵ�ץȡ���� �����᳢��{@link INovelSpider#MAX_TRY_TIMES}������
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url) throws Exception {
		return getsTr(url,INovelSpider.MAX_TRY_TIMES);
	}
	/**
	 * ��ָ����������ʽȥ������Ӧ��ҳ
	 * @param url
	 * @param maxTryTimes ���Ϊnull����Ĭ��Ϊ{@link INovelSpider#MAX_TRY_TIMES}
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url,Integer maxTryTimes) throws Exception {
		maxTryTimes = maxTryTimes == null? INovelSpider.MAX_TRY_TIMES:maxTryTimes;
		 Elements trs = null;
		 for (int i = 0; i < maxTryTimes; i++) {
			try {
				String result = super.crawl(url);
				Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
				String novelSelector = context.get("novel-selector");
				if(novelSelector == null) {
					throw new RuntimeException("url="+url+"Ŀǰ��֧��ץȡС˵�б�");
				}
				Document doc = Jsoup.parse(result);
				doc.setBaseUri(url);
				trs = doc.select(novelSelector);
				
				String nextPageSelector = context.get("novel-next-page-selector");
				//�ж�С˵�б��Ƿ�����һҳ
				if (nextPageSelector != null) {
					Elements nextPageElements = doc.select(nextPageSelector);
					nextPageElement = nextPageElements == null ? null : nextPageElements.first();
					
					if (nextPageElement != null) {
						nextPage = nextPageElement.absUrl("href");
					} else {
						nextPage = "";
					}
				}
				return trs;
			} catch (Exception e) {
			
			}
		}
		 throw new RuntimeException(url+"������"+maxTryTimes+"��");
	}
	@Override
	public boolean hasNext() {
		return !nextPage.isEmpty();
	}

	@Override
	public String next() {
		return nextPage;
	}
	/**
	 * ��ȡ����С˵ �е�С˵���͡���顢����
	 */
	@Override
	public Map<String, String> getNovelInfo(String url) {
		Map<String,String> map = new HashMap<>();
		try {
			String result = super.crawl(url);
			Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			Document doc = Jsoup.parse(result);
			String detailType = context.get("novel-detail-type-selector");
			String type = doc.select(detailType).text();
			//����С˵�� > ����ħ�� > ���������翪ʼ�����½��б�
			String[] split = type.split("\\s");
			// /s��ʾ�ո� ��split ����ָ�
			/*  ����С˵��
				>
				����ħ��
				>
				���������翪ʼ�����½��б�
			 */
			type = split[2];
//			System.out.println(type);
			map.put("type", type);
			String dateilCover = context.get("novel-detatil-cover-selector");
			Element first = doc.select(dateilCover).first();
			//<img src="/files/article/image/146/146730/146730s.jpg" width="120" height="150" onerror="this.src='/images/no_photo.jpg'">
			String[] split2 = first.toString().split("\\\"");
			String cover  = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("url")+split2[1];
			map.put("cover", cover);
//			System.out.println(cover);
			String dateilIntroduce = context.get("novel-detatil-introduce-selector");
			String introduce = doc.select(dateilIntroduce).first().text();
			//��ȡ���ǰ100���ַ��������Ļ���...���
			map.put("introduce", StringUtils.abbreviate(introduce,100));
//			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		nextPage = firstPage;
		return new NovelIterator(maxTryTimes);
	}
	
	@Override
	public int getPageCount(String url) {
		try {
			String result = super.crawl(url);
			Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			Document doc = Jsoup.parse(result);
			return Integer.parseInt(doc.select(context.get("novel-pageCount-selector")).text());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return 0;
		
	}
	
	/**
	 * һ����������ר�����ڶ�С˵��վ�����б�ץȡ
	 * 
	 * @author ���
	 * @date 2019��9��16�� ����11:07:55   
	 */
	private class NovelIterator implements Iterator<List<Novel>> {
		private Integer maxTryTimes;
		public NovelIterator(Integer maxTryTimes) {
			this.maxTryTimes = maxTryTimes;
		}
		@Override
		public boolean hasNext() {
			return AbstractNovelSpider.this.hasNext();
		}
		@Override
		public List<Novel> next() {
			List<Novel> novels = getNovel(nextPage, maxTryTimes);
			return novels;
		}
	}
}
