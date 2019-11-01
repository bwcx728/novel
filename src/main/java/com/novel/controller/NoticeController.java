package com.novel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.pojo.Notice;
import com.novel.pojo.PageInfo;
import com.novel.service.NoticeService;


@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeServiceImpl;
	
	@RequestMapping("/pageNotice")
	public String pageNotice(Model model,PageInfo pageInfo) {
		//查询公告总数
		int total = noticeServiceImpl.selCount();
		pageInfo.setTotal(total);
		List<Notice> noticeList = noticeServiceImpl.selPageInfo(pageInfo);
		pageInfo.setList(noticeList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/notice";
	}
	@RequestMapping("/addNotice")
	@ResponseBody
	public void addNotice(String content){
		Notice notice = new Notice();
		System.out.println(content);
		notice.setContent(content);
		noticeServiceImpl.AddNotice(notice);
	}

	@RequestMapping("/delNotice")
	@ResponseBody
	public void delNotice(int id){
		noticeServiceImpl.delNotice(id);
	}
	
	@RequestMapping("/updNotice")
	@ResponseBody
	public void updNotice(Notice notice){
		noticeServiceImpl.updNotice(notice);
	}
	
	
}
