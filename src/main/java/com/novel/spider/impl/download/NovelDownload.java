package com.novel.spider.impl.download;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.novel.spider.configuration.Configuration;
import com.novel.spider.entitys.Chapter;
import com.novel.spider.entitys.ChapterDetail;
import com.novel.spider.interfaces.IChapterDetailSpider;
import com.novel.spider.interfaces.IChapterSpider;
import com.novel.spider.interfaces.INovelDownload;
import com.novel.spider.util.ChapterDetailSpiderFactory;
import com.novel.spider.util.ChapterSpiderFactory;
import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * 实现多线程下载网站的小说
 * 1.拿到该网站的基本小说的所有章节：章节列表
 * 2.通过计算，将这些章节分配给指定数量的线程，让他们去解析，然后保存到文本文件中
 * 3.通知主线程，将这些零散的小文件合并成一个大文件。最后将那些分片的小文件删除掉。
 * @author 清风
 * @date 2019年9月14日 下午1:37:55   
 */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url,Configuration config) {
		IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = spider.getChapters(url);
		
		//某个线程下载完毕之后，你得告诉主线程，下载好了
		//所有线程下载完毕后，主线程将所有的合并
		int size = config.getSize();
		//Math.ceil(double) 10 10.1->11 -10.1->10
		//最大线程数量
		//chapters.size()小说章节总数量
		int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
		Map<String , List<Chapter>> downloadTackAlloc = new HashMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			//i=0 0-99
			//i=1 100-199
			//...
			//当总共才2053章时
			//i = 19 1900-1999
			//i=20 2000-2052
			int fromIndex = i * config.getSize();
			int toIndex = i == maxThreadSize -1 ? chapters.size() : i * config.getSize() + config.getSize();
			//subList 子集合 包左不包右
			downloadTackAlloc.put(fromIndex+"-"+toIndex, chapters.subList(fromIndex, toIndex));
		}
		//线程池 newFixedThreadPool可重用固定个数的线程池
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet = downloadTackAlloc.keySet();
		List<Future<String>> tasks = new ArrayList<>();
		
		String savePath = config.getPath()+"/"+NovelSiteEnum.getEnumByUrl(url).getUrl();
		new File(savePath).mkdirs();
		for (String key : keySet) {
			tasks.add(service.submit(new DownloadCallable(downloadTackAlloc.get(key),savePath+"/"+key+".txt",config.getTryTimes())));
		}
		//关闭线程池
		service.shutdown();
		for (Future<String> future : tasks) {
			try {
				System.out.println(future.get()+",下载完成！");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		NovelSpiderUtil.multiFileMerge(savePath,null, true);
		return savePath+"/mrege.txt";
	}

}
class DownloadCallable implements Callable<String>{
	private List<Chapter> chapters;
	private String path;
	private int tryTimes;
	public DownloadCallable(List<Chapter> chapters,String path, int tryTimes) {
		this.path = path;
		this.chapters = chapters;
		this.tryTimes = tryTimes;
	}
	@Override
	public String call() throws Exception {
		try(
				PrintWriter out = new PrintWriter(new File(path),"UTF-8");
				) {
			for (Chapter chapter : chapters) {
				IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
				ChapterDetail detail = null;
				for (int i = 0; i < tryTimes; i++) {
					try {
						detail = spider.getChapterDetail(chapter.getUrl());
						out.print(detail.getTitle());
						out.print(detail.getContent());
						break;
					} catch (Exception e) {
						System.err.println("尝试第[" + (i + 1) + "/" + tryTimes + "]次下载失败了！");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return path;
	}
}
