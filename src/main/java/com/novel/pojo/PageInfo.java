package com.novel.pojo;

import java.util.List;
import java.util.Map;

/**
 * ��װ��ҳ�������
 * @author ���
 * @date 2019��9��21�� ����7:16:00   
 */
public class PageInfo {
	/** ÿҳ��ʾ������ */
	private int pageSize; 
	/** ��ǰҳ�� */
	private int pageNumber;
	/** limit��ѯ��ʼֵ  */
	private int pageStart;
	/** ��ʾҳ������ */
	private List<Integer> pageList;
	/** ҳ����ʾ���ְ�ť����  */
	private int pageNum;
	/** ��ǰҳ�����ְ�ť��Сֵ(pageList.subList(fromIndex,toIndex)) */
	private int fromIndex;
	/** ��ʾ��ҳ�� */
	private int pageCount;
	/** ����,��Ҫ�������ݿ��ѯ */
	private int total;
	/** ��ǰҳ��ʾ������ */
	private List<?> list;
	/**	��ѯ������	*/
	public Map<String,?> map;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getFromIndex() {
		return fromIndex;
	}
	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	public Map<String, ?> getMap() {
		return map;
	}
	public void setMap(Map<String, ?> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "PageInfo [pageSize=" + pageSize + ", pageNumber=" + pageNumber + ", pageStart=" + pageStart
				+ ", pageList=" + pageList + ", pageNum=" + pageNum + ", fromIndex=" + fromIndex + ", pageCount="
				+ pageCount + ", total=" + total + ", list=" + list + ", map=" + map + "]";
	}
	
}
