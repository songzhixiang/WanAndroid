package com.andysong.wanandroid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.andysong.library.core.net.NetStatusBus;
import com.andysong.library.core.net.NetSubscribe;
import com.andysong.library.core.net.NetType;
import com.andysong.wanandroid.core.BaseActivity;
import com.andysong.wanandroid.ui.view.MainFragment;
import com.blankj.utilcode.util.LogUtils;

import java.util.List;

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
        NetStatusBus.getInstance().register(this);
//        getNotchParams();
    }


    /**
     * 获得刘海区域信息
     */
    @TargetApi(28)
    public void getNotchParams() {
        final View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.post(new Runnable() {
                @Override
                public void run() {
                    WindowInsets windowInsets = decorView.getRootWindowInsets();
                    if (windowInsets != null) {
                        // 当全屏顶部显示黑边时，getDisplayCutout()返回为null
                        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                        Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                        Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                        Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                        Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());
                        // 获得刘海区域
                        List<Rect> rects = displayCutout.getBoundingRects();
                        if (rects == null || rects.size() == 0) {
                            Log.e("TAG", "不是刘海屏");
                        } else {
                            Log.e("TAG", "刘海屏数量:" + rects.size());
                            for (Rect rect : rects) {
                                Log.e("TAG", "刘海屏区域：" + rect);
                            }
                        }
                    }
                }
            });
        }
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


    @NetSubscribe()
    public void wifiChange(NetType netType) {
        LogUtils.e(netType.name() + "<<<<<<<<<<BlankFragment");
        switch (netType) {
            case NONE:
                LogUtils.e("网络连接中断...");
                break;

            case WIFI:

                LogUtils.e("wifi已连接");
                break;

            case MOBILE:

                LogUtils.e("移动网络已连接");
                break;

            default:
        }

    }




    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStatusBus.getInstance().unregister(this);
    }
}
