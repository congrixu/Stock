package com.rxv5.platform.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class SendData {

	private List<Map<String, Object>> formatBean(Map<String, String> propertiesMap, List<?> data) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Iterator<?> i = data.iterator(); i.hasNext();) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object bean = i.next();
			for (String propertie : propertiesMap.keySet()) {
				String[] properties = propertie.split("[-]");
				Field field = null;
				Object temp = bean;
				if (temp != null) {
					for (String pro : properties) {
						if (temp != null) {
							Class<?> cc = temp.getClass();
							field = cc.getDeclaredField(pro);
							field.setAccessible(true);
							temp = field.get(temp);
						}
					}
					if (temp != null) {
						if (propertie.equals(propertiesMap.get(propertie))) {
							map.put(propertie, temp.toString());
						} else {
							if (temp.getClass().getName().equals("java.lang.Double")) {
								map.put(propertie, formatDouble((Double) temp, propertiesMap.get(propertie)));
							} else if (temp.getClass().getName().equals("java.util.Date")) {
								map.put(propertie, this.formatDate((Date) temp, propertiesMap.get(propertie)));
							} else if (temp.getClass().getName().equals("java.sql.Timestamp")) {
								map.put(propertie, this.formatDate((Date) temp, propertiesMap.get(propertie)));
							} else if (temp.getClass().getName().equals("java.lang.String")) {
								map.put(propertie, this.formatStr(temp.toString().replace("\n", " ").replace("\r", " ")
										.replace("\t", " ")));
							} else {
								map.put(propertie, temp.toString());
							}
						}
					}
				}
			}
			result.add(map);
		}
		return result;
	}

	public void sendDataJson(Map<String, String> propertiesMap, List<?> data, long pageSize,
			HttpServletResponse response) {
		try {
			List<Map<String, Object>> dataList = this.formatBean(propertiesMap, data);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", pageSize);
			map.put("rows", dataList);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JsonUtils.toJsonString(map));
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String formatDouble(Double dou, String str) {
		if (!str.equals("")) {
			DecimalFormat df = new DecimalFormat(str);
			return df.format(dou);
		} else {
			String douStr = String.valueOf(dou);
			if (douStr.endsWith(".0")) {

				int n = douStr.lastIndexOf(".");
				douStr = douStr.substring(0, n);

			}
			return douStr;

		}

	}

	private String formatDate(Date date, String str) {
		SimpleDateFormat df = new SimpleDateFormat(str);
		return df.format(date);
	}

	private String formatStr(String str) {
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] > 47 && c[i] < 58) {
				continue;
			}
			if (c[i] > 64 && c[i] < 91) {
				continue;
			}
			if (c[i] > 96 && c[i] < 123) {
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}
}
