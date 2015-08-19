package com.rxv5.stock.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.Constant.SalesEnum;
import com.rxv5.stock.entity.SalesItem;
import com.rxv5.stock.entity.SalesOrder;
import com.rxv5.stock.sales.dao.SalesDao;
import com.rxv5.stock.sales.dao.SalesItemDao;
import com.rxv5.stock.storage.dao.StorageDao;

@Service
@SuppressWarnings("unchecked")
public class SalesService {

	@Resource
	private SalesDao salesDao;

	@Resource
	private StorageDao storageDao;

	@Resource
	private SalesItemDao salesItemDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		Map<String, Object> result = salesDao.select(param, (page - 1) * rows, rows, sort, order);
		List<SalesOrder> list = (List<SalesOrder>) result.get("list");
		if (list != null && list.size() > 0) {
			for (SalesOrder so : list) {
				String state = so.getState();
				so.setStateStr(SalesEnum.byId(state).getText());
			}
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		SalesOrder sales = salesDao.get(SalesOrder.class, id);
		sales.setState(SalesEnum.delete.getId());
		salesDao.update(sales);
	}

	public SalesOrder get(String id) {
		return salesDao.get(SalesOrder.class, id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(SalesOrder sales) {
		String id = sales.getId();
		if (id == null || id.trim().length() <= 0) {
			sales.setSalesDate(new Date());
			sales.setState(SalesEnum.newCreate.getId());
			salesDao.save(sales);
		} else {
			SalesOrder _sales = salesDao.get(SalesOrder.class, id);
			_sales.setClientAddr(sales.getClientAddr());
			_sales.setClientName(sales.getClientName());
			_sales.setClientPhone(sales.getClientPhone());
			_sales.setRemark(sales.getRemark());
			salesDao.update(_sales);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void outlib(String salesId) {
		SalesOrder so = salesDao.get(SalesOrder.class, salesId);
		so.setState(SalesEnum.outLib.getId());
		salesDao.update(so);
		List<SalesItem> items = salesItemDao.selectBySalesId(salesId);
		if (items != null && items.size() > 0) {
			for (SalesItem item : items) {
				storageDao.sub(item.getCommodity().getId(), item.getNum());
			}
		}
	}
}
