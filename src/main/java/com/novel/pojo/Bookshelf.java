package com.novel.pojo;

import java.util.List;

/**
 *	 ��ܷ���ʵ����
 * @author ���
 * @date 2019��10��9�� ����8:09:46   
 */
public class Bookshelf {
	private int id;
	/**
	 * �����
	 */
	private String name;
	/**
	 * �û����id
	 */
	private int uid;
	/**  ��ܵ�С˵����	 */
	private List<Novel> novelList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public List<Novel> getNovelList() {
		return novelList;
	}
	public void setNovelList(List<Novel> novelList) {
		this.novelList = novelList;
	}
	@Override
	public String toString() {
		return "Bookshelf [id=" + id + ", name=" + name + ", uid=" + uid + ", novelList=" + novelList + "]";
	}
	
}
