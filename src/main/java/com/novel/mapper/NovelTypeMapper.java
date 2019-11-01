package com.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.novel.pojo.NovelType;

/**
 * С˵����mapper
 * @author ���
 * @date 2019��9��24�� ����12:10:13   
 */
public interface NovelTypeMapper {
	
	@Select("select * from t_type")
	List<NovelType> selAll();
	
	@Select("select * from t_type where id=#{id}")
	NovelType selById(@Param("id") int id);
	
	@Select("select * from t_type where name=#{0}")
	NovelType selByName(String name);
}
