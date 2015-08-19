package com.rxv5.stock.sales.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.entity.SalesItem;

@Repository
public class SalesItemDao extends BaseHibernateDao {

	public Map<String, Object> select(String salesId, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<SalesItem> list = new ArrayList<SalesItem>();

		String key = "";

		String hql = "from SalesItem where salesOrder.id='" + salesId + "' ";

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

	public Object[] selectSumTotal(String salesId) {
		String hql = "select sum(num), sum(totalPrice) from SalesItem where salesOrder.id='" + salesId + "'";
		Object obj = getSession().createQuery(hql).uniqueResult();
		return (Object[]) obj;
	}

	public List<SalesItem> selectBySalesId(String salesId) {
		String hql = "from SalesItem where salesOrder.id='" + salesId + "' ";
		return selectList(hql, null, null);
	}

}
