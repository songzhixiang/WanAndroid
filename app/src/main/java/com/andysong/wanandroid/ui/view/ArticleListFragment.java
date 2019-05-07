package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article_list;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        if (null != getArguments()) {
            switch (getArguments().getInt(ARTICLE_TYPE)) {
                case ARTICLE_FAVORITE:

                    break;

                case ARTICLE_HISTORY:

                    break;

                case ARTICLE_FLLOW:

                    break;

                case ARTICLE_STAR:

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
