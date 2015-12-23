package com.zd.dao;

import com.zd.model.User;

public interface IUserDao extends IBaseDao<User>{
	 public abstract User login(User user);
	 User byId(Class<User> cls,int id);
}
