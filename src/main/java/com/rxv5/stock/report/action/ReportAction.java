package com.rxv5.stock.report.action;

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
import com.rxv5.platform.util.SendData;
import com.rxv5.stock.Constant;
import com.rxv5.stock.bean.SalesBean;
import com.rxv5.stock.bean.TotalBean;
import com.rxv5.stock.report.service.ReportService;

/**
 * 报表统计
 * @author congrixu
 *
 */
@Controller
@Namespace("/report")
@Scope("prototype")
@ParentPackage("default")
@SuppressWarnings("unchecked")
public class ReportAction extends BaseActionSupport {

	private static final long serialVersionUID = 2672436906134129086L;

	@Resource
	private ReportService reportService;

	@Action(value = "sumtotal")
	public String sumTotal() {
		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");
		TotalBean tb = reportService.sumTotal(startDate, endDate);
		getRequest().setAttribute("tb", tb);
		getRequest().setAttribute("startDate", startDate);
		getRequest().setAttribute("endDate", endDate);
		return dispatcher("/WEB-INF/stock/report/sumtotal.jsp");
	}

	@Action(value = "sumcommodityview")
	public String sumCommodityView() throws Exception {
		return dispatcher("/WEB-INF/stock/report/sumcommodity.jsp");
	}

	@Action(value = "sumcommodity")
	public void sumCommodity() {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));
		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");
		String name = getParameterFromRequest("name");

		Map<String, Object> result = reportService.sumCommodity(name, startDate, endDate, page, rows);
		Long total = (Long) result.get("total");
		List<SalesBean> list = (List<SalesBean>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("commodityId", "commodityId");
		map.put("commodityName", "commodityName");
		map.put("commodityType", "commodityType");
		map.put("totalNum", "totalNum");
		map.put("totalPrice", "totalPrice");
		map.put("storageNum", "storageNum");

		new SendData().sendDataJson(map, list, total, getResponse());
	}

	/**
	 * 销售人员销售情况统计
	 */
	@Action(value = "sumsaluserview")
	public String sumSalUserView() throws Exception {
		return dispatcher("/WEB-INF/stock/report/sumsaluser.jsp");
	}

	@Action(value = "sumsaluser")
	public void sumSalUser() {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));
		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");
		String userName = getParameterFromRequest("userName");
		Map<String, Object> result = reportService.sumSalUser(userName, startDate, endDate, page, rows);

		Long total = (Long) result.get("total");
		List<SalesBean> list = (List<SalesBean>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("total", "####.####");
		map.put("count", "####");
		map.put("userId", "userId");
		map.put("userName", "userName");
		map.put("salName", "salName");
		map.put("salType", "salType");
		new SendData().sendDataJson(map, list, total, getResponse());
	}

	/**
	 * 安装人员统计
	 * @return
	 * @throws Exception
	 */
	@Action(value = "sumfitteruserview")
	public String sumFitteruserView() throws Exception {
		return dispatcher("/WEB-INF/stock/report/sumfitteruser.jsp");
	}

	@Action(value = "sumfitteruser")
	public void sumFitteruser() {
		Integer page = getRequest().getParameter("page") == null ? 1 : Integer.valueOf(getRequest()
				.getParameter("page"));
		Integer rows = getRequest().getParameter("rows") == null ? Constant.DEFAULT_PAGE_SIZE : Integer
				.valueOf(getRequest().getParameter("rows"));
		String startDate = getParameterFromRequest("startDate");
		String endDate = getParameterFromRequest("endDate");
		String userName = getParameterFromRequest("userName");
		Map<String, Object> result = reportService.sumFitterUser(userName, startDate, endDate, page, rows);

		Long total = (Long) result.get("total");
		List<SalesBean> list = (List<SalesBean>) result.get("list");
		Map<String, String> map = new HashMap<String, String>();
		map.put("count", "####");
		map.put("userId", "userId");
		map.put("userName", "userName");
		map.put("salName", "salName");
		map.put("salType", "salType");
		new SendData().sendDataJson(map, list, total, getResponse());
	}

}
