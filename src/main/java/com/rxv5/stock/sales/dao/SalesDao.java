package com.rxv5.stock.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.Constant.SalesEnum;
import com.rxv5.stock.entity.SalesOrder;

@Repository
public class SalesDao extends BaseHibernateDao {

	public Map<String, Object> select(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<SalesOrder> list = new ArrayList<SalesOrder>();

		String key = "";

		String hql = "from SalesOrder where state<>'" + SalesEnum.delete.getId() + "' ";

		if (param != null && param.size() > 0) {
			String startDate = param.get("startDate");
			if (startDate != null && startDate.length() > 0) {
				key = key + " and salesDate >='" + startDate + " 00:00:00' ";
			}

			String endDate = param.get("endDate");
			if (endDate != null && endDate.length() > 0) {
				key = key + " and salesDate <='" + endDate + " 23:59:59' ";
			}

			String clientName = param.get("clientName");
			if (clientName != null && clientName.length() > 0)
				key = key + " and clientName like '%" + clientName + "%'";

			String clientPhone = param.get("clientPhone");
			if (clientPhone != null && clientPhone.length() > 0)
				key = key + " and clientPhone like '%" + clientPhone + "%'";
		}

		Long total = selectUniqueResult("select count(*) " + hql + key);

		if (total > 0) {
			if (sort != null && order != null) {
				key = key + " order by " + sort + " " + order;
			} else {
				key = key + " order by salesDate DESC ";
			}
			list = selectList(hql + key, page, rows);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("list", list);
		return result;
	}

	public void updateTotal(String id, Integer totalNum, Double totalPrice) {
		SalesOrder so = get(SalesOrder.class, id);
		so.setTotalPrice(totalPrice);
		so.setTotalNum(totalNum);
		update(so);
	}

}
