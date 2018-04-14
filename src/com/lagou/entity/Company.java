package com.lagou.entity;

/**
 * 公司实体
 */
import java.util.ArrayList;

public class Company {
	private int com_id;
	private String com_account;
	private String com_password;
	private String com_name;
	private String com_address;
	private String com_logo;
	private int com_status;
	private String com_reg;
	private String[] com_type;
	private String com_desc;
	private String com_email;
	private String sign;
	private int posistionNum;

	public Company(String com_account, String com_password) {
		super();
		this.com_account = com_account;
		this.com_password = com_password;
	}

	/**
	 * 公司是否通过简历
	 */
	// 通过
	public static final int PASS = 1;
	// 没有通过
	public static final int FAIL = 0;
	/**
	 * 公司发布的职位
	 * 
	 * @return
	 */
	private ArrayList<Position> positions = new ArrayList<>();

	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

	public int getPosistionNum() {
		return posistionNum;
	}

	public void setPosistionNum(int posistionNum) {
		this.posistionNum = posistionNum;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getCom_account() {
		return com_account;
	}

	public void setCom_account(String com_account) {
		this.com_account = com_account;
	}

	public String getCom_password() {
		return com_password;
	}

	public void setCom_password(String com_password) {
		this.com_password = com_password;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_address() {
		return com_address;
	}

	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}

	public String getCom_logo() {
		return com_logo;
	}

	public void setCom_logo(String com_logo) {
		this.com_logo = com_logo;
	}

	public int getCom_status() {
		return com_status;
	}

	public void setCom_status(int com_status) {
		this.com_status = com_status;
	}

	public String getCom_reg() {
		return com_reg;
	}

	public void setCom_reg(String com_reg) {
		this.com_reg = com_reg;
	}

	public String[] getCom_type() {
		return com_type;
	}

	public void setCom_type(String[] com_type) {
		this.com_type = com_type;
	}

	public String getCom_desc() {
		return com_desc;
	}

	public void setCom_desc(String com_desc) {
		this.com_desc = com_desc;
	}

	public String getCom_email() {
		return com_email;
	}

	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
}
