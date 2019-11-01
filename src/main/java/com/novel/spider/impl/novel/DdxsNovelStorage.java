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
 * @author 清风
 * @date 2019年9月14日 下午12:14:09   
 */
public class DdxsNovelStorage {
	/**
	 * 总页数
	 */
	private int pageCount;
	/**开启线程个数*/
	private int threadNum;
	/**
	 * 
	 * 将小说列表存入数据库
	 * @param url 小说列表第一页
	 * @param pageNum 每个线程下载的页数
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
						System.err.println("开始抓取url："+value);
						novels = iterator.next();
					}
					//插入语句
					return null;
				}
			}));
		}
		service.shutdown();
		for (Future<String> future : futures) {
			try {
				System.out.println("抓取[" + future.get() + "]结束！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
