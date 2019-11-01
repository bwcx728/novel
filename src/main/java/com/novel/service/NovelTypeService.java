package com.novel.service;

import java.util.List;

import com.novel.pojo.NovelType;

/**
 * С˵���� service
 * @author ���
 * @date 2019��9��23�� ����8:32:05   
 */
public interface NovelTypeService {
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<NovelType> selAll();
	/**
	 * ��id��ѯ ����
	 * @param id
	 * @return
	 */
	public NovelType selById(int id);
	/**
	 * ��name��ѯ ����
	 * @param name
	 * @return
	 */
	public NovelType selByName(String name);
}
