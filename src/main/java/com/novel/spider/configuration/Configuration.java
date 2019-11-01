package com.novel.spider.configuration;

import java.io.Serializable;

/**
 * 	�������ò���
 * @author ���
 * @date 2019��9��14�� ����2:15:21   
 */
public class Configuration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ÿ���߳�Ĭ�Ͽ������ص�����½�����
	 */
	public static final int DEFAULT_SIZE = 50;
	
	/**
	 * ÿ���߳�����ÿһ�������������Դ���
	 */
	public static final int DEFAULT_TRY_TIMES = 5;
	/**
	 * ���غ��ļ�����Ļ���ַ
	 * d:/bxwx.org/��������/1-2.txt
	 */
	private String path;
	/**
	 * ÿ���߳��ܹ����ص�����½�����
	 */
	private int size;
	
	/**
	 * ÿ���߳�����ÿһ�µ�����Դ���
	 */
	private int tryTimes;
	public Configuration() {
		this.size = DEFAULT_SIZE;
		this.tryTimes = DEFAULT_TRY_TIMES;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTryTimes() {
		return tryTimes;
	}
	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
	
	
	
}
