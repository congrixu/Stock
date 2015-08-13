package com.rxv5.stock.supplier.action;

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
import com.rxv5.stock.entity.Supplier;
import com.rxv5.stock.supplier.service.SupplierService;

@Controller
@Namespace("/supplier")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class SupplierAction extends BaseActionSupport {

	private static final long serialVersionUID = 585734172729151829L;

	private Supplier supplier;

	@Resource
	private SupplierService supplierService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/supplier/query.jsp");
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
		String py = getParameterFromRequest("py");

		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		param.put("py", py);

		Map<String, Object> result = supplierService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<Supplier> list = (List<Supplier>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("name", "name");
		map.put("py", "py");
		map.put("addr", "addr");
		map.put("phone", "phone");
		map.put("fax", "fax");
		map.put("mobile", "mobile");
		map.put("bankInfo", "bankInfo");

		new SendData().sendDataJson(map, list, total, getResponse());
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
			Supplier supplier = supplierService.get(id);
			getRequest().setAttribute("supplier", supplier);
		}
		return dispatcher("/WEB-INF/stock/supplier/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			supplierService.saveModify(supplier);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	public String choose() throws Exception {
		return dispatcher("/WEB-INF/stock/supplier/choose.jsp");
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
