package com.novel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.pojo.Novel;
import com.novel.pojo.NovelType;
import com.novel.pojo.PageInfo;
import com.novel.service.NovelService;
import com.novel.service.NovelTypeService;
import com.novel.spider.interfaces.INovelSpider;
import com.novel.spider.util.NovelSpiderFactory;
import com.novel.util.PageUtil;

/**
 * 小说后台页面的控制器
 * 
 * @author 清风
 * @date 2019年9月20日 上午9:59:45
 */
@Controller
public class NovelAfterController {

	@Autowired
	private NovelService novelService;
	@Autowired
	private NovelTypeService novelTypeServiceImpl;

	@RequestMapping("/pageNovel")
	public String pageNovel(PageInfo pageInfo,Model model,HttpServletRequest req,HttpServletResponse resp) {
		String queryType = req.getParameter("queryType");
		String fuzzyQuery = req.getParameter("fuzzyQuery");
		boolean lean1 = queryType != null && !"0".equals(queryType) ;
		boolean lean2 = fuzzyQuery != null && !"".equals(fuzzyQuery);
		int total = 0;
		if(lean1||lean2){
			Map<String,String> map = new HashMap<String,String>();
			try {
				if(lean1) {
					queryType = URLDecoder.decode(queryType,"UTF-8");
					map.put("queryType", queryType);
				}
				if(lean2){
					fuzzyQuery = URLDecoder.decode(fuzzyQuery,"UTF-8");
					map.put("fuzzyQuery", fuzzyQuery);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			pageInfo.setMap(map);
			total = novelService.fuzzyQueryCount(pageInfo);
		}else {
			total = novelService.selCount();
		}
		if(total <= 0) {
			req.getSession().setAttribute("msg", "没有找到相关的小说！");
			return "redirect:/toArticle";
		}
		pageInfo.setTotal(total);
		List<Novel> novelList = new ArrayList<>();
		novelList = novelService.selByPageInfo(pageInfo);
		pageInfo.setList(novelList);
		Map<String, String> map = (Map<String, String>) pageInfo.getMap();
		if(map!=null) {
			pageInfo.setMap(PageUtil.getMap(map));
		}
		model.addAttribute("pageInfo", pageInfo);
		//小说类型下拉框集合
		List<NovelType> typeList = novelTypeServiceImpl.selAll();
		model.addAttribute("typeList", typeList);
		return "admin/article";
	}

	@RequestMapping("/deleteNovel")
	@ResponseBody
	public void deletenovel(int id, HttpServletResponse resp, HttpSession session) throws IOException {
		int index = novelService.deleteById(id);
		if (index > 0) {
			session.setAttribute("msg", "删除成功");
		} else {
			session.setAttribute("msg", "删除失败");
		}
	}

	@RequestMapping("batchDeleteNovel")
	@ResponseBody
	public void batchDeletenovel(@RequestParam(value = "checkId[]") Integer[] checkId, HttpSession session)
			throws IOException {
		int index = novelService.batchDeleteNovel(checkId);
		if (index > 0) {
			session.setAttribute("msg", "删除成功");
		} else {
			session.setAttribute("msg", "删除失败");
		}
	}
	
	@RequestMapping("batchInsertNovel")
	@ResponseBody
	public void batchInsertNovel() throws IOException {
		String url = "https://www.23wx.cc/quanben/1";
		INovelSpider spider = NovelSpiderFactory.getNovelSpider(url);
		Iterator<List<Novel>> iterator = spider.iterator(url, 10);
		while (iterator.hasNext()) {
			List<Novel> novels = iterator.next();
			for (Novel novel : novels) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM--dd");
				try {
					novel.setLastUpdateTime(format.parse(format.format(novel.getAddTime())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			novelService.batchInsertNovel(novels);
		}
	}
}
