package com.xwkj.mengular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MengularDocument {
	public final static String DEFAULT_TEMPLATE_CHARACTER_ENCODING="UTF-8";
	
	private String rootPath;
	
	private boolean readTemplateSuccess;
	private String document;
	
	public String getRootPath() {
		return rootPath;
	}

	public boolean isReadTemplateSuccess() {
		return readTemplateSuccess;
	}

	public MengularDocument(String rootPath, String templatePath, String templateCharacterEncoding) {
		super();
		this.rootPath = rootPath;
		File template=new File(rootPath+templatePath);
		if(!template.isFile()||!template.exists()) {
			readTemplateSuccess=false;
			return;
		}
		try {
			InputStreamReader streamReader=new InputStreamReader(new FileInputStream(template), templateCharacterEncoding);
			BufferedReader bufferedReader=new BufferedReader(streamReader);
			String line=null;
			document="";
			while((line=bufferedReader.readLine())!=null) {
				document+=line+"\n";
			}
			bufferedReader.close();
			readTemplateSuccess=true;
		} catch (IOException e) {
			readTemplateSuccess=false;
			e.printStackTrace();
		}
	}

	public void setValue(String key, String value) {
		if(readTemplateSuccess) {
			document=document.replace("#{"+key+"}", value);
		}
	}
	
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
