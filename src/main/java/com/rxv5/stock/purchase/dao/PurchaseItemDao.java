package com.rxv5.stock.purchase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.entity.PurchaseItem;
import com.rxv5.stock.entity.PurchaseOrder;

@Repository
public class PurchaseItemDao extends BaseHibernateDao {
	public Map<String, Object> select(String purchaseId, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();

		String key = "";

		String hql = "from PurchaseItem where purchaseOrder.id='" + purchaseId + "' ";

		Long total = selectUniqueResult("select count(*) " + hql + key);

		if (total > 0) {
			if (sort != null && order != null) {
				key = key + " order by " + sort + " " + order;
			}
			list = selectList(hql + key, page, rows);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("list", list);
		return result;
	}

	public PurchaseItem selectOne(String id) {
		String hql = "from PurchaseItem where id = '" + id + "'";
		return selectUniqueResult(hql);
	}

	public Double selectSumTotal(String purchaseId) {
		String hql = "select sum(totalPrice) from PurchaseItem where purchaseOrder.id='" + purchaseId + "'";
		Object obj = getSession().createQuery(hql).uniqueResult();
		return obj == null ? 0d : Double.valueOf(obj.toString());
	}
}