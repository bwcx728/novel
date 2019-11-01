package com.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.novel.pojo.Novel;
import com.novel.pojo.PageInfo;

/**
 * 	小说 的 CRUD 操作接口 实现批量删除 与 模糊查询 分页
 * @author 清风
 * @date 2019年9月20日 上午9:44:16   
 */
public interface NovelMapper {
	
	public int batchInsertNovel(@Param("novels") List<Novel> novels);

	public List<Novel> findAllNovel();

	public List<Novel> findNovelByType(String type);
	
	public List<Novel> selByPageInfo(PageInfo pageInfo);
	public int fuzzyQueryCount(PageInfo pageInfo);
	
	@Select("select count(*) from t_novel")
	public int selCount();

	@Insert("insert into t_novel(name,author,url) values(?,?,?)")
	public int insert(Novel novel);

	@Delete("delete from t_novel where id = #{id} ")
	public int deleteById(int id);

	public Novel findById(int id);

	public int batchDeleteNovel(@Param("checkId") Integer[] checkId);
	
	
}
