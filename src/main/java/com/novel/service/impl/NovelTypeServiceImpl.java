package com.novel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.mapper.NovelTypeMapper;
import com.novel.pojo.NovelType;
import com.novel.service.NovelTypeService;

/**
 * @author ���
 * @date 2019��9��24�� ����12:13:03   
 */
@Service
public class NovelTypeServiceImpl implements NovelTypeService{
	@Autowired
	private NovelTypeMapper novelTypeMapper;
	@Override
	public List<NovelType> selAll() {
		return novelTypeMapper.selAll();
	}
	@Override
	public NovelType selById(int id) {
		return novelTypeMapper.selById(id);
	}
	@Override
	public NovelType selByName(String name) {
		return novelTypeMapper.selByName(name);
	}


	
	

}
