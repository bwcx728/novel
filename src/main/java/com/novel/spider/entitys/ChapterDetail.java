package com.novel.spider.entitys;

import java.io.Serializable;

/**
 *  章节详细内容实体类
 * @author 清风
 * @date 2019年9月14日 上午8:05:51   
 */
public class ChapterDetail implements Serializable{
	private String title; //章节标题
	private String content; //章节内容
	private String prev; //前一章地址
	private String next; //下一章地址
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
