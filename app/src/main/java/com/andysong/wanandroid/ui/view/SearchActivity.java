package com.andysong.wanandroid.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseActivity;

/**
 * @author AndySong on 2019/4/1
 * @Blog https://github.com/songzhixiang
 */
public class SearchActivity extends BaseActivity {
    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.layout_content;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        if (findFragment(SearchFragment.class) == null) {
            loadRootFragment(R.id.fl_container, SearchFragment.newInstance());
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);

        context.startActivity(starter);
    }


}
