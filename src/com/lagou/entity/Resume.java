package com.lagou.entity;

/**
 * 简历实体
 * 
 * @author Administrator
 *
 */
public class Resume {

	private int r_id;
	private String r_name;
	private String r_header;
	private String r_degree;
	private String edu_exp;
	private String project_exp;
	private String self_avalue;
	private String r_category;
	private String sex;
	private String email;
	private User user;

	public Resume() {
		super();
	}

	public Resume(String r_name) {
		super();
		this.r_name = r_name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_header() {
		return r_header;
	}

	public void setR_header(String r_header) {
		this.r_header = r_header;
	}

	public String getR_degree() {
		return r_degree;
	}

	public void setR_degree(String r_degree) {
		this.r_degree = r_degree;
	}

	public String getEdu_exp() {
		return edu_exp;
	}

	public void setEdu_exp(String edu_exp) {
		this.edu_exp = edu_exp;
	}

	public String getProject_exp() {
		return project_exp;
	}

	public void setProject_exp(String project_exp) {
		this.project_exp = project_exp;
	}

	public String getSelf_avalue() {
		return self_avalue;
	}

	public void setSelf_avalue(String self_avalue) {
		this.self_avalue = self_avalue;
	}

	public String getR_category() {
		return r_category;
	}

	public void setR_category(String r_category) {
		this.r_category = r_category;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
