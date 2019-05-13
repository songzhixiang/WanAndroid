package com.andysong.wanandroid.utils.helpers;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;
import com.andysong.wanandroid.utils.itemdecoration.ItemDecorationFactory;
import com.andysong.wanandroid.widget.stateview.StateView;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class RefreshLoadMoreHelper<T> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private int firstPage = 0;
    private int currPage = firstPage;

    private SwipeRefreshLayout refreshLayout;
    private StateView mStateView;

    private List<T> items;
    private BaseQuickAdapter<T, BaseViewHolder> adapter;
    private IRefreshPage refreshPage;

    public RefreshLoadMoreHelper(IRefreshPage refreshPage, SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, Class<? extends BaseQuickAdapter<T, BaseViewHolder>> adapterClass, int... adapterLayoutId) {
        this.refreshPage = refreshPage;
        this.refreshLayout = refreshLayout;

        Context context = recyclerView.getContext();
        refreshLayout.setColorSchemeColors(context.getResources().getColor(R.color.colorPrimary));
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDecorationFactory.DividerBuilder()
                .dividerColor(context.getResources().getColor(R.color.colorDivider))
                .build(recyclerView));
        this.items = new ArrayList<>();
        try {
            if (adapterLayoutId != null && adapterLayoutId.length > 0) {
                this.adapter = adapterClass.getConstructor(Integer.class, List.class).newInstance(adapterLayoutId[0], items);
            } else {
                this.adapter = adapterClass.getConstructor(List.class).newInstance(items);
            }
            recyclerView.setAdapter(this.adapter);
            adapter.setOnLoadMoreListener(this, recyclerView);
//            adapter.setEmptyView(R.layout.base_empty);
        } catch (Exception e) {
            throw new RuntimeException("Adapter's constructor must be Adapter(layoutId,List) or Adapter(List)");
        }
    }


    public RefreshLoadMoreHelper addStateView(StateView mStateView){
        this.mStateView = mStateView;
        return this;
    }

    public void autoRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (null!=mStateView){
                    mStateView.showLoading();
                }

                onRefresh();
            }
        });
    }

    public void loadSuccess(PageList<T> response) {
        adapter.setEnableLoadMore(true);
        if (firstPage + 1 == response.getCurPage()) {
            items.clear();
        }


        items.addAll(response.getData());
        if (response.hasNextStartWithZero()){
            if (firstPage == 0) {
                adapter.setEnableLoadMore(response.hasNextStartWithZero());
            } else {
                adapter.setEnableLoadMore(response.hasNext());
            }
            if (currPage > firstPage) {
                adapter.loadMoreComplete();
            } else {
                adapter.notifyDataSetChanged();
            }
        }else {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(false);
        }
        refreshLayout.setRefreshing(false);
        mStateView.showContent();
        currPage++;
    }

    public void loadError() {
        adapter.setEnableLoadMore(true);
        if (currPage > firstPage) {
            adapter.loadMoreFail();
        } else {
            refreshLayout.setRefreshing(false);
        }
        if (adapter.getData().isEmpty()){
            mStateView.showRetry();
        }

    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    @Override
    public void onRefresh() {
        currPage = firstPage;
        if (refreshPage != null) {
            refreshPage.loadData();
            adapter.setEnableLoadMore(false);
        }
    }

    @Override
    public void onLoadMoreRequested() {

        if (refreshPage != null) {
            refreshPage.loadData();
        }
    }

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }

    public T getItem(int position) {
        if (items == null || items.size() < position || position < 0) {
            return null;
        }
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    public BaseQuickAdapter<T, BaseViewHolder> getAdapter() {
        return adapter;
    }

    public boolean isFirstPage() {
        return currPage == firstPage;
    }

    public void onDestroy() {
        refreshLayout = null;
        adapter.setOnItemClickListener(null);
        adapter = null;
        mStateView = null;
        refreshPage = null;
    }
}
