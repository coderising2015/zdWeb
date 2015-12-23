package com.zd.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;

import com.zd.dao.IUserDao;
import com.zd.model.User;



public class UserDao extends  BaseDao<User> implements IUserDao{

	public  User login(User user){
    
    	try {
			Session session = this.getSession();
			Query query = session.createQuery("from User where username=? and password=?");
			query.setString(0, user.getUsername());
			query.setString(1, user.getPassword());
			return (User) query.uniqueResult();
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;	
    }

	public User byId(Class<User> cls, int id) {
		
		return null;
	}





}
