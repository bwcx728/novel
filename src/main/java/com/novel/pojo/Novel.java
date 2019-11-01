package com.novel.pojo;

import java.util.Date;
import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * С˵�Ļ�����Ϣʵ��
 * @author ���
 * @date 2019��9��15�� ����7:37:11   
 */
/**
 * @author ���
 * @date 2019��9��17�� ����1:39:31   
 */
public class Novel {
	
	/**С˵���*/
	private int id;
	/** ���� */
	private String name;
	/** ������ */
	private String author;
	/** С˵������ */
	private String url;
	/** С˵����� �磺���� ���� */
	private String type;
	/**  ���һ�µ��½��� */
	private String lastUpdateChapter;
	/**  ���һ�µ����� */
	private String lastUpdateChapterUrl;
	/** С˵�����µ�ʱ�� */
	private Date lastUpdateTime ;
	/** С˵��״̬:1 ����   2��� */
	private int status;
	/** С˵���� */
	private String wordage;
	/** С˵ƽ̨��id*/
	private int platformId;
	/** С˵�洢���������ݿ��ʱ�� */
	private Date addTime;
	/** ��� */
	private String introduce;
	/** С˵���� */
	private String cover;
	private List<Chapter> chapterList;
	
	/** ���漯�ϣ�ֻ�й���Ա���Է��� */
	private List<Notice> noticeList;
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}
	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}
	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}
	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getWordage() {
		return wordage;
	}
	public void setWordage(String wordage) {
		this.wordage = wordage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public List<Chapter> getChapterList() {
		return chapterList;
	}
	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}
	public List<Notice> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	@Override
	public String toString() {
		return "Novel [id=" + id + ", name=" + name + ", author=" + author + ", url=" + url + ", type=" + type
				+ ", lastUpdateChapter=" + lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl
				+ ", lastUpdateTime=" + lastUpdateTime + ", status=" + status + ", wordage=" + wordage + ", platformId="
				+ platformId + ", addTime=" + addTime + ", introduce=" + introduce + ", cover=" + cover + "]";
	}
	
}
