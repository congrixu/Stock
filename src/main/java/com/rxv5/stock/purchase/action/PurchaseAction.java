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
import com.rxv5.stock.entity.PurchaseOrder;
import com.rxv5.stock.purchase.service.PurchaseService;

/**
 * 采购单
 * @author congrixu
 */
@Controller
@Namespace("/purchase")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class PurchaseAction extends BaseActionSupport {

	private static final long serialVersionUID = -653879543909909625L;

	private PurchaseOrder purchase;

	@Resource
	private PurchaseService purchaseService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/purchase/query.jsp");
	}

	@Action(value = "queryjson")
	public void queryJson() throws Exception {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));

		String sort = getRequest().getParameter("sort");
		String order = getRequest().getParameter("order");

		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");

		Map<String, String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		Map<String, Object> result = purchaseService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<PurchaseOrder> list = (List<PurchaseOrder>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("supplier-name", "supplier-name");
		map.put("createDate", "yyyy-MM-dd HH:mm:dd");
		map.put("stateStr", "stateStr");
		map.put("state", "state");
		map.put("remark", "remark");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	@Action(value = "delete")
	public void delete() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			String id = getParameterFromRequest("id");
			purchaseService.delete(id);
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
			PurchaseOrder purchase = purchaseService.get(id);
			getRequest().setAttribute("purchase", purchase);
		}
		return dispatcher("/WEB-INF/stock/purchase/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			purchaseService.saveOrModify(purchase);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	public PurchaseOrder getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseOrder purchase) {
		this.purchase = purchase;
	}

}
