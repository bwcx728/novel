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
 * 	С˵ǰ��ҳ��Ŀ�����
 * @author ���
 * @date 2019��9��20�� ����10:00:59   
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
	 * 	����С˵��ҳ
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		//��ȡȫ��С˵��Ϣ
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
	 * ����С˵ ҳ��
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/type/{id}",method = RequestMethod.GET)
	public String typeNovel(Model model,@PathVariable int id) {
		//��ǰС˵����
		NovelType novelType = novelTypeServiceImpl.selById(id);
		model.addAttribute("novelType", novelType);

		//��ȡ��ǰ����С˵�б�
		List<Novel> novelList = novelServiceImpl.findNovelByType(novelType.getName());
		model.addAttribute("novelList", novelList);
		
		//��ȡ����С˵����
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "novel/type_novel";
	}
	
	/**
	 * 	��ת��ȫ��С˵ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/completeList")
	public String complete(PageInfo pageInfo,HttpServletRequest req,Model model) {
		//ÿҳ��ʾ9��
		pageInfo.setPageSize(9);
		int total = novelServiceImpl.selCount();
		if(total <= 0) {
			req.getSession().setAttribute("msg", "û���ҵ���ص�С˵��");
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
	 * ��������С˵ҳ��
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
			req.getSession().setAttribute("msg", "û���ҵ���ص�С˵��");
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
		//С˵���������򼯺�
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "novel/search";
	}
	
	
	/**
	 * �������ҳ��
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
			//�ж�������Ƿ���С˵
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
	 * ����һ��ʱ�䣬��������뵱ǰʱ�������
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Date nowDate = new Date();
		return (int) ((nowDate.getTime()-date.getTime())/(1000*60*60*24));
	}
	
	
	/**
	 * 	����ID��ȡС˵��Ϣ��Ȼ����ת��С˵�½�����
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/novel/{id}",method=RequestMethod.GET)
	public String chapter(HttpServletRequest req,Model model,@PathVariable int id) {
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		Novel novel = novelServiceImpl.findById(id);
		// ��ȡС˵�����½�
		IChapterSpider iChapterImpl = ChapterSpiderFactory.getChapterSpider(novel.getUrl());
		List<Chapter> chapterLists = iChapterImpl.getChapters(novel.getUrl());
		//���½�����url����ȡ����Ĳ��֣�ǰ�沿�����½�url��ͬ
		List<Chapter> chapterList = ChapterHandle.subUrl(chapterLists);
		// ����12��
		List<List<Chapter>> newChapterList = ChapterHandle.getNewChapters(chapterList);
		model.addAttribute("novel", novel);
		model.addAttribute("chapterLists", ChapterHandle.getChapterLists(chapterList));
		model.addAttribute("newChapterLists",newChapterList);
		
		//����Ѿ���¼���ж�С˵�Ƿ��Ѿ��������
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
	 * @param id1 novel.id��ȡname
	 * @param url �½��������ӣ�https://www.23wx.cc/du/27/27895/7802038.html��
	 * @return
	 */
	@RequestMapping(value="novel/{id}/{url}",method = RequestMethod.GET)
	public String chapterContent(Model model,@PathVariable("id") int id,@PathVariable("url") String url) {
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		//����Id���novel ���С˵���ֺ�С˵�½�url
		Novel novel = novelServiceImpl.findById(id);
		url = novel.getUrl() + url + ".html";
		//���������ȡ�½�������
		IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
		ChapterDetail chapterDetail = spider.getChapterDetail(url);
		String content = chapterDetail.getContent();
		//�Ի��н��д���
		content = content.replace("<br/> <br/>","<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		chapterDetail.setContent(content);
		//��ǰһ�����һ�½��д���
		chapterDetail.setPrev(ChapterHandle.subUrl(chapterDetail.getPrev()));
		chapterDetail.setNext(ChapterHandle.subUrl(chapterDetail.getNext()));
		
		model.addAttribute("novel",novel);
		model.addAttribute("chapterDetail",chapterDetail);
		return "novel/chapter_content";
	}
}
