package com.novel.spider.interfaces;

import com.novel.spider.configuration.Configuration;

/**
 * С˵���ؽӿ�
 * @author ���
 * @date 2019��9��24�� ����12:14:49   
 */
public interface INovelDownload {
	/**
	 * ���������ı����ڵ�·��
	 * @param url ���url��ָĳ��С˵���½��б�ҳ��
	 * @param config 
	 * @return
	 */
	public String download(String url,Configuration config);
}
