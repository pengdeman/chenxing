package com.cx.chenxing.utils.mybatisutils;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    protected List<T> result;
    protected int pageSize;
    protected int pageNumber;
    protected int totalCount;
    protected int thisPageNumber;

    public Page(int pageSizeArg, int pageNumberArg) {
        this.pageSize = pageSizeArg;
        this.pageNumber = pageNumberArg;
        this.thisPageNumber=pageNumberArg;
    }

    public Page(int pageSizeArg, int pageNumberArg, int totalCountArg) {
        this.pageSize = pageSizeArg;
        this.pageNumber = pageNumberArg;
        this.totalCount = totalCountArg;
        this.thisPageNumber=pageNumberArg;
    }

    public Page(PageCondition pageCondition) {
        this.pageSize = pageCondition.getPageSize();
        this.pageNumber = pageCondition.getPageNumber();
        this.totalCount = pageCondition.getTotalCount();
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public int getPageNumber() {
        return this.pageNumber;
    }

    public boolean isFirstPage() {
        return this.getThisPageNumber() == 1;
    }

    public boolean isLastPage() {
        return this.getThisPageNumber() >= this.getLastPageNumber();
    }

    public boolean isHasNextPage() {
        return this.getLastPageNumber() > this.getThisPageNumber();
    }

    public boolean isHasPreviousPage() {
        return this.getThisPageNumber() > 1;
    }

    public int getLastPageNumber() {
        return PageUtil.computeLastPageNumber(this.totalCount, this.pageSize);
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getThisPageFirstElementNumber() {
        return (this.getThisPageNumber() - 1) * this.getPageSize() + 1;
    }

    public int getThisPageLastElementNumber() {
        int fullPage = this.getThisPageFirstElementNumber() + this.getPageSize() - 1;
        return this.getTotalCount() < fullPage ? this.getTotalCount() : fullPage;
    }

    public int getNextPageNumber() {
        return this.getThisPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return this.getThisPageNumber() - 1;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getThisPageNumber() {
        return this.pageNumber;
    }

    public List<Integer> getLinkPageNumbers() {
        return PageUtil.generateLinkPageNumbers(this.getThisPageNumber(), this.getLastPageNumber(), 10);
    }

    public int getFirstResult() {
        return PageUtil.getFirstResult(this.pageNumber, this.pageSize);
    }

    public Iterator<T> iterator() {
        return this.result == null ? Collections.EMPTY_LIST.iterator() : this.result.iterator();
    }
}
