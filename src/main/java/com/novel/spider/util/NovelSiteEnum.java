package com.novel.spider.util;

/**
 * ö�٣�֧�ֽ�����С˵��վ
 * @author ���
 * @date 2019��9��11�� ����10:14:54   
 */
public enum NovelSiteEnum {
	DingDianXiaoShuo(1,"23wx.cc"),
	BiQuGe(2,"biquge.tw"),
	BiXiaWenXue(3,"bxwx8.org");
	private int id;
	private String url;
	private NovelSiteEnum(int id,String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 	�����һ��id���Ҹ���һ��ö��
	 * @param id
	 * @return
	 */
	public static NovelSiteEnum getEnumById(int id) {
		switch(id) {
		case 1:return DingDianXiaoShuo;
		case 2:return BiQuGe;
		default:throw new RuntimeException("id="+id+"�ǲ���֧�ֵ�С˵��վ");
		}
	}
	/**
	 *	 �����һ��url���Ҹ���һ��ö��
	 * @param url
	 * @return
	 */
	public static NovelSiteEnum getEnumByUrl(String url) {
		//����ö��
		for(NovelSiteEnum novelSiteEnum:values()) {
			//�ж���ַ���Ƿ����ö���е���ַ
			if(url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url="+url+"�ǲ���֧�ֵ�С˵��վ");
	}
}
