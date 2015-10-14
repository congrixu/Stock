package com.rxv5.stock.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rxv5.stock.bean.SalesBean;
import com.rxv5.stock.bean.SalesUserBean;
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

	public Map<String, Object> sumCommodity(String name, String startDate, String endDate, final Integer page,
			final Integer rows) {

		List<SalesBean> sblist = new ArrayList<SalesBean>();
		Map<String, Object> result = reportDao.sumCommodity(name, startDate, endDate, (page - 1) * rows, rows);
		List<?> list = (List<?>) result.get("list");
		if (list != null && list.size() > 0) {
			for (Object objs : list) {
				Object[] obj = (Object[]) objs;
				String commodityId = (String) obj[0];
				String commodityName = (String) obj[1];
				String commodityType = (String) obj[2];
				Long num = obj[3] == null ? 0l : Long.valueOf(String.valueOf(obj[3]));
				Double totalPrice = obj[4] == null ? 0d : Double.valueOf(String.valueOf(obj[4]));
				Long storageNum = obj[5] == null ? 0l : Long.valueOf(String.valueOf(obj[5]));

				SalesBean sb = new SalesBean();
				sb.setCommodityId(commodityId);
				sb.setCommodityName(commodityName);
				sb.setCommodityType(commodityType);
				sb.setStorageNum(storageNum);
				sb.setTotalNum(num);
				sb.setTotalPrice(totalPrice);
				sblist.add(sb);
			}
			result.put("list", sblist);
		}

		return result;
	}

	public Map<String, Object> sumSalUser(String userName, String startDate, String endDate, final Integer page,
			final Integer rows) {

		List<SalesUserBean> sblist = new ArrayList<SalesUserBean>();
		Map<String, Object> result = reportDao.sumSalUser(userName, startDate, endDate, (page - 1) * rows, rows);
		List<?> list = (List<?>) result.get("list");
		if (list != null && list.size() > 0) {
			for (Object objs : list) {
				Object[] obj = (Object[]) objs;
				Double total = obj[0] == null ? 0d : Double.valueOf(String.valueOf(obj[0]));
				Double count = obj[1] == null ? 0 : Double.valueOf(String.valueOf(obj[1]));
				String userId = (String) obj[2];
				String _userName = (String) obj[3];

				SalesUserBean sb = new SalesUserBean();
				sb.setTotal(total);
				sb.setCount(count);
				sb.setUserId(userId);
				sb.setUserName(_userName);
				sblist.add(sb);
			}
			result.put("list", sblist);
		}

		return result;
	}

	public Map<String, Object> sumFitterUser(String userName, String startDate, String endDate, final Integer page,
			final Integer rows) {

		List<SalesUserBean> sblist = new ArrayList<SalesUserBean>();
		Map<String, Object> result = reportDao.sumFitterUser(userName, startDate, endDate, (page - 1) * rows, rows);
		List<?> list = (List<?>) result.get("list");
		if (list != null && list.size() > 0) {
			for (Object objs : list) {
				Object[] obj = (Object[]) objs;
				Double count = obj[0] == null ? 0 : Double.valueOf(String.valueOf(obj[0]));
				String userId = (String) obj[1];
				String _userName = (String) obj[2];

				SalesUserBean sb = new SalesUserBean();
				sb.setCount(count);
				sb.setUserId(userId);
				sb.setUserName(_userName);
				sblist.add(sb);
			}
			result.put("list", sblist);
		}

		return result;
	}

}
