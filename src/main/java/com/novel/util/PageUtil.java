package com.novel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.novel.pojo.PageInfo;

/**
 * ��ҳ������
 * 
 * @author ���
 * @date 2019��9��24�� ����10:22:07
 */
public class PageUtil {
	/** Ĭ�Ϸ�ҳ��С */
	private static int pageSize = 6;
	/** Ĭ�ϵ�ǰҳ�� */
	private static int pageNumber = 1;
	/** Ĭ��ҳ����ʾ���ְ�ť���� */
	private static int pageNum = 6;
	/** Ĭ�ϵ�ǰҳ�����ְ�ť��ʼֵ */
	private static int fromIndex = 1;

	/**
	 * �Է�ҳ����ز��� total�ֶ�Ҫsetֵ��ȥ
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
		// ��������
		int total = pageInfo.getTotal();
		// �����ҳ��������pageInfo
		int pageCount = total % pageInfo.getPageSize() == 0 ? total / pageInfo.getPageSize()
				: total / pageInfo.getPageSize() + 1;
		// �ж�Ĭ��ҳ����ʾ���ְ�ť�����Ƿ������ҳ��
		if (pageInfo.getPageNum() >= pageCount) {
			pageInfo.setPageNum(pageCount);
		} else {
			pageInfo.setPageNum(pageNum);
		}
		pageInfo.setPageCount(pageCount);
		// �ж�ҳ���Ƿ�С��1�������ҳ��
		if (pageInfo.getPageNumber() <= 1) {
			pageInfo.setPageNumber(pageNumber);
		} else if (pageInfo.getPageNumber() > pageInfo.getPageCount()) {
			pageInfo.setPageNumber(pageInfo.getPageCount());
		}
		// ҳ������
		List<Integer> pageCountList = new ArrayList<>();
		// ��ʼ�����ְ�ťֵ,��������ҳ��
		if (pageInfo.getPageList() == null) {
			for (int i = 0; i < pageInfo.getPageCount(); i++) {
				pageCountList.add(i + 1);
			}
		}
		// ҳ���Ӽ��ϣ���ǰҳ����ʾ���ְ�ť�ļ���
		List<Integer> pageList = new ArrayList<>();
		// ���ư�ťҵ���߼�
		if (pageInfo.getFromIndex() < pageInfo.getPageNumber() - pageInfo.getPageNum() + 1) {
			pageInfo.setFromIndex(pageInfo.getPageNumber() - pageInfo.getPageNum() + 1);
			// ���ư�ťҵ���߼�
		} else if (pageInfo.getFromIndex() > pageInfo.getPageNumber()) {
			pageInfo.setFromIndex(pageInfo.getPageNumber());
		}

		// ���ְ�ť��ʼֵ�������һҳ��ʼֵʱʹ��������һҳ��ʼֵ
		if (pageInfo.getFromIndex() > pageCountList.size() - pageInfo.getPageNum() + 1) {
			pageInfo.setFromIndex(pageCountList.size() - pageInfo.getPageNum() + 1);
		}
		// ���ְ�ť��ʼֵС��1ʱʹ��ΪĬ��ֵ
		if (pageInfo.getFromIndex() < 1) {
			pageInfo.setFromIndex(fromIndex);
		}
		// ���󲻰���
		// pageCountList.subList(fromIndex, toIndex)
		pageList = pageCountList.subList(pageInfo.getFromIndex() - 1,
				pageInfo.getFromIndex() - 1 + pageInfo.getPageNum());
		pageInfo.setPageList(pageList);
		pageInfo.setPageStart(pageInfo.getPageSize() * (pageInfo.getPageNumber() - 1));
		return pageInfo;
	}

	/**
	 * 	�жϲ�ѯ�����Ƿ�Ϊ�գ� ���ݲ�ѯȥ��ģ����ѯ�� %
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
