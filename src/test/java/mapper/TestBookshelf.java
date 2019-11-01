package mapper;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.novel.mapper.BookshelfMapper;
import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;

//配置spring和junit整合，这样junit在启动时就会加载spring容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
public class TestBookshelf {
	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}
	@Autowired
	private BookshelfMapper bookshelfMapper;
	@Test
	public void findBookshelf() {
		List<Bookshelf> findAllBookshelf = bookshelfMapper.findAllBookshelf(1);
		for (Bookshelf bookshelf : findAllBookshelf) {
			System.out.println(bookshelf);
			List<Novel> novelList = bookshelf.getNovelList();
			for (Novel novel : novelList) {
				System.out.println(novel);
			}
		}
	}
	@Test
	public void findBookshelfByBid() {
		Bookshelf b = bookshelfMapper.findBookshelfByBid(1,7);
		
		System.err.println(b.getNovelList().get(0)==null);
		System.err.println(b);
	}
	
	@Test
	public void findNovel() {
		List<Novel> novelList = bookshelfMapper.findAllNovel(1);
		for (Novel novel : novelList) {
			System.out.println(novel);
		}
	}
	
	@Test
	public void insBookshelf() {
		Bookshelf b = new Bookshelf();
		b.setName("测试");
		b.setUid(1);
		bookshelfMapper.insBookshelf(b);
	}
	@Test
	public void updBookshelf() {
		Bookshelf b = new Bookshelf();
		b.setId(3);
		b.setName("测试");
		b.setUid(1);
		bookshelfMapper.updBookshelf(b);
	}
	
	@Test
	public void selByUid() {
		System.out.println(bookshelfMapper.selByUid(6));
	}
}
