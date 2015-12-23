package com.zd.biz.impl;

import java.util.List;

import com.zd.aop.annotation.BizAnnotation;
import com.zd.biz.IUserBiz;
import com.zd.dao.IUserDao;
import com.zd.model.User;

public class UserBiz implements IUserBiz {
	IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@BizAnnotation(moduleName = "系统管理", operation = "用户登录")
	public User login(User user) {
		return userDao.login(user);
	}

	public List<User> conseillerList() {
		// TODO Auto-generated method stub
		String hql="select u from User u inner join u.roles r where r.id=2 ";
		return userDao.getAll(hql);
	}

	public List<User> lecturerList() {
		try {
			String hql="select u from User u inner join u.roles r where r.id=1 ";
			return userDao.getAll(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User byId(Class<User> cls,int id) {
		// TODO Auto-generated method stub
		return userDao.byId(cls, id);
	}
}
