package com.andysong.wanandroid.ui.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andysong.wanandroid.model.bean.IPageTitle;
import com.andysong.wanandroid.utils.helpers.IPageContent;

import java.util.List;

/**
 * @author andysong
 * @data 2019/4/28
 * @discription xxx
 */
public class TabPageAdapter<T extends IPageTitle> extends FragmentStatePagerAdapter {

    private List<T> items;
    private IPageContent mIPageContent;

    public TabPageAdapter(FragmentManager fm, List<T> items, IPageContent pageContent) {
        super(fm);
        this.items = items;
        this.mIPageContent = pageContent;
    }

    @Override
    public Fragment getItem(int i) {
        if (mIPageContent == null){
            return null;
        }
        return mIPageContent.getItem(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (null== items || items.size() < position || position<0){
            return "";
        }
        return items.get(position).getTitle();
    }

    @Override
    public int getCount() {
        if (items == null){
            return 0;
        }
        return items.size();
    }
}
