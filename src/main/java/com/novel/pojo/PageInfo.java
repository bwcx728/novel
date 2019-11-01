package com.novel.pojo;

import java.util.List;
import java.util.Map;

/**
 * 封装分页相关属性
 * @author 清风
 * @date 2019年9月21日 下午7:16:00   
 */
public class PageInfo {
	/** 每页显示的条数 */
	private int pageSize; 
	/** 当前页数 */
	private int pageNumber;
	/** limit查询开始值  */
	private int pageStart;
	/** 显示页数集合 */
	private List<Integer> pageList;
	/** 页面显示数字按钮个数  */
	private int pageNum;
	/** 当前页面数字按钮最小值(pageList.subList(fromIndex,toIndex)) */
	private int fromIndex;
	/** 显示总页数 */
	private int pageCount;
	/** 总数,需要连接数据库查询 */
	private int total;
	/** 当前页显示的数据 */
	private List<?> list;
	/**	查询的条件	*/
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
