package com.andysong.wanandroid.utils.itemdecoration;

import android.view.View;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public interface StickyDividerCallback {

    GroupData getGroupData(int position);

    View getStickyHeaderView(int position);
}

