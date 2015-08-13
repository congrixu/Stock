package com.rxv5.platform.result;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class StringResult implements Result {

	private static final long serialVersionUID = 2156796872012750183L;

	public void execute(ActionInvocation invocation) throws Exception {
		String contentType = "text/plain;charset=UTF-8";
		String result = "nothing string render";
		Object resultObj = ActionContext.getContext().get("result");
		if (resultObj != null && resultObj instanceof String) {
			result = resultObj.toString();
		}
		Object contentTypeObj = ActionContext.getContext().get("contentType");
		if (contentTypeObj != null && contentTypeObj instanceof String) {
			contentType = contentTypeObj.toString();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType);
		response.getWriter().write(result);
	}

}
