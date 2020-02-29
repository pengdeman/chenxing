package com.cx.chenxing.utils.mybatisutils;

import java.io.Serializable;

public class PageCondition implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageSize=20;
    private int pageNumber;
    private int totalCount;
    private String sortColumns;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}
