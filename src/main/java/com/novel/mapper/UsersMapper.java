package com.novel.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;

/**
 * 	用户  持久层
 *	 对用户的CRUD操作
 * @author 清风
 * @date 2019年9月24日 下午12:10:29   
 */
public interface UsersMapper {
	
	
	/**
	 * 用户登录
	 * @param users
	 * @return
	 */
	@Results(value= {
			@Result(id=true,property="id",column="id"),
			@Result(property="name",column="name"),
			@Result(property = "uid",column = "uid"),
			@Result(property = "bookshelfList",column = "id",many=@Many(select="com.novel.mapper.BookshelfMapper.findAllBookshelf")),
	})
	@Select("select * from users where username=#{username} and password=#{password}")
	Users selByUsers(Users users);
	
	
//	@Select("select * from users where rid=2")
//	List<Users> selAll();
	@Select("select * from users where rid=2 limit #{pageStart},#{pageSize}")
	List<Users> selPageInfo(PageInfo pageInfo);
	
	@Select("select count(*) from users where rid=2")
	int selCount();
	
	
	
	
	@Insert("insert into users values(default,#{username},#{password},#{usertel},#{email},#{photo},#{rid})")
	int insByUsers(Users users);
	/** 用了@Param注解不能使用#{0} */
	@Delete("delete from users where id = #{id}")
	int delById(@Param("id") int id);
	@Select("select * from users where id=#{0}")
	Users selById(int id);
	@Update("update users set username=#{username},password=#{password},usertel=#{usertel},email=#{email},photo=#{photo} where id = #{id}")
	int updByUsers(Users users);
	/** 设置用户为管理员 */
	@Update("update users set rid=#{rid} where id =#{id}")
	int setAdmin(Users users);
}
