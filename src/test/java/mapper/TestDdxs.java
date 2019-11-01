package mapper;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.novel.pojo.Novel;
import com.novel.spider.configuration.Configuration;
import com.novel.spider.entitys.Chapter;
import com.novel.spider.entitys.ChapterDetail;
import com.novel.spider.impl.download.NovelDownload;
import com.novel.spider.interfaces.IChapterDetailSpider;
import com.novel.spider.interfaces.IChapterSpider;
import com.novel.spider.interfaces.INovelDownload;
import com.novel.spider.interfaces.INovelSpider;
import com.novel.spider.util.ChapterDetailSpiderFactory;
import com.novel.spider.util.ChapterHandle;
import com.novel.spider.util.ChapterSpiderFactory;
import com.novel.spider.util.NovelSpiderFactory;
import com.novel.spider.util.NovelSpiderUtil;


/**
 * 
 * 顶点小说融合web项目测试
 * @author 清风
 * @date 2019年9月17日 下午1:56:46   
 */
public class TestDdxs {

	/**
	 *
	 * 测试获取章节列表
	 * @throws IOException
	 * 
	 */
	@Test
	public void testGetChapter() throws IOException {
		String url = "http://www.23wx.cc/du/80/80892/"; 
		IChapterSpider iChapterImpl = ChapterSpiderFactory.getChapterSpider(url);
		List<Chapter> chapterList = iChapterImpl.getChapters(url);
		for (Chapter chapter : chapterList) {
			System.out.println(chapter);
		}
	}
	/**
	 * 测试章节处理
	 * @throws IOException
	 */
	@Test
	public void ChapterHandleTest() throws IOException {
		String url = "http://www.23wx.cc/du/80/80892/"; 
		IChapterSpider iChapterImpl = ChapterSpiderFactory.getChapterSpider(url);
		List<Chapter> chapterList = iChapterImpl.getChapters(url);
		List<List<Chapter>> newChapters = ChapterHandle.getNewChapters(chapterList);
		for (List<Chapter> lists : newChapters) {
			for (Chapter chapter : lists) {
				System.out.print(chapter.getTitle()+"  ");
			}
			System.out.println();
		}
	}
	/**
	 * 测试获取章节详细信息
	 */
	@Test
	public void testGetChapterDetail() {
		String url = "https://www.23wx.cc/du/80/80892/13055110.html";
		IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
		System.out.println(spider.getChapterDetail(url));
	}
	
	/**
	 * 测试获取章节内容
	 */
	@Test
	public void testGetChapterContent() {
		String url = "https://www.23wx.cc/du/97/97330/19730236.html";
		IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
		ChapterDetail chapterDetail = spider.getChapterDetail(url);
		System.out.println(chapterDetail.getContent());
	}
	/**
	 * 测试下载小说
	 */
	@Test
	public void testDownload() {
		INovelDownload download = new NovelDownload();
		Configuration config = new Configuration();
		config.setPath("E:/1");
		config.setSize(100);
		System.out.println("下载完毕，文件保存在"+download.download("http://www.23wx.cc/du/80/80892/", config));
	}
	
	/**
	 * 测试合并小说
	 */
	@Test
	public void testMultiFileMerge() {
		NovelSpiderUtil.multiFileMerge("E:/1/23wx.cc", null,true);
	}
	
	/**
	 * 获取顶点小说列表(全本)
	 */
	@Test
	public void testDdxsGetNovel() {
		String url="https://www.23wx.cc/quanben/1";
		INovelSpider spider = NovelSpiderFactory.getNovelSpider(url);
		List<Novel> novels = spider.getNovel(url,10);
		for (Novel novel : novels) {
			System.out.println(novel);
		}
	}
	/**
	 * 迭代抓取所有小说列表数据
	 */
	@Test
	public void testBxwxIterator() {
		String url="https://www.23wx.cc/quanben/1";
		INovelSpider spider = NovelSpiderFactory.getNovelSpider(url);
		Iterator<List<Novel>> iterator = spider.iterator(url, 10);
		while (iterator.hasNext()) {
			List<Novel> novels = iterator.next();
			System.err.println("URL：" + spider.next());
//			for (Novel novel : novels) {
//				System.out.println(novel);
//			}
		}
	}
}
