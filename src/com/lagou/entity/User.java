package com.lagou.entity;

/**
 * 普通用戶实体
 * 
 * @author Administrator
 *
 */
public class User {

	private int u_id;
	private String u_account;
	private String u_password;
	private Resume resume;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_account() {
		return u_account;
	}

	public void setU_account(String u_account) {
		this.u_account = u_account;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public User(String u_account, String u_password) {
		super();
		this.u_account = u_account;
		this.u_password = u_password;
	}

	public User() {
		super();
	}

}
