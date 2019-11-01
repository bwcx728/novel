package com.novel.pojo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 	用户基本信息实体类
 * @author 清风
 * @date 2019年9月24日 下午12:11:29   
 */
public class Users {
	private int id;
	private String username;
	private String password;
	/** 手机号 */
	private String usertel;
	/** 邮箱 */
	private String email;
	/** 头像 */
	private String photo;
	/** 登录次数  */
	private int count; 
	/** 上次登录时间 */
	private Timestamp loginTime; 
	/** 权限id */
	private int rid;
	/** 拥有的书架列表 */
	private List<Bookshelf> bookshelfList;
	/** 书架默认id */
	private int bookshelfId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public List<Bookshelf> getBookshelfList() {
		return bookshelfList;
	}

	public void setBookshelfList(List<Bookshelf> bookshelfList) {
		this.bookshelfList = bookshelfList;
	}
	
	public int getBookshelfId() {
		return bookshelfId;
	}

	public void setBookshelfId(int bookshelfId) {
		this.bookshelfId = bookshelfId;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", usertel=" + usertel
				+ ", email=" + email + ", photo=" + photo + ", count=" + count + ", loginTime=" + loginTime + ", rid="
				+ rid + ", bookshelfList=" + bookshelfList + "]";
	}
	
}
