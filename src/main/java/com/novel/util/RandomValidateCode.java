package com.novel.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注册页面实现图形 验证码
 * @author 清风
 * @date 2019年9月17日 下午12:16:06   
 */
public class RandomValidateCode {
	public static void validcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long time = System.currentTimeMillis();
		// 通知浏览器不要缓存
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setHeader("Expires", "-1");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "-1");
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", 0);
		//创建一张图片
		BufferedImage image = new BufferedImage(200,100,BufferedImage.TYPE_INT_RGB);
		//设置画笔
		Graphics2D gra = image.createGraphics();
		gra.setColor(Color.WHITE);
		gra.fillRect(0,0,200,100);
		List<Integer> randList = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			randList.add(random.nextInt(10));
		}
		Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		for (int i = 0; i <randList.size(); i++) {
			gra.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,50+i*5));
			gra.setColor(color);
			gra.drawString(randList.get(i)+"",i*40,70+(random.nextInt(21)-10));
		}
		//画横线
		for (int j = 0; j < 2; j++) {
			gra.setColor(color);
			gra.drawLine(0,random.nextInt(101),200,random.nextInt(101));
		}
		ServletOutputStream outputStream = resp.getOutputStream();
		//工具类
		ImageIO.write(image, "jpg", outputStream);
		//把验证码放到session中
		HttpSession session = req.getSession();
		session.setAttribute("code", "" + randList.get(0) + randList.get(1) + randList.get(2) + randList.get(3));
	}
}
