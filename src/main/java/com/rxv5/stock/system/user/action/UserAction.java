package com.rxv5.stock.system.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rxv5.platform.common.BaseActionSupport;
import com.rxv5.platform.result.SuccessOrFailure;
import com.rxv5.platform.util.HttpUtils;
import com.rxv5.platform.util.JsonUtils;
import com.rxv5.platform.util.SendData;
import com.rxv5.stock.Constant;
import com.rxv5.stock.entity.User;
import com.rxv5.stock.system.user.service.UserService;

@Controller
@Namespace("/system/user")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class UserAction extends BaseActionSupport {

	private static final long serialVersionUID = 5121558317859632433L;

	private User user;

	@Resource
	private UserService userService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/system/user/query.jsp");
	}

	@Action(value = "queryjson")
	public void queryJson() throws Exception {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));

		String sort = getRequest().getParameter("sort");
		String order = getRequest().getParameter("order");

		String name = getParameterFromRequest("name");

		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);

		Map<String, Object> result = userService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<User> list = (List<User>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("userId", "userId");
		map.put("name", "name");
		map.put("phone", "phone");
		map.put("addr", "addr");
		map.put("idCard", "idCard");
		map.put("entryDate", "yyyy-MM-dd");
		map.put("resignDate", "yyyy-MM-dd");
		map.put("birthday", "yyyy-MM-dd");
		map.put("state", "state");
		map.put("stateStr", "stateStr");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	@Action(value = "delete")
	public void delete() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = getParameterFromRequest("id");
			userService.delete(id);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	/**
	 * 添加/修改
	 * @return
	 * @throws Exception
	 */
	@Action(value = "edit")
	public String edit() throws Exception {
		String id = getRequest().getParameter("id");
		if (id != null && id.length() > 0) {
			User user = userService.get(id);
			getRequest().setAttribute("user", user);
		}
		return dispatcher("/WEB-INF/stock/system/user/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			userService.saveOrModify(user);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	@Action(value = "choose")
	public String choose() throws Exception {
		return dispatcher("/WEB-INF/stock/system/user/choose.jsp");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
