package com.lagou.entity;

/**
 * 搜索链接的左边部分
 */
import java.util.ArrayList;

public class MenuLeft {
	private int ml_id;
	private String ml_title;
	private String[] ml_link;
	private ArrayList<MenuRight> list;

	public MenuLeft() {
		super();
	}

	public int getMl_id() {
		return ml_id;
	}

	public void setMl_id(int ml_id) {
		this.ml_id = ml_id;
	}

	public String getMl_title() {
		return ml_title;
	}

	public void setMl_title(String ml_title) {
		this.ml_title = ml_title;
	}

	public String[] getMl_link() {
		return ml_link;
	}

	public void setMl_link(String[] ml_link) {
		this.ml_link = ml_link;
	}

	public ArrayList<MenuRight> getList() {
		return list;
	}

	public void setList(ArrayList<MenuRight> list) {
		this.list = list;
	}
}
