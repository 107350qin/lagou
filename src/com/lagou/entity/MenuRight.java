package com.lagou.entity;

/**
 * 搜索链接的右边部分
 * 
 * @author Administrator
 *
 */
public class MenuRight {
	private int mr_id;
	private String mr_title;
	private String[] mr_link;

	public int getMr_id() {
		return mr_id;
	}

	public void setMr_id(int mr_id) {
		this.mr_id = mr_id;
	}

	public String getMr_title() {
		return mr_title;
	}

	public void setMr_title(String mr_title) {
		this.mr_title = mr_title;
	}

	public String[] getMr_link() {
		return mr_link;
	}

	public void setMr_link(String[] mr_link) {
		this.mr_link = mr_link;
	}

	public MenuRight() {
		super();
		// TODO Auto-generated constructor stub
	}

}
