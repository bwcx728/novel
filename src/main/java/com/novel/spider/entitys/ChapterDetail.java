package com.novel.spider.entitys;

import java.io.Serializable;

/**
 *  �½���ϸ����ʵ����
 * @author ���
 * @date 2019��9��14�� ����8:05:51   
 */
public class ChapterDetail implements Serializable{
	private String title; //�½ڱ���
	private String content; //�½�����
	private String prev; //ǰһ�µ�ַ
	private String next; //��һ�µ�ַ
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "ChapterDetail [title=" + title + ", content=" + content + ", prev=" + prev + ", next=" + next + "]";
	}
}
