package com.novel.pojo;

/**
 * 	С˵����ʵ����
 * @author ���
 * @date 2019��9��21�� ����3:54:08   
 */
public class NovelType {
	private int id;
	private String name;
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
	@Override
	public String toString() {
		return "NovelType [id=" + id + ", name=" + name + "]";
	}
	
}
