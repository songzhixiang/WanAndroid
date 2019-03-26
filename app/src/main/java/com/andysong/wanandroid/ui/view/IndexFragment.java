package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.RootFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.ui.contract.IndexContract;
import com.andysong.wanandroid.ui.presenter.IndexPresenter;
import com.andysong.wanandroid.ui.view.adapter.IndexAdapter;
import com.andysong.wanandroid.utils.helpers.IRefreshPage;
import com.andysong.wanandroid.utils.helpers.RefreshHelper;
import com.andysong.wanandroid.widget.stateview.StateView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexFragment extends RootFragment<IndexPresenter> implements IRefreshPage,IndexContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.stateView)
    StateView mStateView;
    private RefreshHelper<ArticleEntity> refreshHelper;


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
        refreshHelper = new RefreshHelper<>(this, mSwipeRefreshLayout, mRecyclerView, IndexAdapter.class);
        refreshHelper.autoRefresh();
        refreshHelper.setOnItemClickListener(this);
    }

    @Override
    protected void onRetry() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showArticle(List<ArticleEntity> articleEntityList) {
        refreshHelper.loadSuccess(articleEntityList);
    }


    @Override
    public void loadData() {
        if (mPresenter != null) {
            mPresenter.getArticle(0);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void showErrorMsg(@NotNull String msg) {

    }

    @Override
    public void hideLoading() {

    }
}
