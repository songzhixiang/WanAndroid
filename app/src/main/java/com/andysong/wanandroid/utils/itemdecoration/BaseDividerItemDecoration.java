package com.andysong.wanandroid.utils.itemdecoration;

import android.support.v7.widget.RecyclerView;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class BaseDividerItemDecoration extends RecyclerView.ItemDecoration {
    ItemDecorationHelper.DividerHelper dividerHelper;

    BaseDividerItemDecoration(ItemDecorationFactory.DividerBuilder builder) {
        dividerHelper = new ItemDecorationHelper.DividerHelper(builder);
    }
}
