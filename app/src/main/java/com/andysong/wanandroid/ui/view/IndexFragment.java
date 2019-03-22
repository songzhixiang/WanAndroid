package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.ui.contract.IndexContract;
import com.andysong.wanandroid.ui.presenter.IndexPresenter;
import com.andysong.wanandroid.ui.view.adapter.IndexAdapter;
import com.andysong.wanandroid.widget.stateview.StateView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexFragment extends BaseMVPFragment<IndexPresenter> implements IndexContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.stateView)
    StateView mStateView;

    private IndexAdapter mIndexAdapter;

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
        mIndexAdapter = new IndexAdapter(null);
        mRecyclerView.setAdapter(mIndexAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity,LinearLayoutManager.VERTICAL,false));
        if (mPresenter != null) {
            mPresenter.getArticle();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showArticle(List<ArticleEntity> articleEntityList) {
        mIndexAdapter.setNewData(articleEntityList);
    }

    @Override
    public void showErrorMsg(@NotNull String msg) {

    }

    @Override
    public void stateError() {

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
