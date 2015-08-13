package com.rxv5.stock.supplier.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.entity.Supplier;
import com.rxv5.stock.supplier.dao.SupplierDao;

@Service
public class SupplierService {

	@Resource
	private SupplierDao supplierDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return supplierDao.select(param, (page - 1) * rows, rows, sort, order);
	}

	public Supplier get(String id) {
		return supplierDao.selectOne(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveModify(Supplier supplier) {
		String id = supplier.getId();
		if (id == null || id.trim().length() <= 0) {
			supplierDao.save(supplier);
		} else {
			supplierDao.update(supplier);
		}
	}

}
