package com.novel.spider.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderHttpGet;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * @author ���
 * @date 2019��9��21�� ����10:03:56   
 */
public abstract class AbstractSpider {
	/**
	 * ץȡָ��С˵��վ������
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {
		//try with resource jdk1.7
		try (
			//����HttpClient����
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			//����get����
			 CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
			//ʹ�ù�����EntityUtils������Ӧ��ȡ��ʵ���ʾ�����ݲ�ת�����ַ���
			String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
