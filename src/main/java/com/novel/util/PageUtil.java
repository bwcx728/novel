package com.novel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.novel.pojo.PageInfo;

/**
 * 分页工具类
 * 
 * @author 清风
 * @date 2019年9月24日 上午10:22:07
 */
public class PageUtil {
	/** 默认分页大小 */
	private static int pageSize = 6;
	/** 默认当前页面 */
	private static int pageNumber = 1;
	/** 默认页面显示数字按钮个数 */
	private static int pageNum = 6;
	/** 默认当前页面数字按钮开始值 */
	private static int fromIndex = 1;

	/**
	 * 对分页的相关操作 total字段要set值进去
	 * 
	 * @param pageInfo
	 * @return
	 */
	public static PageInfo getPageInfo(PageInfo pageInfo) {
		if (pageInfo.getPageSize() == 0) {
			pageInfo.setPageSize(pageSize);
		}
		if (pageInfo.getPageNumber() == 0) {
			pageInfo.setPageNumber(pageNumber);
		}
		if (pageInfo.getPageNum() == 0) {
			pageInfo.setPageNum(pageNum);
		}
		// 文章总数
		int total = pageInfo.getTotal();
		// 计算出页数并存入pageInfo
		int pageCount = total % pageInfo.getPageSize() == 0 ? total / pageInfo.getPageSize()
				: total / pageInfo.getPageSize() + 1;
		// 判断默认页面显示数字按钮个数是否大于总页数
		if (pageInfo.getPageNum() >= pageCount) {
			pageInfo.setPageNum(pageCount);
		} else {
			pageInfo.setPageNum(pageNum);
		}
		pageInfo.setPageCount(pageCount);
		// 判断页数是否小于1或大于总页数
		if (pageInfo.getPageNumber() <= 1) {
			pageInfo.setPageNumber(pageNumber);
		} else if (pageInfo.getPageNumber() > pageInfo.getPageCount()) {
			pageInfo.setPageNumber(pageInfo.getPageCount());
		}
		// 页数数组
		List<Integer> pageCountList = new ArrayList<>();
		// 初始化数字按钮值,存入所有页数
		if (pageInfo.getPageList() == null) {
			for (int i = 0; i < pageInfo.getPageCount(); i++) {
				pageCountList.add(i + 1);
			}
		}
		// 页面子集合，当前页面显示数字按钮的集合
		List<Integer> pageList = new ArrayList<>();
		// 右移按钮业务逻辑
		if (pageInfo.getFromIndex() < pageInfo.getPageNumber() - pageInfo.getPageNum() + 1) {
			pageInfo.setFromIndex(pageInfo.getPageNumber() - pageInfo.getPageNum() + 1);
			// 左移按钮业务逻辑
		} else if (pageInfo.getFromIndex() > pageInfo.getPageNumber()) {
			pageInfo.setFromIndex(pageInfo.getPageNumber());
		}

		// 数字按钮开始值大于最后一页开始值时使其等于最后一页开始值
		if (pageInfo.getFromIndex() > pageCountList.size() - pageInfo.getPageNum() + 1) {
			pageInfo.setFromIndex(pageCountList.size() - pageInfo.getPageNum() + 1);
		}
		// 数字按钮开始值小于1时使其为默认值
		if (pageInfo.getFromIndex() < 1) {
			pageInfo.setFromIndex(fromIndex);
		}
		// 包左不包右
		// pageCountList.subList(fromIndex, toIndex)
		pageList = pageCountList.subList(pageInfo.getFromIndex() - 1,
				pageInfo.getFromIndex() - 1 + pageInfo.getPageNum());
		pageInfo.setPageList(pageList);
		pageInfo.setPageStart(pageInfo.getPageSize() * (pageInfo.getPageNumber() - 1));
		return pageInfo;
	}

	/**
	 * 	判断查询内容是否为空， 内容查询去掉模糊查询的 %
	 * 
	 * @return
	 */
	public static Map<String, String> getMap(Map<String, String> map) {
		String str = map.get("fuzzyQuery");
		System.out.println("fuzzyQuery" + str);
		if (str != null && !str.equals("")) {
			str = str.replace("%", "");
			map.put("fuzzyQuery", str);
		}
		return map;
	}
}
