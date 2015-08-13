package com.rxv5.stock.login.dao;

import org.springframework.stereotype.Repository;

import com.rxv5.platform.common.BaseHibernateDao;
import com.rxv5.stock.entity.User;

@Repository
public class LoginDao extends BaseHibernateDao {

	public User selectUserById(String userId) throws Exception {
		String hql = "from User where userId='" + userId + "'";
		return selectUniqueResult(hql);
	}
}
