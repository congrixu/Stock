package com.rxv5.stock.login.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rxv5.platform.Constant;
import com.rxv5.platform.common.BaseActionSupport;
import com.rxv5.stock.entity.User;
import com.rxv5.stock.login.service.LoginService;

@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("default")
public class LoginAction extends BaseActionSupport {

	private static final long serialVersionUID = -1939758104401189382L;

	@Resource
	private LoginService loginService;

	/**
	 * 登录系统VIEW
	 * @return
	 * @throws Exception
	 */
	@Action(value = "loginview", interceptorRefs = @InterceptorRef("rxv5DefaultStack"))
	public String loginView() throws Exception {
		return dispatcher("/WEB-INF/stock/login.jsp");
	}

	/**
	 * 登录系统
	 * @return
	 * @throws Exception
	 */
	@Action(value = "login", interceptorRefs = @InterceptorRef("rxv5DefaultStack"))
	public String login() throws Exception {
		String result = "/WEB-INF/stock/main.jsp";
		User user = (User) getSession().getAttribute(Constant.SESSION_USER);
		if (user == null) {
			String userId = getRequest().getParameter("userId");
			String pwd = getRequest().getParameter("pwd");
			user = loginService.checkUserLogin(userId, pwd);
			if (user == null) {
				getRequest().setAttribute("success", false);
				result = "/WEB-INF/stock/login.jsp";
			}
			getSession().setAttribute(Constant.SESSION_USER, user);
		}
		return dispatcher(result);
	}
}
