package com.rxv5.stock.report.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;

@SuppressWarnings( { "rawtypes" })
@Repository
public class ReportDao extends BaseHibernateDao {

	public List sumTotal(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		final String queryString = sb.append("select ").append(
				" sum(count_) count_,sum(total_num) total_num,sum(total_price) total_price,type_ ").append(
				" FROM V_TOTAL_INFO ").append(" where  operdate>='" + startDate + " 00:00:00' ").append(
				" and operdate<='" + endDate + " 23:59:59' ").append(" GROUP BY type_ ").toString();
		return (List) getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(queryString).list();
			}
		});

	}

	/**
	 * 统计商品信息
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List sumCommodity(String startDate, String endDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select item.commodity_id,c.name_,sum(item.num_),sum(item.price_) ,ts.num_ ");
		sb.append(" from t_sales_item item,t_sales_order sorder,t_commodity c,t_storage ts ");
		sb.append(" where item.commodity_id = c.commodity_id ");
		sb.append(" and item.commodity_id = ts.commodity_id ");
		sb.append(" and  sorder.sales_date>='" + startDate + " 00:00:00' ").append(
				" and sorder.sales_date<='" + endDate + " 23:59:59' ");
		sb.append(" group by item.commodity_id,c.name_,ts.num_ ");
		sb.append(" order by sum(item.num_) DESC ");
		final String queryString = sb.toString();
		return (List) getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(queryString).list();
			}
		});
	}

}
