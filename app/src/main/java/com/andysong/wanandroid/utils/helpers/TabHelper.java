package com.andysong.wanandroid.utils.helpers;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.andysong.wanandroid.ui.view.adapter.TabPageAdapter;

/**
 * @author andysong
 * @data 2019/4/28
 * @discription xxx
 */
public class TabHelper {

    private static final int MAX_CACHE_SIZE = 4;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPageAdapter adapter;

    public TabHelper(TabLayout tabLayout, ViewPager viewPager, TabPageAdapter adapter) {
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        this.adapter = adapter;

        if (adapter.getCount() <= MAX_CACHE_SIZE) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(MAX_CACHE_SIZE);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setCurrentPosition(int position) {
        viewPager.setCurrentItem(position);
    }

    public void setCurrentPosition(int position, boolean smoothScroll) {
        viewPager.setCurrentItem(position, smoothScroll);
    }

    public TabPageAdapter getAdapter() {
        return adapter;
    }
}
