package com.andysong.wanandroid.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseActivity;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class LoginActivity extends BaseActivity {


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);

        context.startActivity(starter);
    }

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.layout_content;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        if (findFragment(LoginFragment.class) == null) {
            loadRootFragment(R.id.fl_container, LoginFragment.newInstance());
        }
    }
}
