package com.xwkj.mengular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MengularDocument {
	//定义模板文件默认文字编码为UTF-8
	public final static String DEFAULT_TEMPLATE_CHARACTER_ENCODING="UTF-8";
	//定义输出文件默认文字编码为UTF-8
	public final static String DEFAULT_OUTPUT_CHARACTER_ENCODING="UTF-8";
	
	private String rootPath;
	
	private boolean readTemplateSuccess;
	private String document;
	private List<String> paragraphs;
	private List<String> templates;
	private List<String> ids;
	
	public String getRootPath() {
		return rootPath;
	}

	public boolean isReadTemplateSuccess() {
		return readTemplateSuccess;
	}
	
	/**
	 * 使用默认模板文件文字编码的构造方法
	 * @param rootPath Web项目根目录
	 * @param templatePath 模板文件路径
	 */
	public MengularDocument(String rootPath, int depth, String templatePath) {
		super();
		this.rootPath = rootPath;
		init(templatePath, depth, DEFAULT_TEMPLATE_CHARACTER_ENCODING);
	}

	/**
	 * 自定义模板文件文字编码的构造方法
	 * @param rootPath Web项目根目录
	 * @param templatePath 模板文件路径
	 * @param templateCharacterEncoding 自定义文字编码
	 */
	public MengularDocument(String rootPath, int depth, String templatePath, String templateCharacterEncoding) {
		super();
		this.rootPath = rootPath;
		init(templatePath,  depth, templateCharacterEncoding);
	}
	
	/**
	 * 初始化方法
	 * @param templatePath 模板文件路径
	 * @param templateCharacterEncoding 自定义文字编码
	 */
	private void init(String templatePath, int depth, String templateCharacterEncoding) {
		File templateFile=new File(rootPath+templatePath);
		if(!templateFile.isFile()||!templateFile.exists()) {
			readTemplateSuccess=false;
			return;
		}
		try {
			InputStreamReader streamReader=new InputStreamReader(new FileInputStream(templateFile), templateCharacterEncoding);
			BufferedReader bufferedReader=new BufferedReader(streamReader);
			String line=null;
			document="";
			paragraphs=new ArrayList<>();
			templates=new ArrayList<>();
			ids=new ArrayList<>();
			String paragraph="", template="";
			boolean isTemplate=false;
			while((line=bufferedReader.readLine())!=null) {
				if(line.contains("<!--mengular-start")) {
					paragraphs.add(paragraph);
					paragraph="";
					isTemplate=true;
					ids.add(line.split("id=\"")[1].split("\"")[0]);
					continue;
				}
				if(line.contains("<!--mengular-end-->")) {
					templates.add(template);
					template="";
					isTemplate=false;
					continue;
				}
				if(isTemplate) {
					template+=line+"\n";
				} else {
					paragraph+=line+"\n";
				}
			}
			paragraphs.add(paragraph);
			bufferedReader.close();
			readTemplateSuccess=true;
//			String back="";
//			for(int i=0; i<depth; i++) {
//				back+="../";
//			}
//			document=document.replace("href=\"", "href=\""+back).replace("src=\"", "src=\""+back);
			
			for(String s: paragraphs) {
				System.out.println(s);
				System.out.println();
			}
			
			for(String s: templates) {
				System.out.println(s);
				System.out.println();
			}
			
			for(String id: ids) {
				System.out.println(id);
			}
			
		} catch (IOException e) {
			readTemplateSuccess=false;
			e.printStackTrace();
		}
	}

	/**
	 * 填充占位符
	 * @param key 占位符名称
	 * @param value 占位符值
	 */
	public void setValue(String key, String value) {
		if(readTemplateSuccess) {
			document=document.replace("#{"+key+"}", value);
		}
	}
	
	public void setLoop(String id, List<Map<String, String>> items) {
		
	}
	
	/**
	 * 使用默认文字编码输出
	 * @param outputPath 输出文件路径
	 */
	public void output(String outputPath) {
		output(outputPath, DEFAULT_OUTPUT_CHARACTER_ENCODING);
	}
	
	/**
	 * 自定义文字编码输出
	 * @param outputPath 输出文件路径
	 * @param outputCharacterEncoding 自定义输出文件文字编码
	 */
	public void output(String outputPath, String outputCharacterEncoding) {
		if(!readTemplateSuccess) {
			return;
		}
		File output=new File(rootPath+outputPath);
		try {
			if (!output.exists()) {
				output.createNewFile();
			} 
			OutputStreamWriter streamWriter=new OutputStreamWriter(new FileOutputStream(output), outputCharacterEncoding);
			BufferedWriter bufferedWriter=new BufferedWriter(streamWriter);
			bufferedWriter.write(document);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
