package com.zd.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zd.biz.IBaseBiz;
import com.zd.dao.IBaseDao;

public  class BaseBiz<T> implements IBaseBiz<T> {

	IBaseDao<T> baseDao;

	
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public boolean add(T t) {
		return baseDao.add(t);
	}

	public boolean del(T t) {
		return baseDao.del(t);
	}

	public boolean update(T t) {
		return baseDao.update(t);
	}

	public int count() {
		String hql="";
		Map<?, ?> map=new HashMap<String, String>();
		return baseDao.getCount(hql, map);
	}

	public T get(Class<T> clas,long id) {
		return baseDao.get(clas, id);
	}

	public List<T> getAll(int index) {
		String hql="";
		int pageSize=5;
		Map<String, String> map = new HashMap<String,String>();
		return baseDao.pageList(hql, index, pageSize, map);

	}



}
