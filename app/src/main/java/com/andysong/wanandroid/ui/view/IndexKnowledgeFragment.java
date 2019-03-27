package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.ui.contract.KnowledgeContract;
import com.andysong.wanandroid.ui.presenter.KnowledgePresenter;
import com.andysong.wanandroid.ui.view.adapter.KnowledgeTreeAdapter;
import com.andysong.wanandroid.utils.helpers.IRefreshPage;
import com.andysong.wanandroid.utils.helpers.RefreshHelper;
import com.andysong.wanandroid.widget.stateview.StateView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class IndexKnowledgeFragment extends BaseMVPFragment<KnowledgePresenter> implements IRefreshPage,KnowledgeContract.View, BaseQuickAdapter.OnItemClickListener {

    private RefreshHelper<TreeEntity> refreshHelper;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;



    public static IndexKnowledgeFragment newInstance() {

        Bundle args = new Bundle();

        IndexKnowledgeFragment fragment = new IndexKnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycleview;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {

        refreshHelper = new RefreshHelper<>(this, mSwipeRefreshLayout, mRecyclerView, KnowledgeTreeAdapter.class);
        refreshHelper.autoRefresh();
        refreshHelper.setOnItemClickListener(this);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showKnowledge(List<TreeEntity> treeEntityList) {
        refreshHelper.loadSuccess(treeEntityList);
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


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void loadData() {
        mPresenter.getKnowledge();
    }
}
