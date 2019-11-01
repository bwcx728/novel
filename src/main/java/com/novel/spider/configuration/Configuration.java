package com.novel.spider.configuration;

import java.io.Serializable;

/**
 * 	下载配置参数
 * @author 清风
 * @date 2019年9月14日 下午2:15:21   
 */
public class Configuration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 每个线程默认可以下载的最大章节数量
	 */
	public static final int DEFAULT_SIZE = 50;
	
	/**
	 * 每个线程下载每一章所允许的最大尝试次数
	 */
	public static final int DEFAULT_TRY_TIMES = 5;
	/**
	 * 下载后文件保存的基地址
	 * d:/bxwx.org/完美世界/1-2.txt
	 */
	private String path;
	/**
	 * 每个线程能够下载的最大章节数量
	 */
	private int size;
	
	/**
	 * 每个线程下载每一章的最大尝试次数
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
