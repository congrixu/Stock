package com.rxv5.stock.purchase.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.entity.PurchaseItem;
import com.rxv5.stock.purchase.dao.PurchaseItemDao;

@Service
public class PurchaseItemService {

	@Resource
	private PurchaseItemDao purchaseItemDao;

	public Map<String, Object> query(String purchaseId, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return purchaseItemDao.select(purchaseId, (page - 1) * rows, rows, sort, order);
	}

	public PurchaseItem get(String id) {
		return purchaseItemDao.selectOne(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		PurchaseItem pt = get(id);
		purchaseItemDao.delete(pt);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(PurchaseItem item) {
		String id = item.getId();
		if (id == null || id.trim().length() <= 0) {
			purchaseItemDao.save(item);
		} else {
			Double price = item.getPrice() == null ? 0d : item.getPrice();
			Integer num = item.getNum() == null ? 0 : item.getNum();

			PurchaseItem _item = purchaseItemDao.selectOne(id);
			_item.setCdy(item.getCdy());
			_item.setClothes(item.getClothes());
			_item.setNum(item.getNum());
			_item.setPrice(item.getPrice());
			_item.setTotalPrice(price * num);
			purchaseItemDao.update(_item);
		}
	}
}
