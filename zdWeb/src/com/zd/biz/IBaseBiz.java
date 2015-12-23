package com.zd.biz;

import java.util.List;

public interface IBaseBiz<T> {
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
	 * @param id
	 * @return
	 */
	T get(Class<T> clas,long id);
	/**
	 * 分页
	 * @return
	 */
	List<T> getAll(int index);
	/**
	 * 数据量
	 * @return
	 */
	int count();
}
