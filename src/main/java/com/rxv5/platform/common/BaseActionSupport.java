/**
 * 
 */
package com.rxv5.platform.common;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings( { "unchecked" })
public class BaseActionSupport extends ActionSupport {

	private static final long serialVersionUID = 5208748057591198755L;

	public static Logger logger = Logger.getLogger(BaseActionSupport.class.getName());

	/**
	 * 获取request路径
	 * @return
	 */
	protected String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * 获取servletContext
	 * @return
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 获取request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取response
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得session
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		HttpSession session = getRequest().getSession();

		return session;

	}

	/**
	 * 将对象存入ActionContext中，供页面显示
	 * 
	 * @param key
	 *            页面调用的值 ${key}
	 * @param value
	 *            具体显示的信息
	 */
	protected void putObjToContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	protected Object getObjFromContext(String key) {
		return ActionContext.getContext().get(key);
	}

	/**
	 * 将action中的消息显示到页面
	 * 
	 * @param value	要显示的字符串
	 */
	protected void displayMsg(String value) {
		HashMap<String, String> map = (HashMap<String, String>) getObjFromContext("messageMap");
		if (map == null || map.keySet().size() == 0) {
			map = new HashMap<String, String>();
		}
		map.put("message", value);
		putObjToContext("messageMap", map);
	}

	/**
	 * 将信息存入session中，当redirect后，供页面显示
	 * 
	 * @param key
	 *            页面调用的值 ${key}
	 * @param value
	 *            具体显示的文字信息
	 */
	protected void putObjToSession(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	protected Object getObjFromSession(String key) {
		HttpSession session = getSession();
		if (key == null || session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}
	}

	/**
	 * 从request中取得数据
	 * 
	 * @param key
	 *            页面传递的参数名
	 * @return 参数值，如果传递的为null返回空字符串
	 */
	protected String getParameterFromRequest(String key) {
		String parameter = getRequest().getParameter(key);
		if (parameter == null) {
			parameter = "";
		}
		return parameter;
	}

	/**
	 * 输出字符串到页面中
	 * @param text	要输出的字符串
	 */
	protected String renderText(String text) {
		if (text != null && !text.equalsIgnoreCase("")) {
			putObjToContext("result", text);
		}
		return "renderString";
	}

	/**
	 * 重定向
	 * @param file
	 * @return
	 */
	protected String redirect(String file) {
		String location = "/";
		if (file != null && !file.equalsIgnoreCase("")) {
			location = file;
		}
		putObjToContext("location", location);
		return "jspRedirect";
	}

	/**
	 * 转发
	 * @param file
	 * @return
	 */
	protected String dispatcher(String file) {
		String location = "/";
		if (file != null && !file.equalsIgnoreCase("")) {
			location = file;
		}
		putObjToContext("location", location);
		return "jspDiapatcher";
	}

	/**
	 * 请求action
	 * @param file
	 * @return
	 */
	protected String chain(String file) {
		String location = "/";
		if (file != null && !file.equalsIgnoreCase("")) {
			location = file;
		}
		putObjToContext("location", location);
		return "actionChain";
	}

}
