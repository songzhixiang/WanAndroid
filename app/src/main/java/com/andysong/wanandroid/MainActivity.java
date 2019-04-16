package com.andysong.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.andysong.wanandroid.core.BaseActivity;
import com.andysong.wanandroid.ui.view.MainFragment;
import com.andysong.wanandroid.utils.StatusBarUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.layout_content;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this, null);

    }







    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }





    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
