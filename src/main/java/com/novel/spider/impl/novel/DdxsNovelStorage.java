package com.novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.novel.pojo.Novel;
import com.novel.spider.interfaces.INovelSpider;
import com.novel.spider.util.NovelSpiderFactory;


/**
 * @author ���
 * @date 2019��9��14�� ����12:14:09   
 */
public class DdxsNovelStorage {
	/**
	 * ��ҳ��
	 */
	private int pageCount;
	/**�����̸߳���*/
	private int threadNum;
	/**
	 * 
	 * ��С˵�б�������ݿ�
	 * @param url С˵�б��һҳ
	 * @param pageNum ÿ���߳����ص�ҳ��
	 */
	public void process(String url,int pageNum) {
		Map<Integer,String> tasks = new TreeMap<>();
		AbstractNovelSpider d = new DdxsNovelSpider();
		pageCount = d.getPageCount(url);
		threadNum = pageCount % pageNum == 0 ? pageCount/pageNum : pageCount/pageNum + 1;
		for(int i = 1 ; i < threadNum ; i++) {
			tasks.put(i, "https://www.23wx.cc/quanben/"+(1+pageNum*(i-1)));
		}
		ExecutorService service = Executors.newFixedThreadPool(tasks.size());
		List<Future<String>> futures = new ArrayList<>(tasks.size());
		for(Entry<Integer, String> entry : tasks.entrySet()) {
			final Integer key = entry.getKey();
			final String value = entry.getValue();
			futures.add(service.submit(new Callable<String> () {
				@Override
				public String call() throws Exception {
					INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
					Iterator<List<Novel>> iterator = spider.iterator(value, 10);
					List<Novel> novels = null;
					while(iterator.hasNext()) {
						System.err.println("��ʼץȡurl��"+value);
						novels = iterator.next();
					}
					//�������
					return null;
				}
			}));
		}
		service.shutdown();
		for (Future<String> future : futures) {
			try {
				System.out.println("ץȡ[" + future.get() + "]������");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
