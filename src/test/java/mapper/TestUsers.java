package mapper;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.novel.mapper.UsersMapper;
import com.novel.pojo.Users;

//配置spring和junit整合，这样junit在启动时就会加载spring容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring/applicationContext.xml" })
public class TestUsers {
	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}
	@Autowired
	private UsersMapper usersMapper;
	@Test
	public void find() {
		Users users = new Users();
		users.setUsername("admin");
		users.setPassword("123123");
		users = usersMapper.selByUsers(users);
		System.out.println(users);
	}
}
