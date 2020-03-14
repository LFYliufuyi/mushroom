package com.yiquanxinhe.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Raw
 * @Date: 2020/3/14 15:49
 * @Description:
 */
@Setter
@Getter
@ToString
public class PageInfo {
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //总页数
    private int pages;
    //总条数
    private int totalCount;

    public PageInfo() {
    }

    public PageInfo(int pageNum, int pageSize, int totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }


    public int getPages() {
        return this.totalCount / this.pageSize + (this.totalCount % this.pageSize == 0 ? 0 : 1);
    }

    public boolean getIsFirstPage() {
        return pageNum == 1;
    }

    public boolean getIsLastPage() {
        return pageNum > this.totalCount / this.pageSize + (this.totalCount % this.pageSize == 0 ? 0 : 1) - 1;
    }

    public boolean getHasPreviousPage() {
        return pageNum > 1;
    }

    public boolean getHasNextPage() {
        return pageNum < this.totalCount / this.pageSize + (this.totalCount % this.pageSize == 0 ? 0 : 1);
    }


    private static ThreadLocal<PageInfo> threadLocal = new ThreadLocal<>();


    public static PageInfo get() {
        return threadLocal.get();
    }

    public static void set(PageInfo pageInfo) {
        threadLocal.set(pageInfo);
    }
}
