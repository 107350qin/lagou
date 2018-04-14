package com.lagou.entity;

/**
 * 分页实体
 */
import java.util.List;

public class Pager<T> {
	private int pageNow;
	private int pageCount;
	private int totalCount;
	private int pageSize = 10;

	/**
	 * 注意：这里只能用list集合，如果用的是set集合，由于它是无序的，那么每次刷新微博的时候微博的顺序并不是有序的！
	 */
	private List<T> content;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Pager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pager(int pageNow, int pageCount, int totalCount, int pageSize, List<T> content) {
		super();
		this.pageNow = pageNow;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.content = content;
	}

}
