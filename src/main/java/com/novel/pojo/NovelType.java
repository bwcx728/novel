package com.novel.pojo;

/**
 * 	小说类型实体类
 * @author 清风
 * @date 2019年9月21日 下午3:54:08   
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
