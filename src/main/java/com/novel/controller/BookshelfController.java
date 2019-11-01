package com.novel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.pojo.Bookshelf;
import com.novel.pojo.Novel;
import com.novel.pojo.Users;
import com.novel.service.BookshelfService;
import com.novel.service.UsersService;

@Controller
public class BookshelfController {
	@Autowired
	private BookshelfService bookshelfServiceImpl;
	@Autowired
	private UsersService usersServiceImpl;

	@RequestMapping("/addBookshelf")
	@ResponseBody
	public void addBookshelf(String name, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		Bookshelf b = new Bookshelf();
		b.setName(name);
		b.setUid(user.getId());
		bookshelfServiceImpl.insBookshelf(b);
		// 更新session
		user = usersServiceImpl.selByUsers(user);
		session.setAttribute("user", user);
	}

	@RequestMapping("/updBookshelf")
	@ResponseBody
	public void updBookshelf(int id, String name, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		Bookshelf bookshelf = bookshelfServiceImpl.selById(id);
		bookshelf.setName(name);
		bookshelfServiceImpl.updBookshelf(bookshelf);
		// 更新session
		user = usersServiceImpl.selByUsers(user);
		session.setAttribute("user", user);
	}

	@RequestMapping("/delBookshelf")
	@ResponseBody
	public void delBookshelf(int id,HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		// 判断书架中是否有小说
		List<Novel> novelList = bookshelfServiceImpl.findAllNovel(id);
		if (novelList.get(0) != null) {
			for (Novel novel : novelList) {
				bookshelfServiceImpl.delNovelByBid(id, novel.getId());
			}
		}
		bookshelfServiceImpl.delBookshelf(id);
		// 更新session
		user = usersServiceImpl.selByUsers(user);
		session.setAttribute("user", user);
	}

	@RequestMapping("/addNovel")
	@ResponseBody
	public void addNovel(int nid, int bid, HttpSession session) {
		bookshelfServiceImpl.addNovelByBid(bid, nid);
	}

	@RequestMapping("/delNovel")
	@ResponseBody
	public void delNovel(int nid, int bid) {
		bookshelfServiceImpl.delNovelByBid(bid, nid);
	}
}
