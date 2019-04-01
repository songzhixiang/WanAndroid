package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;

/**
 * @author AndySong on 2019/3/28
 * @Blog https://github.com/songzhixiang
 */
public class JueJinFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycleview;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {

    }

    public static JueJinFragment newInstance() {

        Bundle args = new Bundle();

        JueJinFragment fragment = new JueJinFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
