package com.novel.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novel.pojo.Bookshelf;
import com.novel.pojo.Notice;
import com.novel.pojo.Novel;
import com.novel.pojo.NovelType;
import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;
import com.novel.service.BookshelfService;
import com.novel.service.NoticeService;
import com.novel.service.NovelService;
import com.novel.service.NovelTypeService;
import com.novel.spider.entitys.Chapter;
import com.novel.spider.entitys.ChapterDetail;
import com.novel.spider.interfaces.IChapterDetailSpider;
import com.novel.spider.interfaces.IChapterSpider;
import com.novel.spider.util.ChapterDetailSpiderFactory;
import com.novel.spider.util.ChapterHandle;
import com.novel.spider.util.ChapterSpiderFactory;
import com.novel.util.PageUtil;

/**
 * 	小说前端页面的控制器
 * @author 清风
 * @date 2019年9月20日 上午10:00:59   
 */
@Controller
public class NovelFrontController {
	@Autowired
	private NovelService novelServiceImpl;
	
	@Autowired
	private NovelTypeService novelTypeServiceImpl;
	
	@Autowired
	private NoticeService noticeServiceImpl;
	
	@Autowired
	private BookshelfService bookshelfServiceImpl;
	
	/**
	 * 	跳到小说首页
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		//获取全部小说信息
		List<Novel> novelList = novelServiceImpl.findAllNovel();
		model.addAttribute("novelList", novelList);
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		List<Notice> noticeList = noticeServiceImpl.findTenNoticeByTime();
		if(noticeList!=null) {
			Notice newNotice = noticeList.get(0);
			model.addAttribute("newNotice", newNotice);
		}
		return "novel/index";
	}
	
	/**
	 * 分类小说 页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/type/{id}",method = RequestMethod.GET)
	public String typeNovel(Model model,@PathVariable int id) {
		//当前小说类型
		NovelType novelType = novelTypeServiceImpl.selById(id);
		model.addAttribute("novelType", novelType);

		//获取当前类型小说列表
		List<Novel> novelList = novelServiceImpl.findNovelByType(novelType.getName());
		model.addAttribute("novelList", novelList);
		
		//获取所有小说类型
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "novel/type_novel";
	}
	
	/**
	 * 	跳转到全本小说页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/completeList")
	public String complete(PageInfo pageInfo,HttpServletRequest req,Model model) {
		//每页显示9条
		pageInfo.setPageSize(9);
		int total = novelServiceImpl.selCount();
		if(total <= 0) {
			req.getSession().setAttribute("msg", "没有找到相关的小说！");
			return "redirect:/toIndex";
		}
		pageInfo.setTotal(total);
		List<Novel> novelList = novelServiceImpl.selByPageInfo(pageInfo);
		pageInfo.setList(novelList);
		model.addAttribute("pageInfo", pageInfo);
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "novel/complete";
	}
	
	/**
	 * 跳到搜索小说页面
	 * @param pageInfo
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchNovel")
	public String searchNovel(PageInfo pageInfo,HttpServletRequest req,Model model) {
		String fuzzyQuery = req.getParameter("fuzzyQuery");
		String queryType = req.getParameter("queryType");
		try {
			fuzzyQuery = URLDecoder.decode(fuzzyQuery,"UTF-8");
			queryType = URLDecoder.decode(queryType,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("fuzzyQuery", fuzzyQuery);
		if(queryType!=null&&!queryType.equals("")) {
			if(novelTypeServiceImpl.selByName(queryType)!=null) {
				map.put("queryType", queryType);
			}else {
				NovelType novelType = novelTypeServiceImpl.selById(Integer.parseInt(queryType));
				map.put("queryType", novelType.getName());
			}
		}
		pageInfo.setMap(map);
		int total = novelServiceImpl.fuzzyQueryCount(pageInfo);
		if(total <= 0) {
			req.getSession().setAttribute("msg", "没有找到相关的小说！");
			return "redirect:/toIndex";
		}
		pageInfo.setTotal(total);
		List<Novel> novelList = new ArrayList<>();
		novelList = novelServiceImpl.selByPageInfo(pageInfo);
		pageInfo.setList(novelList);
		Map<String, String> map1 = (Map<String, String>) pageInfo.getMap();
		if(map1!=null) {
			pageInfo.setMap(PageUtil.getMap(map));
		}
		model.addAttribute("pageInfo", pageInfo);
		//小说类型下拉框集合
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "novel/search";
	}
	
	
	/**
	 * 跳到书架页面
	 * @param pageInfo
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bookshelfList/{bid}",method=RequestMethod.GET)
	public String bookshelfNovel(HttpServletRequest req,Model model,@PathVariable int bid) {
		Users user = (Users) req.getSession().getAttribute("user");
		if(user == null) {
			return "redirect:toLogin";
		}else {
			List<Bookshelf> bookshelfList = bookshelfServiceImpl.findAllBookshelf(user.getId());
			model.addAttribute("bookshelfList", bookshelfList);
			Bookshelf bookshelf = bookshelfServiceImpl.findBookshelfByBid(user.getId(),bid);
			model.addAttribute("bookshelf", bookshelf);
			//判断书架中是否有小说
			boolean emptyNovelList = false;
			if(bookshelf!=null) {
				emptyNovelList = bookshelf.getNovelList().get(0)!=null;
			}
			model.addAttribute("emptyNovelList",emptyNovelList);
			List<NovelType> typeList = novelTypeServiceImpl.selAll();
			model.addAttribute("typeList", typeList);
			return "novel/bookshelf";
		}
	}
	/**
	 * 给我一个时间，返回你距离当前时间的天数
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Date nowDate = new Date();
		return (int) ((nowDate.getTime()-date.getTime())/(1000*60*60*24));
	}
	
	
	/**
	 * 	根据ID获取小说信息，然后跳转到小说章节详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/novel/{id}",method=RequestMethod.GET)
	public String chapter(HttpServletRequest req,Model model,@PathVariable int id) {
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		Novel novel = novelServiceImpl.findById(id);
		// 获取小说所有章节
		IChapterSpider iChapterImpl = ChapterSpiderFactory.getChapterSpider(novel.getUrl());
		List<Chapter> chapterLists = iChapterImpl.getChapters(novel.getUrl());
		//对章节内容url处理，取后面的部分，前面部分与章节url相同
		List<Chapter> chapterList = ChapterHandle.subUrl(chapterLists);
		// 最新12章
		List<List<Chapter>> newChapterList = ChapterHandle.getNewChapters(chapterList);
		model.addAttribute("novel", novel);
		model.addAttribute("chapterLists", ChapterHandle.getChapterLists(chapterList));
		model.addAttribute("newChapterLists",newChapterList);
		
		//如果已经登录，判断小说是否已经加入书架
		Users user = (Users) req.getSession().getAttribute("user");
		if(user != null) {
			int result = bookshelfServiceImpl.findNovelIsBookshelf(novel.getId(), user.getId());
			if(result>0) {
				model.addAttribute("result", result);
			}
		}
		return "novel/novel_chapter";
	}
	
	/**
	 * @param model
	 * @param id1 novel.id获取name
	 * @param url 章节内容链接（https://www.23wx.cc/du/27/27895/7802038.html）
	 * @return
	 */
	@RequestMapping(value="novel/{id}/{url}",method = RequestMethod.GET)
	public String chapterContent(Model model,@PathVariable("id") int id,@PathVariable("url") String url) {
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		//根据Id查出novel 获得小说名字和小说章节url
		Novel novel = novelServiceImpl.findById(id);
		url = novel.getUrl() + url + ".html";
		//调用爬虫获取章节数据类
		IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
		ChapterDetail chapterDetail = spider.getChapterDetail(url);
		String content = chapterDetail.getContent();
		//对换行进行处理
		content = content.replace("<br/> <br/>","<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		chapterDetail.setContent(content);
		//对前一章与后一章进行处理
		chapterDetail.setPrev(ChapterHandle.subUrl(chapterDetail.getPrev()));
		chapterDetail.setNext(ChapterHandle.subUrl(chapterDetail.getNext()));
		
		model.addAttribute("novel",novel);
		model.addAttribute("chapterDetail",chapterDetail);
		return "novel/chapter_content";
	}
}
