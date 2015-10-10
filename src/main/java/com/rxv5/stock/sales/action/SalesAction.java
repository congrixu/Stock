package com.rxv5.stock.sales.action;

import java.util.Date;
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
import com.rxv5.stock.entity.SalesItem;
import com.rxv5.stock.entity.SalesOrder;
import com.rxv5.stock.entity.User;
import com.rxv5.stock.sales.service.SalesItemService;
import com.rxv5.stock.sales.service.SalesService;

@Controller
@Namespace("/sales")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class SalesAction extends BaseActionSupport {

	private static final long serialVersionUID = 2507843799286018450L;

	private SalesOrder sales;

	@Resource
	private SalesService salesService;

	@Resource
	private SalesItemService salesItemService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/sales/query.jsp");
	}

	@Action(value = "queryjson")
	public void queryJson() throws Exception {
		Integer page = getRequest().getParameter("page") == null ? 1
				: Integer.valueOf(getRequest().getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE
				: Integer.valueOf(getRequest().getParameter("rows"));

		String sort = getRequest().getParameter("sort");
		String order = getRequest().getParameter("order");

		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");
		String clientName = getParameterFromRequest("clientName");
		String clientPhone = getParameterFromRequest("clientPhone");

		Map<String, String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("clientName", clientName);
		param.put("clientPhone", clientPhone);

		Map<String, Object> result = salesService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<SalesOrder> list = (List<SalesOrder>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("clientName", "clientName");
		map.put("clientPhone", "clientPhone");
		map.put("clientAddr", "clientAddr");
		map.put("salesDate", "yyyy-MM-dd HH:mm:ss");
		map.put("totalNum", "totalNum");
		map.put("totalPrice", "totalPrice");
		map.put("remark", "remark");
		map.put("stateStr", "stateStr");
		map.put("state", "state");
		map.put("user-name", "user-name");
		map.put("fitters-name", "fitters-name");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	@Action(value = "delete")
	public void delete() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = getParameterFromRequest("id");
			salesService.delete(id);
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
			SalesOrder sales = salesService.get(id);
			getRequest().setAttribute("sales", sales);
		}
		return dispatcher("/WEB-INF/stock/sales/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = sales.getId();
			if (id == null || id.trim().length() <= 0) {
				User user = (User) getSession().getAttribute(com.rxv5.platform.Constant.SESSION_USER);
				sales.setUser(user);
			}
			salesService.saveOrModify(sales);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	@Action(value = "outlib")
	public void outlib() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = getRequest().getParameter("id");
			String fitter = getRequest().getParameter("fitter");
			User user = new User();
			user.setId(fitter);
			salesService.outlib(id, user);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	@Action(value = "print")
	public String print() {
		String id = getParameterFromRequest("id");
		SalesOrder sales = salesService.get(id);
		List<SalesItem> items = salesItemService.queryBySalesId(id);
		getRequest().setAttribute("sales", sales);
		getRequest().setAttribute("items", items);
		getRequest().setAttribute("currentDate", new Date());
		return dispatcher("/WEB-INF/stock/sales/print.jsp");
	}

	/**
	 * 出库设置安装人员
	 * @return
	 * @throws Exception
	 */
	@Action(value = "setFitters")
	public String setFitters() throws Exception {
		String id = getRequest().getParameter("id");
		getRequest().setAttribute("id", id);
		return dispatcher("/WEB-INF/stock/sales/setFitters.jsp");
	}

	public SalesOrder getSales() {
		return sales;
	}

	public void setSales(SalesOrder sales) {
		this.sales = sales;
	}

}
