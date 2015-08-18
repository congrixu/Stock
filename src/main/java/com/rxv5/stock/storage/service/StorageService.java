package com.rxv5.stock.storage.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.entity.Inventory;
import com.rxv5.stock.entity.Storage;
import com.rxv5.stock.storage.dao.StorageDao;

@Service
public class StorageService {

	@Resource
	private StorageDao storageDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return storageDao.select(param, (page - 1) * rows, rows, sort, order);
	}

	@Transactional(rollbackFor = Exception.class)
	public void inventory(Inventory inv) {

		inv.setCreateDate(new Date());
		storageDao.save(inv);

		String cdyId = inv.getCdy().getId();
		Storage storage = storageDao.get(cdyId);
		storage.setNum(inv.getNum());
		storageDao.update(storage);
	}

}
