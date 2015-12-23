package com.zd.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.zd.biz.IUserBiz;
import com.zd.model.User;

public class UserAction  {
	IUserBiz userBiz;
	User user;
	public void setUserBiz(IUserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	/****
	 * �û�����
	 * 
	 * @return
	 */
	public String login() {
		//System.out.println(user.getU_name() + user.getPwd());
		User user1 = userBiz.login(user);
		//System.out.println(user + "===");
		HttpServletRequest request = ServletActionContext.getRequest();
		//String pwd = request.getParameter("user.pwd");
		//System.out.println(pwd);
		HttpSession session = request.getSession();
		if (user1 != null ) {
			session.setAttribute("user", user1);
			return "true";
		}
		return "false";
	}
	/***
	 * 注销
	 */
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "true";
	}

}
