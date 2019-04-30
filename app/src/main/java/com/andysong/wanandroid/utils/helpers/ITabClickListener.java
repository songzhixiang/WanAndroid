package com.andysong.wanandroid.utils.helpers;

/**
 * @author andysong
 * @data 2019/4/29
 * @discription xxx
 */
public interface ITabClickListener {
    //点击tab返回顶部
    void onScrollToTop();

    //判断当前界面是否在顶部
    boolean isTop();

    //点击tab刷新
    void onRefresh();
}
