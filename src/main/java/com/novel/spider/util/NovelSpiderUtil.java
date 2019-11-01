package com.novel.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 小说爬虫工具类
 * @author 清风
 * @date 2019年9月11日 上午10:13:09   
 */
public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum,Map<String,String>> CONTEXT_MAP = new HashMap<>();
	//类加载时执行初始化方法
	static {
		init();
	}
	private NovelSpiderUtil() {}
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private static void init() {
		// 创建SAXReader
		SAXReader reader = new SAXReader();
		try {
			String path = "src/main/resources/config/Spider-Rule.xml";
			//解析XML文件
			Document doc = reader.read(new File(path));
			// 获取根节点
			Element root = doc.getRootElement();
			//获取所有文本值
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				//获取根节点下所有子节点
				List<Element> subs = site.elements();
				Map<String,String> subMap = new HashMap();
				for (Element sub : subs) {
					String name = sub.getName();
					String text = sub.getTextTrim();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")),subMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	/**
	 * 	你给我一个枚举，我给你对应网站的解析规则
	 * @param novelSiteEnum
	 * @return
	 */
	public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum){
		return CONTEXT_MAP.get(novelSiteEnum);
	}

	/**
	 * 
	 * 多文件合并成一个文件
	 * @param path 基础目录，该根目录下的所有文本文件都会被合并到mergoToFile
	 * @param mergeToFile 被合并的文本文件可以为null,合并后的文件保存在path/merge.txt
	 * @param deleteThisFile 合并后小文件是否删除
	 */
	public static void multiFileMerge(String path,String mergeToFile,boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File mergeFile = new File(mergeToFile);
		if(mergeFile.exists()){
			System.out.println(mergeFile.delete());
		}
//		System.out.println(mergeToFile);
		//文件过滤
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files,new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int o1Index  = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index  = Integer.parseInt(o2.getName().split("\\-")[0]);
				if(o1Index > o2Index) {
					return 1;
				}else if(o1Index == o2Index) {
					return 0;
				}else {
					return -1;
				}
			}
		});
		PrintWriter out =null;
		try {
			out = new PrintWriter(mergeFile,"UTF-8");
			for(File file:files) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				String line = null;
				while((line=reader.readLine())!=null) {
					out.println(line);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			out.flush();
			out.close();
		}
		for(File f:files) {
			if(deleteThisFile&&f.getName().contains("-")) {
				System.err.println(f.getPath());
				System.gc();//启动jvm垃圾回收
				System.err.println(f.delete());
			}
		}
	}
	
	/**
	 * 获取书籍状态
	 * @param status
	 * @return
	 */
	public static int getNovelStatus(String status) {
		if(status.contains("连载")) {
			return 1;
		}else if(status.contains("完")) {
			return 2;
		}else {
			throw new RuntimeException("不支持的书籍状态："+status);
		}
	}
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
}
