package com.andysong.wanandroid.utils.helpers;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * @author andysong
 * @data 2019/4/29
 * @discription xxx
 */
public class CustomLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
