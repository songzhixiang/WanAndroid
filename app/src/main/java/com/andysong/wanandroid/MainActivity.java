package com.andysong.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.andysong.wanandroid.core.BaseActivity;
import com.andysong.wanandroid.core.BaseFragment;
import com.andysong.wanandroid.ui.view.IndexDataFragment;
import com.andysong.wanandroid.ui.view.IndexFragment;
import com.andysong.wanandroid.ui.view.IndexLastFragment;
import com.andysong.wanandroid.ui.view.IndexSettingFragment;
import com.andysong.wanandroid.ui.view.IndexTaskFragment;
import com.andysong.wanandroid.widget.AnimatedImageView;
import com.andysong.wanandroid.widget.AnimatedTextView;
import com.andysong.wanandroid.widget.ArcView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.arcView)
    ArcView mArcView;
    @BindView(R.id.arcImage)
    AnimatedImageView mAnimatedImageView;
    @BindView(R.id.toolbarTitle)
    AnimatedTextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView mBottomBar;
    @BindView(R.id.mainView)
    CardView mCardView;
    @BindView(R.id.navView)
    NavigationView navView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;

    private boolean isDrawerOpened = false;

    private boolean isArcIcon = false;

    private int preSelected = -1;

    private BaseFragment[] mFragments = new BaseFragment[5];

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);

        context.startActivity(starter);
    }

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        BaseFragment firstFragment = (BaseFragment) findFragment(IndexFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = IndexFragment.newInstance();
            mFragments[SECOND] = IndexTaskFragment.newInstance();
            mFragments[THIRD] = IndexDataFragment.newInstance();
            mFragments[FOURTH] = IndexSettingFragment.newInstance();
            mFragments[FIVE] = IndexLastFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIVE]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = (BaseFragment) findFragment(IndexTaskFragment.class);
            mFragments[THIRD] = (BaseFragment) findFragment(IndexDataFragment.class);
            mFragments[FOURTH] = (BaseFragment) findFragment(IndexSettingFragment.class);
            mFragments[FIVE] = (BaseFragment) findFragment(IndexLastFragment.class);
        }

        initViews();
    }

    private void initViews() {

        mBottomBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_map:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[FIRST]);
                        } else {
                            showHideFragment(mFragments[FIRST], mFragments[preSelected]);
                        }

                        preSelected = FIRST;
                        return true;
                    case R.id.navigation_task:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[SECOND]);
                        } else {
                            showHideFragment(mFragments[SECOND], mFragments[preSelected]);
                        }
                        preSelected = SECOND;
                        return true;
                    case R.id.navigation_data:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[THIRD]);
                        } else {
                            showHideFragment(mFragments[THIRD], mFragments[preSelected]);
                        }
                        preSelected = THIRD;
                        return true;
                    case R.id.navigation_setting:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[FOURTH]);
                        } else {
                            showHideFragment(mFragments[FOURTH], mFragments[preSelected]);
                        }
                        preSelected = FOURTH;
                        return true;

                    case R.id.navigation_search:
                        if (preSelected == -1){
                            showHideFragment(mFragments[FIVE]);
                        }else {
                            showHideFragment(mFragments[FIVE],mFragments[preSelected]);
                        }
                        preSelected = FIVE;
                        break;
                }
                return false;
            }
        });

        mDrawerLayout.setDrawerElevation(0F);

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);


    }





}
