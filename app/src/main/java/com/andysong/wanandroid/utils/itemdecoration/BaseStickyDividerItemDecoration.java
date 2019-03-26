package com.andysong.wanandroid.utils.itemdecoration;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.MotionEvent;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class BaseStickyDividerItemDecoration extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    ItemDecorationHelper.StickyDividerHelper stickyDividerHelper;
    SparseIntArray headersTop;
    private OnHeaderClickListener headerClickListener;
    StickyHeaderClickGestureDetector gestureDetector;

    BaseStickyDividerItemDecoration(ItemDecorationFactory.StickyDividerBuilder builder) {
        stickyDividerHelper = new ItemDecorationHelper.StickyDividerHelper(builder);
        headersTop = new SparseIntArray();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        headersTop.clear();
        if (gestureDetector == null) {
            parent.addOnItemTouchListener(this);
            gestureDetector = new StickyHeaderClickGestureDetector(parent.getContext(), headersTop,
                    stickyDividerHelper);
        }
        gestureDetector.setOnHeaderClickListener(headerClickListener);
    }

    public void setOnHeaderClickListener(OnHeaderClickListener headerClickListener) {
        this.headerClickListener = headerClickListener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return gestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}

