package com.rxv5.stock.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.rxv5.platform.Constant;
import com.rxv5.platform.util.JsonUtils;
import com.rxv5.stock.entity.User;

/**
 * 系统变量拦截器
 * @author Rixu
 *
 */
public class SystemVariablesInterceptor implements Interceptor {

	private static final long serialVersionUID = 5780989740858597567L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ai.getInvocationContext().get(
				ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.SESSION_USER);
		request.setAttribute(Constant.SESSION_USER, user);
		request.setAttribute(Constant.SESSION_USER_JSON, JsonUtils.toJsonString(user));
		request.setAttribute("CONTEXT_PATH", request.getContextPath());
		request.setAttribute("BUILD_TIME", new Date().getTime());
		return ai.invoke();
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}
