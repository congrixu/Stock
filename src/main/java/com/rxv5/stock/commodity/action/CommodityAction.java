package com.rxv5.stock.commodity.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.rxv5.platform.common.BaseActionSupport;

@Controller
@Namespace("/commodity")
@Scope("prototype")
@ParentPackage("default")
public class CommodityAction extends BaseActionSupport {

	private static final long serialVersionUID = 8829908991898687290L;

	@Action(value = "query")
	public String query() throws Exception {
		return dispatcher("/WEB-INF/stock/commodity/query.jsp");
	}

}
