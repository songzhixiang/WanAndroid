package com.andysong.wanandroid.ui.view.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author AndySong on 2019/3/27
 * @Blog https://github.com/songzhixiang
 */
public class NavigationTypeAdapter extends BaseQuickAdapter<NavigationInfoEntity, BaseViewHolder> {
    public NavigationTypeAdapter(@Nullable List<NavigationInfoEntity> data) {
        super(R.layout.item_navigation_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationInfoEntity item) {
        TextView tvItem = helper.getView(R.id.tv_item);
        tvItem.setText(item.getName());
        tvItem.setSelected(item.isSelected());
    }
}
