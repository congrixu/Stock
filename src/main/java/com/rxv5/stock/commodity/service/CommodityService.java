package com.rxv5.stock.commodity.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.commodity.dao.CommodityDao;
import com.rxv5.stock.entity.Commodity;

@Service
public class CommodityService {

	@Resource
	private CommodityDao commodityDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return commodityDao.select(param, (page - 1) * rows, rows, sort, order);
	}

	public Commodity get(String id) {
		return commodityDao.selectOne(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveModify(Commodity commodity) {
		String id = commodity.getId();
		if (id == null || id.trim().length() <= 0) {
			commodityDao.save(commodity);
		} else {
			commodityDao.update(commodity);
		}
	}

}
