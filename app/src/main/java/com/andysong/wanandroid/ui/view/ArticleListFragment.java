package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 列表---包括我的收藏，历史浏览，我的关注，我的点赞
 *
 * @author AndySong on 2019/4/4
 * @Blog https://github.com/songzhixiang
 */
public class ArticleListFragment extends BaseFragment {

    public static final int ARTICLE_FAVORITE = 1;
    public static final int ARTICLE_HISTORY = 2;
    public static final int ARTICLE_FLLOW = 3;
    public static final int ARTICLE_STAR = 4;
    public static final String ARTICLE_TYPE = "ARTICLE_TYPE";
    @BindView(R.id.tv_toolbar)
    TextView mTitle;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article_list;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        if (null != getArguments()) {
            switch (getArguments().getInt(ARTICLE_TYPE)) {
                case ARTICLE_FAVORITE:
                    mTitle.setText("我的收藏");
                    break;

                case ARTICLE_HISTORY:
                    mTitle.setText("历史浏览");
                    break;

                case ARTICLE_FLLOW:
                    mTitle.setText("我的关注");
                    break;

                case ARTICLE_STAR:
                    mTitle.setText("我的点赞");
                    break;
                default:
            }
        }
    }

    public static ArticleListFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARTICLE_TYPE, type);
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
