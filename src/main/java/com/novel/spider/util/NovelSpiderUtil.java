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
 * С˵���湤����
 * @author ���
 * @date 2019��9��11�� ����10:13:09   
 */
public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum,Map<String,String>> CONTEXT_MAP = new HashMap<>();
	//�����ʱִ�г�ʼ������
	static {
		init();
	}
	private NovelSpiderUtil() {}
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private static void init() {
		// ����SAXReader
		SAXReader reader = new SAXReader();
		try {
			String path = "src/main/resources/config/Spider-Rule.xml";
			//����XML�ļ�
			Document doc = reader.read(new File(path));
			// ��ȡ���ڵ�
			Element root = doc.getRootElement();
			//��ȡ�����ı�ֵ
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				//��ȡ���ڵ��������ӽڵ�
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
	 * 	�����һ��ö�٣��Ҹ����Ӧ��վ�Ľ�������
	 * @param novelSiteEnum
	 * @return
	 */
	public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum){
		return CONTEXT_MAP.get(novelSiteEnum);
	}

	/**
	 * 
	 * ���ļ��ϲ���һ���ļ�
	 * @param path ����Ŀ¼���ø�Ŀ¼�µ������ı��ļ����ᱻ�ϲ���mergoToFile
	 * @param mergeToFile ���ϲ����ı��ļ�����Ϊnull,�ϲ�����ļ�������path/merge.txt
	 * @param deleteThisFile �ϲ���С�ļ��Ƿ�ɾ��
	 */
	public static void multiFileMerge(String path,String mergeToFile,boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File mergeFile = new File(mergeToFile);
		if(mergeFile.exists()){
			System.out.println(mergeFile.delete());
		}
//		System.out.println(mergeToFile);
		//�ļ�����
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
				System.gc();//����jvm��������
				System.err.println(f.delete());
			}
		}
	}
	
	/**
	 * ��ȡ�鼮״̬
	 * @param status
	 * @return
	 */
	public static int getNovelStatus(String status) {
		if(status.contains("����")) {
			return 1;
		}else if(status.contains("��")) {
			return 2;
		}else {
			throw new RuntimeException("��֧�ֵ��鼮״̬��"+status);
		}
	}
	/**
	 * ��ʽ�������ַ���Ϊ���ڶ���
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
