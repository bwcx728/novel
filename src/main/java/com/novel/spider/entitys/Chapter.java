package com.novel.spider.entitys;

import java.io.Serializable;

/**
 * 	С˵�½�ʵ����
 * @author ���
 * @date 2019��7��8�� ����7:23:49   
 */
public class Chapter implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;//С˵�½�
	private String url;//�½�����
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Chapter [title=" + title + ", url=" + url + "]";
	}
}
