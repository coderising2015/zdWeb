package com.zd.biz;

import java.util.List;

import com.zd.model.User;

public interface IUserBiz {
	User login(User user);
	/**
	 * 查询用户中的讲师
	 * @return
	 */
	List<User> lecturerList();
	/**
	 * 查询用户中的职业发展顾问
	 * @return
	 */
	List<User> conseillerList();
	/**
	 * 通过ID获得用户
	 * @param id
	 * @return
	 */
	User byId(Class<User> cls,int id);
}
