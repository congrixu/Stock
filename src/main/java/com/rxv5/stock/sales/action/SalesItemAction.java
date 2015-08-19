package com.rxv5.stock.sales.action;

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
import com.rxv5.stock.sales.service.SalesItemService;
import com.rxv5.stock.sales.service.SalesService;

/**
 * 采购明细
 * @author congrixu
 */
@Controller
@Namespace("/salesitem")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class SalesItemAction extends BaseActionSupport {

	private static final long serialVersionUID = 502798296776351437L;

	private SalesItem salesItem;

	@Resource
	private SalesItemService salesItemService;

	@Resource
	SalesService salesService;

	@Action(value = "query")
	public String query() throws Exception {
		String salesId = getParameterFromRequest("salesId");

		SalesOrder so = salesService.get(salesId);
		getRequest().setAttribute("so", so);
		return dispatcher("/WEB-INF/stock/salesitem/query.jsp");
	}

	@Action(value = "queryjson")
	public void queryJson() throws Exception {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));

		String sort = getRequest().getParameter("sort");
		String order = getRequest().getParameter("order");

		String salesId = getParameterFromRequest("salesId");

		Map<String, Object> result = salesItemService.query(salesId, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<SalesItem> list = (List<SalesItem>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("commodity-name", "commodity-name");
		map.put("commodity-type", "commodity-type");
		map.put("sup-name", "sup-name");
		map.put("num", "num");
		map.put("price", "price");
		map.put("totalPrice", "totalPrice");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	@Action(value = "delete")
	public void delete() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = getParameterFromRequest("id");
			salesItemService.delete(id);
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
			SalesItem item = salesItemService.get(id);
			getRequest().setAttribute("item", item);
		}
		return dispatcher("/WEB-INF/stock/salesitem/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			salesItemService.saveOrModify(salesItem);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	public SalesItem getSalesItem() {
		return salesItem;
	}

	public void setSalesItem(SalesItem salesItem) {
		this.salesItem = salesItem;
	}

}
