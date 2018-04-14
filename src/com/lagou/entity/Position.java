package com.lagou.entity;

/**
 * 职位实体
 */
import java.util.Arrays;

public class Position {
	@Override
	public String toString() {
		return "Position [p_id=" + p_id + ", p_name=" + p_name + ", p_desc=" + p_desc + ", p_release=" + p_release
				+ ", p_salary=" + p_salary + ", p_experience=" + p_experience + ", p_education=" + p_education
				+ ", company=" + company + ", p_personNum=" + p_personNum + ", p_state=" + p_state + ", p_category="
				+ Arrays.toString(p_category) + ", p_status=" + p_status + "]";
	}

	private int p_id;
	private String p_name;
	private String p_desc;
	private String p_release;
	private double p_salary;
	private String p_experience;
	private String p_education;

	private Company company;

	private int p_personNum;
	private int p_state;
	private String[] p_category;
	private int p_status;

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public String getP_release() {
		return p_release;
	}

	public void setP_release(String p_release) {
		this.p_release = p_release;
	}

	public double getP_salary() {
		return p_salary;
	}

	public void setP_salary(double p_salary) {
		this.p_salary = p_salary;
	}

	public String getP_experience() {
		return p_experience;
	}

	public void setP_experience(String p_experience) {
		this.p_experience = p_experience;
	}

	public String getP_education() {
		return p_education;
	}

	public void setP_education(String p_education) {
		this.p_education = p_education;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getP_personNum() {
		return p_personNum;
	}

	public void setP_personNum(int p_personNum) {
		this.p_personNum = p_personNum;
	}

	public int getP_state() {
		return p_state;
	}

	public void setP_state(int p_state) {
		this.p_state = p_state;
	}

	public String[] getP_category() {
		return p_category;
	}

	public void setP_category(String[] p_category) {
		this.p_category = p_category;
	}

	public int getP_status() {
		return p_status;
	}

	public void setP_status(int p_status) {
		this.p_status = p_status;
	}

}
