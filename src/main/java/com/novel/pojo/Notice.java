package com.novel.pojo;

import java.util.Date;

/**
 * 小说公告实体类
 * @author 清风
 * @date 2019年9月23日 下午7:57:31   
 */
public class Notice {
	private int id;
	/** 内容 */
	private String content;
	/** 发布时间 */
	private Date releaseTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
}
