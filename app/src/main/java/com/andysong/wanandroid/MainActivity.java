package com.andysong.wanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.andysong.wanandroid.core.BaseActivity;
import com.andysong.wanandroid.core.BaseFragment;
import com.andysong.wanandroid.ui.view.IndexFragment;
import com.andysong.wanandroid.ui.view.JueJinFragment;
import com.andysong.wanandroid.ui.view.KnowledgeFragment;
import com.andysong.wanandroid.ui.view.LoginActivity;
import com.andysong.wanandroid.ui.view.NavigationFragment;
import com.andysong.wanandroid.ui.view.SearchActivity;
import com.andysong.wanandroid.utils.CommonExKt;
import com.andysong.wanandroid.widget.AnimatedImageView;
import com.andysong.wanandroid.widget.AnimatedTextView;
import com.andysong.wanandroid.widget.ArcView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.arcView)
    ArcView mArcView;
    @BindView(R.id.arcImage)
    AnimatedImageView mAnimatedImageView;
    @BindView(R.id.toolbarTitle)
    AnimatedTextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.navigation)
    BottomNavigationView mBottomBar;
    @BindView(R.id.mainView)
    CardView mCardView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.userAvatar)
    ImageView userAvatar;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.tv_recent)
    TextView tvRecent;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.iv_search)
    ImageView mImageViewSearch;


    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;


    private boolean isDrawerOpened = false;


    private int preSelected = -1;

    private BaseFragment[] mFragments = new BaseFragment[4];

    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        loadFragment();

        setArcHamburgerIconState();

        mBottomBar.setItemIconTintList(null);
        mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_index:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[FIRST]);
                        } else {
                            showHideFragment(mFragments[FIRST], mFragments[preSelected]);
                        }

                        preSelected = FIRST;
                        return true;
                    case R.id.navigation_knowledge:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[SECOND]);
                        } else {
                            showHideFragment(mFragments[SECOND], mFragments[preSelected]);
                        }
                        preSelected = SECOND;
                        return true;
                    case R.id.navigation_navi:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[THIRD]);
                        } else {
                            showHideFragment(mFragments[THIRD], mFragments[preSelected]);
                        }
                        preSelected = THIRD;
                        return true;
                    case R.id.navigation_juejin:
                        if (preSelected == -1) {
                            showHideFragment(mFragments[FOURTH]);
                        } else {
                            showHideFragment(mFragments[FOURTH], mFragments[preSelected]);
                        }
                        preSelected = FOURTH;
                        return true;
                }
                return false;
            }
        });
        mDrawerLayout.setDrawerElevation(0F);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                mCardView.setTranslationX(view.getWidth() * v);
                CommonExKt.setScale(mCardView, 1 - v / 4);
                mCardView.setCardElevation(v * CommonExKt.toPx(10, MainActivity.this));
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                handleDrawerOpen();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                handleDrawerClose();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        //去除阴影
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);


    }

    private void loadFragment() {
        BaseFragment firstFragment = findFragment(IndexFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = IndexFragment.newInstance();
            mFragments[SECOND] = KnowledgeFragment.newInstance();
            mFragments[THIRD] = NavigationFragment.newInstance();
            mFragments[FOURTH] = JueJinFragment.newInstance();


            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(KnowledgeFragment.class);
            mFragments[THIRD] = findFragment(NavigationFragment.class);
            mFragments[FOURTH] = findFragment(JueJinFragment.class);

        }
    }


    private void handleDrawerOpen() {
        setArcArrowState();
        isDrawerOpened = true;
    }

    private void handleDrawerClose() {
        setArcHamburgerIconState();
        isDrawerOpened = false;
    }


    private void setArcHamburgerIconState() {

        mAnimatedImageView.setAnimatedImage(R.drawable.hamb, 0L);
    }

    private void setArcArrowState() {

        mAnimatedImageView.setAnimatedImage(R.drawable.arrow_left, 0L);
    }



    @Override
    public void onBackPressedSupport() {
        if (isDrawerOpened) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressedSupport();
        }

    }



    @OnClick({R.id.userAvatar,
            R.id.tv_favorite,
            R.id.tv_recent,
            R.id.tv_follow,
            R.id.tv_star,
            R.id.iv_search,
            R.id.arcView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userAvatar:
                LoginActivity.start(MainActivity.this);
                break;
            case R.id.tv_favorite:
                break;
            case R.id.tv_recent:
                break;
            case R.id.tv_follow:
                break;
            case R.id.tv_star:
                break;

            case R.id.arcView:
                if (isDrawerOpened) {
                    mDrawerLayout.closeDrawers();
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;

            case R.id.iv_search:
                SearchActivity.start(MainActivity.this);
                break;
        }
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
