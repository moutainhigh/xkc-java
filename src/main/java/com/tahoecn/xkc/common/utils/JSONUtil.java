package com.tahoecn.xkc.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.util.ResourceUtils;

public class JSONUtil {

	/**
	 * 读取json文件，返回json串
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readJsonFile(String fileName) {
		String jsonStr = "";
		try {
			File file = ResourceUtils.getFile("classpath:" + fileName);
			FileReader fileReader = new FileReader(file);

			Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
			int ch = 0;
			StringBuffer sb = new StringBuffer();
			while ((ch = reader.read()) != -1) {
				sb.append((char) ch);
			}
			fileReader.close();
			reader.close();
			jsonStr = sb.toString();
			return jsonStr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
