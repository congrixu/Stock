package com.rxv5.stock.commodity.action;

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
import com.rxv5.stock.commodity.service.CommodityService;
import com.rxv5.stock.entity.Commodity;
import com.rxv5.stock.entity.Supplier;

@Controller
@Namespace("/commodity")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class CommodityAction extends BaseActionSupport {

	private static final long serialVersionUID = 8829908991898687290L;

	private Commodity commodity;

	@Resource
	private CommodityService commodityService;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/commodity/query.jsp");
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

		Map<String, Object> result = commodityService.query(param, page, rows, sort, order);
		Long total = (Long) result.get("total");
		List<Supplier> list = (List<Supplier>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "id");
		map.put("name", "name");
		map.put("py", "py");
		map.put("type", "type");
		map.put("remark", "remark");

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
			Commodity commodity = commodityService.get(id);
			getRequest().setAttribute("commodity", commodity);
		}
		return dispatcher("/WEB-INF/stock/commodity/edit.jsp");
	}

	@Action(value = "save")
	public void save() throws Exception {
		SuccessOrFailure result = SuccessOrFailure.SUCCESS;
		try {
			commodityService.saveModify(commodity);
		} catch (Exception e) {
			result = SuccessOrFailure.FAILURE;
			e.printStackTrace();
		}
		HttpUtils.write(JsonUtils.toJsonString(result));
	}

	@Action(value = "choose")
	public String choose() throws Exception {
		return dispatcher("/WEB-INF/stock/commodity/choose.jsp");
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

}
