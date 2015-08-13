package com.rxv5.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.struts2.ServletActionContext;

public class HttpUtils {

	/**
	 * 将数据发送的指定的网址,使用POST方式发送二进制数据
	 * @param destURL	目的网址
	 * @param data	参数组成的字符串
	 * @return		
	 * @throws IOException
	 */
	public static String send(String destURL, String data) {
		String status = "";
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(destURL);
			postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			byte[] dataAsBytes = new byte[data.length()];
			dataAsBytes = data.getBytes();
			RequestEntity entity = new ByteArrayRequestEntity(dataAsBytes);
			postMethod.setRequestEntity(entity);
			StringBuffer html = new StringBuffer(20);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				InputStream is = postMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String tempStr = "";
				while ((tempStr = br.readLine()) != null) {
					html.append(tempStr);
					//System.out.println(tempStr);
				}
			}
			status = html.toString();
		} catch (IOException e) {
			return "EXCEPTION:" + e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
		return status;
	}

	/**
	 * 将参数的map组装成key=value格式的字符串数组
	 * @param map
	 * @return
	 */
	public static String buildParams(Map<String, ?> map) {
		Iterator<String> it = map.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			String key = it.next();
			String value = "";
			Object obj = map.get(key);
			if (obj instanceof String) {
				value = (String) obj;
			}
			if (obj instanceof Integer) {
				value = obj.toString();
			}
			if (obj instanceof Date) {
				value = DateUtil.dateTimeFormatter((Date) obj);
			}
			sb.append(key).append("=").append(value).append("&");
		}
		return sb.toString();
	}

	public static void write(String str) {
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().write(str);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
