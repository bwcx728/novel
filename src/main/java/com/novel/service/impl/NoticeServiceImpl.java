package com.novel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.mapper.NoticeMapper;
import com.novel.pojo.Notice;
import com.novel.pojo.PageInfo;
import com.novel.service.NoticeService;
import com.novel.util.PageUtil;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<Notice> selPageInfo(PageInfo pageInfo) {
		pageInfo = PageUtil.getPageInfo(pageInfo);
		return noticeMapper.selPageInfo(pageInfo);
	}
	@Override
	public int selCount() {
		return noticeMapper.selCount();
	}
	
	@Override
	public int AddNotice(Notice notice) {
		return noticeMapper.AddNotice(notice);
	}
	@Override
	public int delNotice(int id) {
		return noticeMapper.delNotice(id);
	}
	@Override
	public int updNotice(Notice notice) {
		return noticeMapper.updNotice(notice);
	}
	@Override
	public Notice findNotice(int id) {
		return noticeMapper.findNotice(id);
	}
	@Override
	public List<Notice> findTenNoticeByTime() {
		return noticeMapper.findTenNoticeByTime();
	}
}
