package com.novel.pojo;

import java.util.Date;

/**
 * С˵����ʵ����
 * @author ���
 * @date 2019��9��23�� ����7:57:31   
 */
public class Notice {
	private int id;
	/** ���� */
	private String content;
	/** ����ʱ�� */
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
