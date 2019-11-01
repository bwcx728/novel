package com.novel.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.novel.pojo.PageInfo;
import com.novel.pojo.Users;
import com.novel.service.UsersService;
import com.novel.util.RandomValidateCode;

/**
 * @author ���
 * @date 2019��9��21�� ����10:06:26   
 */
@Controller
public class UsersController {
	@Autowired
	private UsersService usersServiceImpl;
	
	@RequestMapping("/usersLogin")
	public String login(Users users,HttpSession session,HttpServletRequest req) {
		Users user = usersServiceImpl.selByUsers(users);
		if(user != null) {
			session.setAttribute("user",user);
			if(user.getRid() == UsersService.ADMIN_ID) {
				//��̨��ҳ
				return "redirect:toAdminIndex";
			}else if(user.getRid() == UsersService.USER_ID) {
				//ǰ����ҳ
				return "redirect:toIndex";
			}
		}
		return "redirect:toLogin";
	}
	@RequestMapping("/showAllUsers")
	public String selAll(Model model,PageInfo pageInfo) {
		//��ѯ�û�����
		int total = usersServiceImpl.selCount();
		pageInfo.setTotal(total);
		List<Users> usersList = usersServiceImpl.selPageInfo(pageInfo);
		pageInfo.setList(usersList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/manage-user";
	}
	
	@RequestMapping("/insUsers")
	public ModelAndView insUsers(Users users,HttpSession session) {
		//Ĭ��Ȩ��Ϊ�û�
		users.setRid(2);
		int i = usersServiceImpl.addByUsers(users);
		ModelAndView modelAndView = new ModelAndView("redirect:/showAllUsers");  
		if(i>0) {
			session.setAttribute("msg", "��ӳɹ�");
		}else {
			session.setAttribute("msg", "���ʧ��");
		}
		return modelAndView;
	}
	
	@RequestMapping("/delUsers")
	@ResponseBody
	public void delById(int id) {
		usersServiceImpl.delById(id);
	}
	@RequestMapping("/selUsers")
	@ResponseBody
	public Map<String, Object> selUsers(int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Users users = usersServiceImpl.selById(id);
		map.put("users",users);
		return map;
	}
	/**
	 * 
	 * @param users
	 * @param position  �޸�λ��  1Ϊ������Ϣ���޸� 2Ϊ����Ա�޸��û���Ϣ
	 * @param session
	 * @return
	 */
	@RequestMapping("/modifyUser")
	public String updByUser(Users users,int position,HttpSession session){
		int index = usersServiceImpl.updByUsers(users);
		if(index>0) {
			session.setAttribute("msg", "�޸ĳɹ�");
			if(position==1) {
				session.setAttribute("user", users);
			}
		}else {
			session.setAttribute("msg", "�޸�ʧ��");
		}
		return "redirect:/toIndex";
	}
	
	@RequestMapping("/rigisterUser")
	public String rigister(Users users,@RequestParam("file")MultipartFile file,HttpServletRequest req,HttpSession session){
		String fileName = "images/users/"+UUID.randomUUID().toString()+
				file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String path =session.getServletContext().getRealPath("/")+fileName;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ֻ�ܻ�ȡ��webapps�ļ�������
		users.setPhoto(fileName);
		//Ĭ��Ȩ��Ϊ�û�
		users.setRid(2);
		int index = usersServiceImpl.addByUsers(users);
		if(index>0) {
			return "/login";
		}else {
			return "redirect:/toRegister";
		}
	}
	@RequestMapping(value="code_ajax",method=RequestMethod.POST,produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Map<String,String> codeAjax(String codePage,HttpServletRequest req) throws IOException {
		String codeSession = req.getSession().getAttribute("code").toString();
		Map<String,String> map = new HashMap<String, String>();
//		System.out.println("code="+codePage+":"+"codeSession="+codeSession);
		if(codeSession.equals(codePage)) {
//			System.out.println("success");
			map.put("result","success");
		}else {
			map.put("result","error");
		}
	    return map;
	}
	@RequestMapping("ImageServlet")
	@ResponseBody
	public void imageServlet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		RandomValidateCode.validcode(req, resp);
	}
	
}
