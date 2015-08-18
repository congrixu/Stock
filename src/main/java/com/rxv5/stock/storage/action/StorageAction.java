package com.rxv5.stock.storage.action;

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
import com.rxv5.stock.entity.Inventory;
import com.rxv5.stock.entity.Storage;
import com.rxv5.stock.storage.service.StorageService;

@Controller
@Namespace("/storage")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class StorageAction extends BaseActionSupport {

	private static final long serialVersionUID = 674793011243868945L;

	private Storage storage;

	private Inventory inventory;

	@Resource
	private StorageService storageService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/storage/query.jsp");
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

		Map<String, Object> result = storageService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<Storage> list = (List<Storage>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("cdy-id", "cdy-id");
		map.put("cdy-name", "cdy-name");
		map.put("cdy-type", "cdy-type");
		map.put("num", "num");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	@Action(value = "inventoryview")
	public String inventoryView() {
		return dispatcher("/WEB-INF/stock/storage/inventory.jsp");
	}

	/**
	 * 盘点
	 */
	@Action(value = "inventory")
	public void inventory() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			storageService.inventory(inventory);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
