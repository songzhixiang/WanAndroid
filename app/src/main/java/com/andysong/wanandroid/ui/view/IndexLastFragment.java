package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexLastFragment extends BaseFragment {

    public static IndexLastFragment newInstance() {

        Bundle args = new Bundle();

        IndexLastFragment fragment = new IndexLastFragment();
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
