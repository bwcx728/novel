package com.novel.spider.util;

/**
 * 枚举，支持解析的小说网站
 * @author 清风
 * @date 2019年9月11日 上午10:14:54   
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
	 * 	你给我一个id，我给你一个枚举
	 * @param id
	 * @return
	 */
	public static NovelSiteEnum getEnumById(int id) {
		switch(id) {
		case 1:return DingDianXiaoShuo;
		case 2:return BiQuGe;
		default:throw new RuntimeException("id="+id+"是不被支持的小说网站");
		}
	}
	/**
	 *	 你给我一个url，我给你一个枚举
	 * @param url
	 * @return
	 */
	public static NovelSiteEnum getEnumByUrl(String url) {
		//遍历枚举
		for(NovelSiteEnum novelSiteEnum:values()) {
			//判断网址中是否包含枚举中的网址
			if(url.contains(novelSiteEnum.url)) {
				return novelSiteEnum;
			}
		}
		throw new RuntimeException("url="+url+"是不被支持的小说网站");
	}
}
