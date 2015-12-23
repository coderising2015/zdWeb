package com.zd.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zd.dao.IBaseDao;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	/**
	 *增加数据
	 * 
	 * @param t
	 * @return
	 */
	public boolean add(T t) {
		try {
			this.getHibernateTemplate().save(t);
			this.getHibernateTemplate().flush();
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改数据
	 * 
	 * @param t
	 * @return
	 */
	public boolean update(T t) {
		try {
			this.getHibernateTemplate().update(t);
//			this.getHibernateTemplate().merge(t);
			this.getHibernateTemplate().flush();
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ͨ通过ID获得一条数据
	 * 
	 * @param clas
	 * @param id
	 * @return
	 */
	public T get(Class<T> clas, long id) {
		return (T) this.getHibernateTemplate().get(clas, id);
	}

	/**
	 * 获取数据列表
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(String hql) {
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * 删除数据
	 * 
	 * @param t
	 * @return
	 */
	public boolean del(T t) {
		try {
			this.getHibernateTemplate().delete(t);
			this.getHibernateTemplate().flush();
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            hql语句
	 * @param index
	 *            第几页了
	 * @param pageSize
	 *            每一页有多少条数据
	 * @param map
	 *            ����Ĳ���
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> pageList(final String hql, final int index,
			final int pageSize, final Map<?, ?>... map) {
		return this.getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (map != null && map.length > 0)
							query.setProperties(map[0]);
						int start = (index - 1) * pageSize;
						query.setFirstResult(start);
						query.setMaxResults(pageSize);
						List<T> list = query.list();
						return list;
					}
				});
	}

	/**
	 * 数据库有多少条数据
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public int getCount(final String hql, final Map<?, ?>... map) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						if (map != null && map.length > 0)
							query.setProperties(map[0]);
						int res = Integer.parseInt((query.uniqueResult()
								.toString()));
						return res;
					}
				});
	}

	/**
	 * 通过SQL语句获得数据库有多少条数据
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	public Object getCountBySql(final String sql, final Map<?, ?>... map) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if (map != null && map.length > 0)
							query.setProperties(map[0]);
						return query.uniqueResult();
					}
				});
	}

	/**
	 * 通过SQL语句分页查询
	 * 
	 * @param sql
	 * @param index
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public Object pageListBySql(final String sql, final int index,
			final int pageSize, final Map<?, ?>... map) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						int start = (index - 1) * pageSize;
						query.setFirstResult(start);
						query.setMaxResults(pageSize);
						if (map != null && map.length > 0)
							query.setProperties(map[0]);
						return query.list();
					}
				});
	}

	public int getCount(final Class<?> clas, final Criterion... criterions) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {

					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(clas);
						for (Criterion criterion : criterions) {
							criteria.add(criterion);
						}
						criteria.setProjection(Projections.rowCount());
						int count = (Integer) criteria.uniqueResult();
						return count;
					}
				});
	}

	/**
	 * 通过条件查询一条数据
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public T getObject(final String hql, final Map<?, ?> map) {
		System.out.println(this.getHibernateTemplate());
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if (map != null)
					query.setProperties(map);
				System.out.println(query);
				return (T) query.uniqueResult();
			}
		});
	}

	public List<T> getList(final String hql, final Map<?, ?>... map) {
		return (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						try {
							Query query = session.createQuery(hql);
							if (map != null && map.length > 0)
								query.setProperties(map);
							return (List<T>) query.list();
						} catch (Throwable e) {
							e.printStackTrace();
						}
						return null;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(T t) {
		return this.getHibernateTemplate().findByExample(t);
	}

	public T getObject(final Class<T> cls, final Criterion... criterions) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Criteria criteria = session.createCriteria(cls);
				if (criterions != null)
					for (Criterion criterion : criterions) {
						criteria.add(criterion);
					}
				return (T) criteria.uniqueResult();
			}
		});
	}

	public List<T> getList(final Class<T> cls, final Criterion... criterions) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(cls);
						if (criterions != null)
							for (Criterion criterion : criterions) {
								criteria.add(criterion);
							}
						return (List<T>) criteria.list();
					}
				});
	}

	public List<T> getList(final Class<T> cls, final int index,
			final int pageSize, final Criterion... criterions) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {

					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(cls);
						if (criterions != null)
							for (Criterion criterion : criterions) {
								criteria.add(criterion);
							}
						criteria.setFirstResult(index);
						criteria.setMaxResults(pageSize);
						return (List<T>) criteria.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<T> getListBySql(final String sql, final Map map,
			final Class<T> cls) {
		return this.getHibernateTemplate().executeFind(
				new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql).addEntity(
								cls);
						if (map != null)
							query.setProperties(map);
						return query.list();
					}
				});
	}

	public Connection getConnection() throws SQLException {
		return SessionFactoryUtils.getDataSource(this.getSessionFactory())
				.getConnection();
	}
}
