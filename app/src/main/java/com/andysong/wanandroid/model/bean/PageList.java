package com.andysong.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author AndySong on 2019/3/22
 * @Blog https://github.com/songzhixiang
 */
public class PageList<T> {

    @SerializedName("datas")
    private List<T> data;
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
