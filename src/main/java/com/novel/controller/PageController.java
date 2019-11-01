package com.novel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 * @author 清风
 * @date 2019年9月24日 下午12:09:52   
 */
@Controller
public class PageController {
	
	
	@RequestMapping("/toAdminIndex")
	public String toAdminIndex() {
		return "admin/admin_index";
	}
	
	@RequestMapping("/toLogin")
	public String login(HttpServletRequest req) {
		if(req.getSession().getAttribute("user")!=null) {
			req.getSession().removeAttribute("user");
		}
		return "login";
	}
	@RequestMapping("/toRigister")
	public String rigister() {
		return "rigister";
	}
	
	
	@RequestMapping("/toUsers")
	public String manageUser() {
		return "forward:showAllUsers";
	}
	
	@RequestMapping("/toArticle")
	public String article(HttpServletRequest req) {
		return "forward:pageNovel";
	}
	
	@RequestMapping("/toComment")
	public String comment() {
		return "admin/comment";
	}
	
	@RequestMapping("/toNotice")
	public String notice() {
		return "forward:pageNotice";
	}
}
