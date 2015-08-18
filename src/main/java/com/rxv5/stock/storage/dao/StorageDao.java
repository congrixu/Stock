package com.rxv5.stock.storage.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.entity.Commodity;
import com.rxv5.stock.entity.Storage;

@Repository
public class StorageDao extends BaseHibernateDao {

	public Map<String, Object> select(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		List<Storage> list = new ArrayList<Storage>();

		String key = "";

		String hql = "from Storage where 1=1 ";

		if (param != null && param.size() > 0) {
			String name = param.get("name");
			if (name != null && name.length() > 0)
				key = key + " and cdy.name like '%" + name + "%'";

			String py = param.get("py");
			if (py != null && py.length() > 0)
				key = key + " and cdy.py = '" + py + "'";

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

	public Storage get(String commodityId) {
		String hql = "from Storage where cdy.id='" + commodityId + "'";
		return selectUniqueResult(hql);
	}

	/**
	 * 增加库存
	 * @param commodityId
	 * @param num
	 */
	public void add(String commodityId, int num) {
		Storage storage = get(commodityId);
		if (storage != null) {
			Integer _num = storage.getNum();
			_num = _num == null ? 0 : _num;
			storage.setNum(_num + num);
			update(storage);
		} else {
			storage = new Storage();
			Commodity cdy = new Commodity();
			cdy.setId(commodityId);
			storage.setCdy(cdy);
			storage.setNum(num);
			save(storage);
		}

	}

	/**
	 * 减少库存
	 * @param commodityId
	 * @param num
	 */
	public void sub(String commodityId, int num) {
		Storage storage = get(commodityId);
		Integer _num = storage.getNum();
		_num = _num == null ? 0 : _num;

		Integer storageNum = _num - num;
		storageNum = storageNum < 0 ? 0 : storageNum;

		storage.setNum(storageNum);
		update(storage);
	}

}
