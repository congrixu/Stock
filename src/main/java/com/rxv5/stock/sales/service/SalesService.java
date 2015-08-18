package com.rxv5.stock.sales.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rxv5.stock.Constant.SalesEnum;
import com.rxv5.stock.entity.SalesOrder;
import com.rxv5.stock.sales.dao.SalesDao;

@Service
public class SalesService {

	@Resource
	private SalesDao salesDao;

	public Map<String, Object> query(Map<String, String> param, Integer page, Integer rows, String sort, String order)
			throws Exception {
		return salesDao.select(param, (page - 1) * rows, rows, sort, order);
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
			sales.setState(SalesEnum.undelete.getId());
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
}
