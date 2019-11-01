package com.novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.novel.pojo.Novel;

/**
 * 
 * ��ȡĳ��վ���С˵�б�
 * @author ���
 * @date 2019��9��15�� ����7:39:31   
 */
public interface INovelSpider {
	/**ץȡҳ�������Դ���3 */
	public static final int MAX_TRY_TIMES = 3;
	/**
	 * 
	 * ����һ��С˵�б��url���Ҿ͸���һ�ѵ�С˵ʵ��
	 * @param url
	 * @return
	 */
	public List<Novel> getNovel(String url, Integer maxTryTimes);
	
	/**
	 * �ж�С˵�б����Ƿ�����һҳ
	 * @return
	 */
	public boolean hasNext();
	/**
	 * 
	 * ���С˵�б�����һҳ��������һҳurl
	 * @return
	 */
	public String next();
	
	/**
	 * �����С˵�б�ĵ�һҳurl���Ҹ����������ҳ��С˵�б�
	 * @param firstPage ��һҳurl
	 * @return
	 */
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
	
	/**
	 * List<novel> novels = new ArrayList<>();
	 * Iterator<Novel> iterator = novels.iterator();
	 * while(iterator.hasNext()){
	 * 	Novel novel iterator.next();
	 *  System.out.println(novel);
	 * }
	 * */
	/**
	 *	 ����С˵����ҳurl,�Ҹ��㷵��С˵���棬С˵��飬С˵����
	 * @param url
	 * @return
	 */
	public Map<String,String> getNovelInfo(String url);

	/**
	 * 
	 * @param url С˵�б�
	 * @return ��ҳ��
	 */
	public int getPageCount(String url) ;
}
