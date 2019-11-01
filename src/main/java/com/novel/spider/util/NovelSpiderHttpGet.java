package com.novel.spider.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * 设置默认的请求参数
 * @author 清风
 * @date 2019年9月14日 下午6:11:23   
 */
public class NovelSpiderHttpGet extends HttpGet {

	public NovelSpiderHttpGet() {
	}

	public NovelSpiderHttpGet(URI uri) {
		super(uri);
	}

	public NovelSpiderHttpGet(String uri) {
		super(uri);
	}
	private void setDefaultConfig() {
		this.setConfig(RequestConfig.custom()
				.setConnectTimeout(10_000) //设置连接服务器的超时时间
				.setConnectionRequestTimeout(10_000) //设置从服务器读取数据的超时时间
				.build());
		this.setHeader("User-Agent","NovelSpider"); //设置请求头
	}
}
