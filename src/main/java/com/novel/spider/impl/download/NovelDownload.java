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
 * ʵ�ֶ��߳�������վ��С˵
 * 1.�õ�����վ�Ļ���С˵�������½ڣ��½��б�
 * 2.ͨ�����㣬����Щ�½ڷ����ָ���������̣߳�������ȥ������Ȼ�󱣴浽�ı��ļ���
 * 3.֪ͨ���̣߳�����Щ��ɢ��С�ļ��ϲ���һ�����ļ��������Щ��Ƭ��С�ļ�ɾ������
 * @author ���
 * @date 2019��9��14�� ����1:37:55   
 */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url,Configuration config) {
		IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = spider.getChapters(url);
		
		//ĳ���߳��������֮����ø������̣߳����غ���
		//�����߳�������Ϻ����߳̽����еĺϲ�
		int size = config.getSize();
		//Math.ceil(double) 10 10.1->11 -10.1->10
		//����߳�����
		//chapters.size()С˵�½�������
		int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
		Map<String , List<Chapter>> downloadTackAlloc = new HashMap<>();
		for (int i = 0; i < maxThreadSize; i++) {
			//i=0 0-99
			//i=1 100-199
			//...
			//���ܹ���2053��ʱ
			//i = 19 1900-1999
			//i=20 2000-2052
			int fromIndex = i * config.getSize();
			int toIndex = i == maxThreadSize -1 ? chapters.size() : i * config.getSize() + config.getSize();
			//subList �Ӽ��� ���󲻰���
			downloadTackAlloc.put(fromIndex+"-"+toIndex, chapters.subList(fromIndex, toIndex));
		}
		//�̳߳� newFixedThreadPool�����ù̶��������̳߳�
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet = downloadTackAlloc.keySet();
		List<Future<String>> tasks = new ArrayList<>();
		
		String savePath = config.getPath()+"/"+NovelSiteEnum.getEnumByUrl(url).getUrl();
		new File(savePath).mkdirs();
		for (String key : keySet) {
			tasks.add(service.submit(new DownloadCallable(downloadTackAlloc.get(key),savePath+"/"+key+".txt",config.getTryTimes())));
		}
		//�ر��̳߳�
		service.shutdown();
		for (Future<String> future : tasks) {
			try {
				System.out.println(future.get()+",������ɣ�");
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
						System.err.println("���Ե�[" + (i + 1) + "/" + tryTimes + "]������ʧ���ˣ�");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return path;
	}
}
