package com.rxv5.stock.purchase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.Constant.PurchaseEnum;
import com.rxv5.stock.entity.PurchaseOrder;

@Repository
public class PurchaseDao extends BaseHibernateDao {
	public Map<String, Object> select(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();

		String key = "";

		String hql = "from PurchaseOrder where state <> '" + PurchaseEnum.delete.getId() + "' ";

		if (param != null && param.size() > 0) {
			String startDate = param.get("startDate");
			if (startDate != null && startDate.length() > 0) {
				key = key + " and createDate >='" + startDate + " 00:00:00' ";
			}

			String endDate = param.get("endDate");
			if (endDate != null && endDate.length() > 0) {
				key = key + " and createDate <='" + endDate + " 23:59:59' ";
			}
		}

		Long total = selectUniqueResult("select count(*) " + hql + key);

		if (total > 0) {
			if (sort != null && order != null) {
				key = key + " order by " + sort + " " + order;
			} else {
				key = key + " order by createDate DESC ";
			}
			list = selectList(hql + key, page, rows);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("list", list);
		return result;
	}

	public PurchaseOrder selectOne(String id) {
		String hql = "from PurchaseOrder where id = '" + id + "'";
		return selectUniqueResult(hql);
	}

	public void updateTotal(String id, Double totalPrice) {
		PurchaseOrder po = selectOne(id);
		po.setTotalPrice(totalPrice);
		update(po);
	}

}