package com.novel.pojo;

import java.util.List;

/**
 *	 书架分组实体类
 * @author 清风
 * @date 2019年10月9日 下午8:09:46   
 */
public class Bookshelf {
	private int id;
	/**
	 * 书架名
	 */
	private String name;
	/**
	 * 用户外键id
	 */
	private int uid;
	/**  书架的小说集合	 */
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
