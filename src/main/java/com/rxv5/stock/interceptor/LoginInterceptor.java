package com.rxv5.stock.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.rxv5.platform.Constant;
import com.rxv5.stock.entity.User;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -4092223010326453897L;

	private String path;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ai.getInvocationContext().get(
				ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.SESSION_USER);
		if (user != null) {
			return ai.invoke();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		String location = request.getContextPath() + path;
		response.sendRedirect(location);
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
