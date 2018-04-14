package com.lagou.entity;

/**
 * 管理员实体
 * 
 * @author Administrator
 *
 */
public class Admin {
	private int a_id;// 管理员id
	private String a_account;// 管理员账号
	private String a_password;// 管理员密码

	public void setID(int id) {
		this.a_id = id;
	}

	public void setAccount(String account) {
		this.a_account = account;
	}

	public void setPassword(String password) {
		a_password = password;
	}

	public int getID() {
		return a_id;
	}

	public String getAccount() {
		return a_account;
	}

	public String getPassword() {
		return a_password;
	}

}
