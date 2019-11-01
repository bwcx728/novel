package mapper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.novel.mapper.NovelMapper;
import com.novel.pojo.Novel;
import com.novel.pojo.PageInfo;
import com.novel.spider.interfaces.INovelSpider;
import com.novel.spider.util.NovelSpiderFactory;

/**
 * ������
 * @author ���
 * @date 2019��9��24�� ����12:17:06   
 */
//����spring��junit���ϣ�����junit������ʱ�ͻ����spring����
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�������ļ�
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
public class NovelTest {
	@Autowired
	private NovelMapper novelMapper;

	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	@Test
	public void findAllTest() {
		List<Novel> novels = novelMapper.findAllNovel();
		for (Novel novel : novels) {
			System.out.println(novel);
		}
	}
	@Test
	public void testInsertBatch() {
		String url = "https://www.23wx.cc/quanben/1";
		INovelSpider spider = NovelSpiderFactory.getNovelSpider(url);
		Iterator<List<Novel>> iterator = spider.iterator(url, 10);
		System.err.println(spider.next());
		while (iterator.hasNext()) {
			List<Novel> novels = iterator.next();
			System.out.println(novelMapper.batchInsertNovel(novels));
		}
	}
	@Test
	public void testfindByIdTest() {
		System.out.println(novelMapper.findById(1));
	}

	@Test
	public void fuzzyQueryTest() {
		PageInfo pageInfo = new PageInfo();
		String type = "��������";
		Map<String,String> map = new HashMap<String,String>();
		map.put("queryType", type);
		String fuzzyQuery = new StringBuilder("%").append("��").append("%").toString();
		map.put("fuzzyQuery", fuzzyQuery);
		pageInfo.setMap(map);
		List<Novel> list = novelMapper.selByPageInfo(pageInfo);
		for (Novel novel2 : list) {
			System.out.println(novel2);
		}
	}
	@Test
	public void fuzzyQueryCountTest() {
		PageInfo pageInfo = new PageInfo();
		String type = "��������";
		Map<String,String> map = new HashMap<String,String>();
		map.put("queryType", type);
		String fuzzyQuery = new StringBuilder("%").append("��").append("%").toString();
		map.put("fuzzyQuery", fuzzyQuery);
		pageInfo.setMap(map);
		System.out.println(novelMapper.fuzzyQueryCount(pageInfo));
	}
}
