package com.raven.dream.job.common.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -8993472009733646419L;

    private List<T> list;
    private long total;
    private long pages;
    private int pageNum;

    public PageResult() {
    }

    public PageResult(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.list = page.getResult();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.pageNum = page.getPageNum();
        } else if (list instanceof Collection) {
            this.list = list;
            this.total = list.size();
            this.pages = 1;
            this.pageNum = 1;
        }

    }

    public PageResult(List<T> list, long total, long pages, int pageNum) {
        this.list = list;
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
