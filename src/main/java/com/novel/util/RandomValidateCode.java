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
 * ע��ҳ��ʵ��ͼ�� ��֤��
 * @author ���
 * @date 2019��9��17�� ����12:16:06   
 */
public class RandomValidateCode {
	public static void validcode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long time = System.currentTimeMillis();
		// ֪ͨ�������Ҫ����
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setHeader("Expires", "-1");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "-1");
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", 0);
		//����һ��ͼƬ
		BufferedImage image = new BufferedImage(200,100,BufferedImage.TYPE_INT_RGB);
		//���û���
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
			gra.setFont(new Font("����",Font.BOLD|Font.ITALIC,50+i*5));
			gra.setColor(color);
			gra.drawString(randList.get(i)+"",i*40,70+(random.nextInt(21)-10));
		}
		//������
		for (int j = 0; j < 2; j++) {
			gra.setColor(color);
			gra.drawLine(0,random.nextInt(101),200,random.nextInt(101));
		}
		ServletOutputStream outputStream = resp.getOutputStream();
		//������
		ImageIO.write(image, "jpg", outputStream);
		//����֤��ŵ�session��
		HttpSession session = req.getSession();
		session.setAttribute("code", "" + randList.get(0) + randList.get(1) + randList.get(2) + randList.get(3));
	}
}
