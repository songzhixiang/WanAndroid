package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexDataFragment extends BaseFragment {

    public static IndexDataFragment newInstance() {

        Bundle args = new Bundle();

        IndexDataFragment fragment = new IndexDataFragment();
        fragment.setArguments(args);
        return fragment;
    }


    
    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycleview;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {

    }
}
