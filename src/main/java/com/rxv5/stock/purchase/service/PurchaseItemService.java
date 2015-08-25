package com.rxv5.stock.purchase.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.entity.PurchaseItem;
import com.rxv5.stock.purchase.dao.PurchaseDao;
import com.rxv5.stock.purchase.dao.PurchaseItemDao;

@Service
public class PurchaseItemService {

	@Resource
	private PurchaseItemDao purchaseItemDao;

	@Resource
	private PurchaseDao purchaseDao;

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
		String purchaseId = pt.getPurchaseOrder().getId();
		purchaseItemDao.delete(pt);
		sumTotal(purchaseId);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(PurchaseItem item) {
		String id = item.getId();
		Double price = item.getPrice() == null ? 0d : item.getPrice();
		Integer num = item.getNum() == null ? 0 : item.getNum();
		if (id == null || id.trim().length() <= 0) {
			item.setTotalPrice(price * num);
			purchaseItemDao.save(item);
		} else {
			PurchaseItem _item = purchaseItemDao.selectOne(id);
			_item.setCdy(item.getCdy());
			_item.setClothes(item.getClothes());
			_item.setNum(item.getNum());
			_item.setPrice(item.getPrice());
			_item.setTotalPrice(price * num);
			purchaseItemDao.update(_item);
		}

		sumTotal(item.getPurchaseOrder().getId());
	}

	private void sumTotal(String purchaseId) {
		Double total = purchaseItemDao.selectSumTotal(purchaseId);
		purchaseDao.updateTotal(purchaseId, total);
	}

	public List<PurchaseItem> queryByPurchaseId(String purchaseId) {
		return purchaseItemDao.selectByPurchaseId(purchaseId);
	}
}
