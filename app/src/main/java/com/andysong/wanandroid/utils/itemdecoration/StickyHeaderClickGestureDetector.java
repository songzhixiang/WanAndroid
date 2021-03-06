package com.andysong.wanandroid.utils.itemdecoration;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class StickyHeaderClickGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private GestureDetector gestureDetector;

    private SparseIntArray headersTop;
    private ItemDecorationHelper.StickyDividerHelper stickyDividerHelper;
    private OnHeaderClickListener headerClickListener;

    StickyHeaderClickGestureDetector(Context context, SparseIntArray headersTop, ItemDecorationHelper.StickyDividerHelper stickyDividerHelper) {
        gestureDetector = new GestureDetector(context, this);
        this.headersTop = headersTop;
        this.stickyDividerHelper = stickyDividerHelper;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener headerClickListener) {
        this.headerClickListener = headerClickListener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        for (int i = 0; i < headersTop.size(); i++) {
            int position = headersTop.keyAt(i);
            int top = headersTop.get(position);
            float y = e.getY();
            if (y >= top && y <= top + stickyDividerHelper.getHeaderHeight()) {
                if (headerClickListener != null) {
                    headerClickListener.onHeaderClicked(position);
                }
                return true;
            }
        }
        return false;
    }
}

