package com.rxv5.stock.sales.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.entity.SalesItem;
import com.rxv5.stock.sales.dao.SalesDao;
import com.rxv5.stock.sales.dao.SalesItemDao;

@Service
public class SalesItemService {

	@Resource
	private SalesItemDao salesItemDao;

	@Resource
	private SalesDao salesDao;

	public Map<String, Object> query(String salesId, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return salesItemDao.select(salesId, (page - 1) * rows, rows, sort, order);
	}

	public SalesItem get(String id) {
		return salesItemDao.get(SalesItem.class, id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String id) {
		SalesItem si = salesItemDao.get(SalesItem.class, id);
		String salesId = si.getSalesOrder().getId();
		salesItemDao.delete(si);
		sumTotal(salesId);
		//修改库存
		//modifyAddStorage(si);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveOrModify(SalesItem item) {
		String id = item.getId();
		Double price = item.getPrice() == null ? 0d : item.getPrice();
		Integer num = item.getNum() == null ? 0 : item.getNum();
		if (id == null || id.trim().length() <= 0) {
			item.setTotalPrice(price * num);
			salesItemDao.save(item);
			//modifySubStorage(item.getCommodity().getId(), num);
		} else {
			SalesItem _item = get(id);
			//1.先将库存恢复
			//modifyAddStorage(_item);

			_item.setCommodity(item.getCommodity());
			_item.setNum(item.getNum());
			_item.setPrice(item.getPrice());
			_item.setSup(item.getSup());
			_item.setTotalPrice(price * num);
			salesItemDao.update(_item);

			////2.再将新修改的库存从库存中减去
			//modifySubStorage(item.getCommodity().getId(), num);
		}

		sumTotal(item.getSalesOrder().getId());
	}

	private void sumTotal(String salesId) {
		Object[] total = salesItemDao.selectSumTotal(salesId);
		Object totalNum = total[0];
		Integer _totalNum = totalNum == null ? 0 : Integer.valueOf(String.valueOf(totalNum));
		Object totalPrice = total[1];
		Double _totalPrice = totalPrice == null ? 0d : Double.valueOf(String.valueOf(totalPrice));
		salesDao.updateTotal(salesId, _totalNum, _totalPrice);
	}

	public List<SalesItem> queryBySalesId(String salesId) {
		return salesItemDao.selectBySalesId(salesId);
	}

}
