package com.novel.service;

import java.util.List;

import com.novel.pojo.Novel;
import com.novel.pojo.PageInfo;


/**
 * 	
 * @author ���
 * @date 2019��9��18�� ����5:52:54   
 */
public interface NovelService {
	/**
	 * 	��������С˵��Ϣ
	 * 
	 * @return
	 */
	public int batchInsertNovel(List<Novel> novels);

	/**
	 * ����type��ѯС˵
	 * @return
	 */
	public List<Novel> findNovelByType(String type);
	/**
	 * ��ѯ����С˵��Ϣ
	 * 
	 * @return
	 */
	List<Novel> findAllNovel();
	
	/**
	 *	 ��ҳ��ʾС˵�б�
	 * 
	 * @param pageInfo
	 * @return
	 */
	public List<Novel> selByPageInfo(PageInfo pageInfo);
	/**
	 * 	��ѯС˵����
	 * 
	 * @return
	 */
	public int selCount();
	
	/**
	 * 	����С˵
	 * @param novel
	 * @return
	 */
	public int insert(Novel novel);
	
	/**
	 * 	����IDɾ��С˵
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	
	/**
	 * ����ID��ѯС˵
	 * @param id
	 * @return
	 */
	public Novel findById(int id);
	
	/**
	 * 	����ɾ��С˵
	 * 
	 * @param checkId С˵id����
	 * @return
	 */
	public int batchDeleteNovel(Integer[] checkId);
	
	/**	������ѯ��С˵������
	 * @param novel
	 * @return
	 */
	public int fuzzyQueryCount(PageInfo pageInfo);
}
