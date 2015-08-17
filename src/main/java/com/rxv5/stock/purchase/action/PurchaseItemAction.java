package com.rxv5.stock.purchase.action;

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
import com.rxv5.stock.entity.PurchaseItem;
import com.rxv5.stock.purchase.service.PurchaseItemService;

/**
 * 采购单明细
 * @author congrixu
 */
@Controller
@Namespace("/purchaseitem")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class PurchaseItemAction extends BaseActionSupport {

	private static final long serialVersionUID = 407307813486854798L;

	private PurchaseItem item;

	@Resource
	private PurchaseItemService purchaseItemService;

	@Action(value = "query")
	public String query() throws Exception {
		String purchaseId = getParameterFromRequest("purchaseId");
		getRequest().setAttribute("purchaseId", purchaseId);
		return dispatcher("/WEB-INF/stock/purchaseitem/query.jsp");
	}

	@Action(value = "queryjson")
	public void queryJson() throws Exception {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));

		String sort = getRequest().getParameter("sort");
		String order = getRequest().getParameter("order");

		String purchaseId = getParameterFromRequest("purchaseId");

		Map<String, Object> result = purchaseItemService.query(purchaseId, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<PurchaseItem> list = (List<PurchaseItem>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("cdy-name", "cdy-name");
		map.put("clothes", "clothes");
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
			purchaseItemService.delete(id);
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
			PurchaseItem item = purchaseItemService.get(id);
			getRequest().setAttribute("item", item);
		}
		return dispatcher("/WEB-INF/stock/purchaseitem/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			//TODO 页面要有设置采购单的位置
			purchaseItemService.saveOrModify(item);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	public PurchaseItem getItem() {
		return item;
	}

	public void setItem(PurchaseItem item) {
		this.item = item;
	}

}
