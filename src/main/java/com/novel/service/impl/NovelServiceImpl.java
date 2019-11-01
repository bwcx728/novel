package com.novel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.mapper.NovelMapper;
import com.novel.pojo.Novel;
import com.novel.pojo.PageInfo;
import com.novel.service.NovelService;
import com.novel.util.PageUtil;

/**
 * 小说业务层实现
 * @author 清风
 * @date 2019年9月24日 下午12:12:14   
 */
@Service
public class NovelServiceImpl implements NovelService {
	@Autowired
	private NovelMapper novelMapper;

	@Override
	public int batchInsertNovel(List<Novel> novels) {
		return novelMapper.batchInsertNovel(novels);
	}
	@Override
	public List<Novel> findAllNovel() {
		return novelMapper.findAllNovel();
	}

	@Override
	public Novel findById(int id) {
		return novelMapper.findById(id);
	}
	@Override
	public List<Novel> selByPageInfo(PageInfo pageInfo) {
		pageInfo = PageUtil.getPageInfo(pageInfo);
		return novelMapper.selByPageInfo(pageInfo);
	}
	@Override
	public int selCount() {
		return novelMapper.selCount();
	}
	@Override
	public int insert(Novel novel) {
		return 0;
	}
	@Override
	public int deleteById(int id) {
		return novelMapper.deleteById(id);
	}

	@Override
	public int batchDeleteNovel(Integer[] checkId) {
		return novelMapper.batchDeleteNovel(checkId);
	}

	@Override
	public int fuzzyQueryCount(PageInfo pageInfo) {
		//先查总数在查小说集合
		Map<String,String> map = (Map<String, String>) pageInfo.getMap();
		if(map.get("fuzzyQuery")!=null&&!map.get("fuzzyQuery").equals("")) {
			String fuzzyQuery = new StringBuilder("%").append(map.get("fuzzyQuery")).append("%").toString();
			map.put("fuzzyQuery",fuzzyQuery);
		}
		pageInfo.setMap(map);
		return novelMapper.fuzzyQueryCount(pageInfo);
	}

	@Override
	public List<Novel> findNovelByType(String type) {
		return novelMapper.findNovelByType(type);
	}
}
