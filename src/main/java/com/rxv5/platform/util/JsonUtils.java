package com.rxv5.platform.util;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings( { "unchecked", "rawtypes" })
public abstract class JsonUtils {

	/**
	 * Json字符串与Java对象互转的工具类
	 */
	protected static final ObjectMapper OM = new ObjectMapper();

	/**
	 * 将一个对象转换成json字符串
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJsonString(Object obj) throws Exception {
		if (obj != null) {
			return OM.writeValueAsString(obj);
		}
		return "";
	}

	/**
	 * 将一个json字符串转成指定的对象
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T toObjectByJson(String json, Class<T> clazz) throws Exception {
		if (json != null && !json.equals("")) {
			return OM.readValue(json, clazz);
		}
		return null;
	}

	public static String formatStr(String str) {
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

	/**
	 * 将一个JSON数组转换为LIST集合对象
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> toListByJsonArray(String jsonArrya, Class<List> clazz) throws Exception {
		List<T> jsonList = (List<T>) OM.readValue(jsonArrya, clazz);
		return jsonList;
	}
}
