package com.rxv5.stock.purchase.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.Constant.PurchaseEnum;
import com.rxv5.stock.entity.PurchaseOrder;
import com.rxv5.stock.purchase.dao.PurchaseDao;

@Service
@SuppressWarnings( { "unchecked" })
public class PurchaseService {

	@Resource
	private PurchaseDao purchaseDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		Map<String, Object> result = purchaseDao.select(param, (page - 1) * rows, rows, sort, order);
		List<PurchaseOrder> list = (List<PurchaseOrder>) result.get("list");
		if (list != null && list.size() > 0) {
			for (PurchaseOrder po : list) {
				String state = po.getState();
				po.setStateStr(PurchaseEnum.byId(state).getText());
			}
		}
		return result;
	}

	public PurchaseOrder get(String id) {
		return purchaseDao.selectOne(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		PurchaseOrder purchase = purchaseDao.selectOne(id);
		purchase.setState(PurchaseEnum.delete.getId());
		purchaseDao.update(purchase);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(PurchaseOrder purchase) {
		String id = purchase.getId();
		if (id == null || id.trim().length() <= 0) {
			purchase.setState(PurchaseEnum.onTheway.getId());
			purchase.setCreateDate(new Date());
			purchaseDao.save(purchase);
		} else {
			PurchaseOrder _purchase = purchaseDao.selectOne(id);
			_purchase.setRemark(purchase.getRemark());
			_purchase.setSupplier(purchase.getSupplier());
			purchaseDao.update(_purchase);
		}
	}
}
