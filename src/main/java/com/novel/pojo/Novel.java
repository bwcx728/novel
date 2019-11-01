package com.novel.pojo;

import java.util.Date;
import java.util.List;

import com.novel.spider.entitys.Chapter;

/**
 * 小说的基本信息实体
 * @author 清风
 * @date 2019年9月15日 上午7:37:11   
 */
/**
 * @author 清风
 * @date 2019年9月17日 下午1:39:31   
 */
public class Novel {
	
	/**小说编号*/
	private int id;
	/** 书名 */
	private String name;
	/** 作者名 */
	private String author;
	/** 小说的链接 */
	private String url;
	/** 小说的类别 如：武侠 都市 */
	private String type;
	/**  最后一章的章节名 */
	private String lastUpdateChapter;
	/**  最后一章的链接 */
	private String lastUpdateChapterUrl;
	/** 小说最后更新的时间 */
	private Date lastUpdateTime ;
	/** 小说的状态:1 连载   2完结 */
	private int status;
	/** 小说字数 */
	private String wordage;
	/** 小说平台的id*/
	private int platformId;
	/** 小说存储到我们数据库的时间 */
	private Date addTime;
	/** 简介 */
	private String introduce;
	/** 小说封面 */
	private String cover;
	private List<Chapter> chapterList;
	
	/** 公告集合，只有管理员可以发布 */
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
