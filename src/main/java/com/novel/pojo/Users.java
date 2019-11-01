package com.novel.pojo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 	�û�������Ϣʵ����
 * @author ���
 * @date 2019��9��24�� ����12:11:29   
 */
public class Users {
	private int id;
	private String username;
	private String password;
	/** �ֻ��� */
	private String usertel;
	/** ���� */
	private String email;
	/** ͷ�� */
	private String photo;
	/** ��¼����  */
	private int count; 
	/** �ϴε�¼ʱ�� */
	private Timestamp loginTime; 
	/** Ȩ��id */
	private int rid;
	/** ӵ�е�����б� */
	private List<Bookshelf> bookshelfList;
	/** ���Ĭ��id */
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
