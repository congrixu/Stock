package com.rxv5.stock.report.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rxv5.stock.bean.TotalBean;
import com.rxv5.stock.report.dao.ReportDao;

@Service
public class ReportService {

	@Resource
	private ReportDao reportDao;

	public TotalBean sumTotal(String startDate, String endDate) {
		TotalBean tb = new TotalBean();

		if (startDate == null || startDate.length() <= 0 || endDate == null || endDate.length() <= 0) {
			return tb;
		}
		List<?> list = reportDao.sumTotal(startDate, endDate);
		if (list != null && list.size() > 0) {
			for (Object obj : list) {

				Object[] _obj = (Object[]) obj;

				Object count = _obj[0];
				count = count == null || String.valueOf(count).length() <= 0 ? 0 : count;
				Object totalNum = _obj[1];
				totalNum = totalNum == null || String.valueOf(totalNum).length() <= 0 ? 0 : totalNum;
				Object totalPrice = _obj[2];
				totalPrice = totalPrice == null || String.valueOf(totalPrice).length() <= 0 ? 0 : totalPrice;

				String type = (String) _obj[3];
				if ("sales".equals(type)) {
					//销售
					tb.setTotalSalesCount(Long.valueOf(count.toString()));
					tb.setTotalSalesNum(Long.valueOf(totalNum.toString()));
					tb.setTotalSalesPrice(Double.valueOf(totalPrice.toString()));
				} else if ("purchase".equals(type)) {
					//采购
					tb.setTotalPurchaseCount(Long.valueOf(count.toString()));
					tb.setTotalPurchaseNum(Long.valueOf(totalNum.toString()));
					tb.setTotalPurchasePrice(Double.valueOf(totalPrice.toString()));
				} else if ("gift".equals(type)) {
					//赠送
					tb.setGiftCount(Long.valueOf(count.toString()));
					tb.setGiftNum(Long.valueOf(totalNum.toString()));
					tb.setTotalGiftPurchasePrice(Double.valueOf(totalPrice.toString()));
				}
			}
		}
		return tb;
	}
}
