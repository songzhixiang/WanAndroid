package com.andysong.wanandroid.utils.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class StaggeredGridDividerItemDecoration extends BaseDividerItemDecoration {
    StaggeredGridDividerItemDecoration(ItemDecorationFactory.DividerBuilder builder) {
        super(builder);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        ItemDecorationHelper.getStaggeredItemOffset(outRect, parent, view, dividerHelper);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        ItemDecorationHelper.onStaggeredDraw(parent, dividerHelper);
    }
}
