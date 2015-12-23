package com.zd.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	/**
	 * 增
	 * @param t
	 * @return
	 */
	boolean add(T t);
	/**
	 * 删
	 * @param t
	 * @return
	 */
	boolean del(T t);
	/**
	 * 改
	 * @param t
	 * @return
	 */
	boolean update(T t);
	/**
	 * 查
	 * @param cls
	 * @param id
	 * @return
	 */
	T get(Class<T> cls, long id);
	/**
	 * 分页
	 * @param hql
	 * @param index
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<T> pageList(final String hql, final int index,
			final int pageSize, final Map<?, ?>... map);
	/**
	 * 数据量
	 * @return
	 */
	int getCount(String hql,Map<?, ?>... map);
	/**
	 * 获得所有数据
	 * @return
	 */
	List<T> getAll(String hql);

}
