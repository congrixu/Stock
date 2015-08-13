package com.rxv5.platform.common;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

@SuppressWarnings( { "unchecked", "rawtypes" })
public abstract class BaseHibernateDao extends HibernateDaoSupport {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public <T> List<T> selectList(final String hql, final Integer start, final Integer limit) {
		List<T> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				List<T> list = null;
				if (start == null || limit == null) {
					list = query.list();
				} else {
					list = query.setFirstResult(start).setMaxResults(limit).list();
				}
				return list;
			}
		});
		return list;
	}

	public <T> T selectUniqueResult(final String hql) {
		T t = (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return (T) session.createQuery(hql).uniqueResult();
			}
		});
		return t;
	}

	/**
	 * 
	 * 用主键检索记录,通用DAO
	 * 
	 * @param persistentClass
	 *            实体类
	 * @param id
	 *            检索主键字段值
	 * @return T，实体类数据
	 */
	public <T> T get(Class<T> persistentClass, Serializable id) {
		T t = (T) this.getSession().get(persistentClass, id);
		// ////getSession().clear();
		return t;
	}

	/**
	 * 
	 * 检索Unique LIST记录,通用DAO,采用Criteria
	 * 
	 * @param persistentClass
	 *            实体类
	 * @param propertyName
	 *            检索字段
	 * @param value
	 *            检索字段值
	 * @return T，实体类数据
	 */
	public <T> T selectUniqueByProperty(Class<T> persistentClass, String propertyName, Object value) {
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propertyName, value));
		T t = (T) criteria.uniqueResult();
		// ////getSession().clear();
		return t;
	}

	/**
	 * 
	 * 检索Object通用DAO,采用Criteria
	 * 
	 * @param startNum
	 *            开始记录数
	 * @param limitNum
	 *            每页记录数
	 * @param dir
	 *            DESC ASC
	 * @param sort
	 *            排序字段
	 * @param persistentClass
	 *            实体类 检索字段map 检索字段方式
	 * @param mapForQuery
	 *            检索字段map
	 * @param mapForCondition
	 *            检索字段方式map
	 * @return Map，数据List和数据记录数
	 */
	public <T> void saveFlush(T bean) {
		this.getHibernateTemplate().save(bean);
		this.getHibernateTemplate().flush();
	}

	/**
	 * 
	 * 检索LIST记录,通用DAO,采用Criteria
	 * 
	 * @param persistentClass
	 *            实体类
	 * @param propertyName
	 *            检索字段
	 * @param value
	 *            检索字段值
	 * @return T，实体类数据
	 */
	public <T> List<T> selectByProperty(Class<T> persistentClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propertyName, value));
		List<T> list = criteria.list();
		// ////getSession().clear();
		return list;
	}

	/**
	 * 
	 * 检索Object一条记录,通用DAO,采用Criteria
	 * 
	 * @param persistentClass
	 *            实体类
	 * @param propertyName
	 *            检索字段
	 * @param value
	 *            检索字段值
	 * @return T，实体类数据
	 */
	public <T> T selectOneByProperty(Class<T> persistentClass, String propertyName, Object value) {
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		criteria.add(Restrictions.eq(propertyName, value));
		List<T> list = criteria.list();
		// getSession().clear();
		if (list.size() > 0) {
			return (T) list.get(0);
		} else {
			return null;

		}

	}

	public <T> void deleteAll(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}

	public <T> void delete(T bean) {
		this.getHibernateTemplate().delete(bean);
	}

	public <T> void save(T bean) {
		this.getHibernateTemplate().save(bean);
	}

	public <T> void saveOrUpdate(T bean) {
		this.getHibernateTemplate().saveOrUpdate(bean);
	}

	public <T> void update(T bean) {
		this.getHibernateTemplate().update(bean);
	}

	public void saveOrUpdateAll(Collection all) {
		this.getHibernateTemplate().saveOrUpdateAll(all);
	}

}
