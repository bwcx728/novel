package com.novel.spider.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * ����Ĭ�ϵ��������
 * @author ���
 * @date 2019��9��14�� ����6:11:23   
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
				.setConnectTimeout(10_000) //�������ӷ������ĳ�ʱʱ��
				.setConnectionRequestTimeout(10_000) //���ôӷ�������ȡ���ݵĳ�ʱʱ��
				.build());
		this.setHeader("User-Agent","NovelSpider"); //��������ͷ
	}
}
