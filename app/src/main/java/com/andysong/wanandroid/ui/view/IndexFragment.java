package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.ui.contract.IndexContract;
import com.andysong.wanandroid.ui.presenter.IndexPresenter;
import com.andysong.wanandroid.ui.view.adapter.IndexAdapter;
import com.andysong.wanandroid.utils.helpers.IRefreshPage;
import com.andysong.wanandroid.utils.helpers.RefreshLoadMoreHelper;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexFragment extends BaseMVPFragment<IndexPresenter> implements IRefreshPage,IndexContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RefreshLoadMoreHelper<ArticleEntity> refreshLoadMoreHelper;


    public static IndexFragment newInstance() {

        Bundle args = new Bundle();

        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycleview;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        refreshLoadMoreHelper = new RefreshLoadMoreHelper<>(this, mSwipeRefreshLayout, mRecyclerView, IndexAdapter.class);
        refreshLoadMoreHelper.autoRefresh();
        refreshLoadMoreHelper.setOnItemClickListener(this);
    }



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showArticle(PageList<ArticleEntity> articleEntityList) {
        refreshLoadMoreHelper.loadSuccess(articleEntityList);
    }


    @Override
    public void loadData() {
        if (mPresenter != null) {
            mPresenter.getArticle(refreshLoadMoreHelper.getCurrPage());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getItem(position) instanceof ArticleEntity)
        {

            ((MainFragment)getParentFragment()).startBrotherFragment((ArticleDetailsFragment.newInstance(((ArticleEntity) adapter.getItem(position)).getLink())));
        }

    }


    @Override
    public void showErrorMsg(@NotNull String msg) {
        refreshLoadMoreHelper.loadError();
        LogUtils.e(msg);
    }

    @Override
    public void stateError() {
        refreshLoadMoreHelper.loadError();
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void stateMain() {

    }
}
