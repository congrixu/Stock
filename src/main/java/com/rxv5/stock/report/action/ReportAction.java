package com.rxv5.stock.report.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rxv5.platform.common.BaseActionSupport;
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

}
