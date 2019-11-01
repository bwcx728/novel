package com.novel.spider.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.novel.spider.util.NovelSiteEnum;
import com.novel.spider.util.NovelSpiderHttpGet;
import com.novel.spider.util.NovelSpiderUtil;

/**
 * @author 清风
 * @date 2019年9月21日 上午10:03:56   
 */
public abstract class AbstractSpider {
	/**
	 * 抓取指定小说网站的内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {
		//try with resource jdk1.7
		try (
			//采用HttpClient技术
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			//声明get请求
			 CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
			//使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
			String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
