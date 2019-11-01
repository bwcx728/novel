package com.novel.service;

import java.util.List;

import com.novel.pojo.Notice;
import com.novel.pojo.PageInfo;

public interface NoticeService {
	
	/**
	 * 查询所有公告 limit分页
	 * @return
	 */
	public List<Notice> selPageInfo(PageInfo pageInfo);
	
	/**
	 * 公告总数
	 * @return
	 */
	public int selCount();
	
	/**
	 * 添加一条公告
	 * @param notice
	 * @return
	 */
	public int AddNotice(Notice notice);
	/**
	 * 删除一条公告
	 * @param id
	 * @return
	 */
	public int delNotice(int id);
	
	/**
	 * 修改一条公告
	 * @param notice
	 * @return
	 */
	public int updNotice(Notice notice);
	
	/**
	 * 查询一条公告
	 * @param id 
	 * @return
	 */
	public Notice findNotice(int id);
	
	/**
	 * 
	 * 查询最新发布的十条公告
	 * @return
	 */
	public List<Notice> findTenNoticeByTime();
}
