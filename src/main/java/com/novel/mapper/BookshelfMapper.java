package com.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;

public interface BookshelfMapper {
	
	/**
	 * 查询用户的所有书架
	 * @param uid 用户id
	 * @return
	 */
	public List<Bookshelf> findAllBookshelf(int uid);
	/**
	 * 	用户的按照指定的id查询书架
	 * @param uid 用户id
	 * @return
	 */
	public Bookshelf findBookshelfByBid(@Param("uid")int uid,@Param("bid")int bid);
	
	/**
	 * 查询书架的所有小说
	 * @param bid 书架id
	 * @return
	 */
	public List<Novel> findAllNovel(int bid);
	
	
	@Insert("insert into t_bookshelf values(default,#{name},#{uid})")
	public int insBookshelf(Bookshelf bookshelf);
	@Delete("delete from t_bookshelf where id=#{id}")
	public int delBookshelf(int id);
	@Select("select * from t_bookshelf where id =#{id}")
	public Bookshelf selById(int id);
	@Update("update t_bookshelf set bname=#{name} where id=#{id}")
	public int updBookshelf(Bookshelf bookshelf);
	/** 查询书架的首个id	 */
	@Select("select * from t_bookshelf where uid =#{uid} limit 1")
	public Bookshelf selByUid(int uid);
	
	@Insert("insert into t_nb values(default,#{bid},#{nid})")
	public int addNovelByBidNid(@Param("bid")int bid,@Param("nid")int nid);
	
	@Delete("delete from t_nb where bid=#{bid} and nid=#{nid}")
	public int delNovelByBidNid(@Param("bid")int bid,@Param("nid")int nid);
	
	/**
	 * 	查询小说是否在用户的书架中
	 * @param nid
	 * @param uid
	 * @return
	 */
	@Select("select count(n.id)\r\n" + 
			"from users u LEFT JOIN t_bookshelf b on b.uid=u.id \r\n" + 
			"	LEFT JOIN t_nb nb on nb.bid=b.id LEFT JOIN t_novel n on n.id=nb.nid\r\n" + 
			"where u.id=#{uid} and n.id=#{nid}")
	public int findNovelIsBookshelf(@Param("nid")int nid,@Param("uid")int uid);
}
