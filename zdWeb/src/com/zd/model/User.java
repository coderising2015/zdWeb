package com.zd.model;

import java.io.Serializable;

import java.util.Set;

public class User implements Serializable {
	private int id;// 用户id
	private String username;// 用户名
	private String password;// 密码
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
