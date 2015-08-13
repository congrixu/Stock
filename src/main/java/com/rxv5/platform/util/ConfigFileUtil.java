package com.rxv5.platform.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 项目名称：
 * 类说明：  
 * 版本：1.0 
 * 作者：congrixu
 * 修改人：
 * 日期：2010-6-17&nbsp;上午10:44:25
 * 单位：<br/>
 */
public class ConfigFileUtil {

	public static String getRootPath() {
		String rootPath = null;
		rootPath = getValueByKey("file.save.path");
		return rootPath;

	}

	public static String getValueByKey(String key) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties");
		String value = null;
		try {
			//FileInputStream fis = new FileInputStream();
			Properties pro = new Properties();
			pro.load(is);
			value = pro.getProperty(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

}
