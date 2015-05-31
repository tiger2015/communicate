package com.util;

public class MySplitePage {
	private long totalRecord = 0;
	private int totalPage = 0;
	private int currentPage = 1;
	private static final int PAGE_SIZE = 5;

	public long getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int) (this.totalRecord % MySplitePage.PAGE_SIZE == 0 ? this.totalRecord
				/ MySplitePage.PAGE_SIZE
				: this.totalRecord / MySplitePage.PAGE_SIZE + 1);
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage > totalPage) {
			this.currentPage = totalPage;
		} else if (currentPage <= 0) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}

}
