package com.andysong.wanandroid.utils.helpers;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;
import com.andysong.wanandroid.utils.itemdecoration.ItemDecorationFactory;
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
            adapter.setEmptyView(R.layout.base_empty);
        } catch (Exception e) {
            throw new RuntimeException("Adapter's constructor must be Adapter(layoutId,List) or Adapter(List)");
        }
    }

    public void autoRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    public void loadSuccess(PageList<T> response) {
        if (firstPage + 1 == response.getCurPage()) {
            items.clear();
        }
        items.addAll(response.getData());
        if (firstPage == 0) {
            adapter.setEnableLoadMore(response.hasNextStartWithZero());
        } else {
            adapter.setEnableLoadMore(response.hasNext());
        }
        if (currPage > firstPage) {
            adapter.loadMoreComplete();
        } else {
            refreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    }

    public void loadError() {
        if (currPage > firstPage) {
            adapter.loadMoreFail();
        } else {
            refreshLayout.setRefreshing(false);
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
        }
    }

    @Override
    public void onLoadMoreRequested() {
        currPage++;
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
        refreshPage = null;
    }
}
