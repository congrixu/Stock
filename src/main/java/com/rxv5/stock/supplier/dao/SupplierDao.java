package com.rxv5.stock.supplier.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.entity.Supplier;

@Repository
public class SupplierDao extends BaseHibernateDao {

	public Map<String, Object> select(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<Supplier> list = new ArrayList<Supplier>();

		String key = "";

		String hql = "from Supplier where 1=1 ";

		if (param != null && param.size() > 0) {
			String name = param.get("name");
			if (name != null && name.length() > 0)
				key = key + " and name like '%" + name + "%'";

			String py = param.get("py");
			if (py != null && py.length() > 0)
				key = key + " and py = '" + py + "'";

		}

		Long total = selectUniqueResult("select count(*) " + hql + key);

		if (total > 0) {
			if (sort != null && order != null)
				key = key + " order by " + sort + " " + order;
			list = selectList(hql + key, page, rows);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("list", list);
		return result;
	}

	public Supplier selectOne(String id) {
		String hql = "from Supplier where id = '" + id + "'";
		return selectUniqueResult(hql);
	}

}
