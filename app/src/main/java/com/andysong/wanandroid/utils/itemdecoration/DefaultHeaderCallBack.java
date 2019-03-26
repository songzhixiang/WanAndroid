package com.andysong.wanandroid.utils.itemdecoration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.andysong.wanandroid.R;

import java.util.WeakHashMap;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public abstract class DefaultHeaderCallBack implements StickyDividerCallback {
    private Context context;
    private WeakHashMap<Integer, View> headerMap;

    public DefaultHeaderCallBack(Context context) {
        this.context = context;
        headerMap = new WeakHashMap<>();
    }

    @Override
    public View getStickyHeaderView(int position) {
        View headerView = headerMap.get(position);
        if (headerView == null) {
            headerView = LayoutInflater.from(context).inflate(R.layout.item_decoration_default_header, null);
            TextView tvHeader = (TextView) headerView.findViewById(R.id.tv_header);
            tvHeader.setText(getGroupData(position).getTitle());
            headerMap.put(position, headerView);
        }
        return headerView;
    }

    public void onDestroy() {
        context = null;
        headerMap.clear();
    }
}