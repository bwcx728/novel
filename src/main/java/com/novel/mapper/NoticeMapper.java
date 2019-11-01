package com.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.novel.pojo.Notice;
import com.novel.pojo.PageInfo;

/**
 * 公告相关的sql
 * @author 清风
 * @date 2019年9月27日 下午9:09:32   
 */
public interface NoticeMapper {
//	@Select("select * from t_notice")
//	public List<Notice> findAll();
	
	@Select("select * from t_notice limit #{pageStart},#{pageSize}")
	public List<Notice> selPageInfo(PageInfo pageInfo);
	@Select("select count(*) from t_notice")
	public int selCount();

	@Insert("insert into t_notice(content) values(#{content})")
	public int AddNotice(Notice notice);
	@Delete("delete from t_notice where id=#{id}")
	public int delNotice(int id);
	@Update("update t_notice set content=#{content} where id=#{id}")
	public int updNotice(Notice notice);
	@Select("select * from t_notice where id=#{id}")
	public Notice findNotice(int id);
	
	@Select("select * from t_notice order by releaseTime desc limit 10")
	public List<Notice> findTenNoticeByTime();
}
