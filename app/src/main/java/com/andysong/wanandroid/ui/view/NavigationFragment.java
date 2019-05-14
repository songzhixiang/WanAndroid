package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.core.RootFragment;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.andysong.wanandroid.ui.contract.NavigationContract;
import com.andysong.wanandroid.ui.presenter.NavigationPresenter;
import com.andysong.wanandroid.ui.view.adapter.NavigationAdapter;
import com.andysong.wanandroid.ui.view.adapter.NavigationTypeAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class NavigationFragment extends RootFragment<NavigationPresenter> implements NavigationContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recycler_types)
    RecyclerView mRecyclerViewType;
    @BindView(R.id.recycler_navigation)
    RecyclerView mRecyclerViewNavigation;

    private NavigationAdapter mNavigationAdapter;
    private NavigationTypeAdapter mTypeAdapter;

    private int currPos;
    private boolean shouldScroll;

    private List<NavigationInfoEntity> items = new ArrayList<>();

    public static NavigationFragment newInstance() {

        Bundle args = new Bundle();

        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        super.initEventAndData(savedInstanceState);
        mRecyclerViewNavigation.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNavigationAdapter = new NavigationAdapter(items);
        mNavigationAdapter.setBaseFragment(this);
        mRecyclerViewNavigation.setAdapter(mNavigationAdapter);
        mRecyclerViewNavigation.addOnScrollListener(onScrollListener);

        mRecyclerViewType.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTypeAdapter = new NavigationTypeAdapter(items);
        mTypeAdapter.setOnItemClickListener(this);
        mRecyclerViewType.setAdapter(mTypeAdapter);
        mRecyclerViewType.addOnScrollListener(onTypeScrollListener);

        load();
    }

    public void load(){
        if (mPresenter != null) {
            mPresenter.getNavigationInfo();
        }
    }

    @Override
    protected void onRetry() {
        load();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showNavigationInfo(List<NavigationInfoEntity> navigationInfoEntities) {
        items.clear();
        items.addAll(navigationInfoEntities);
        if (items.size() > 0) {
            currPos = 0;
            items.get(currPos).setSelected(true);
        }
        mTypeAdapter.notifyDataSetChanged();
        mNavigationAdapter.notifyDataSetChanged();
    }



    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (shouldScroll) {
                return;
            }
            int firstVisibleItemPosition = getFirstVisibleItemPosition();
            if (currPos != firstVisibleItemPosition) {
                selectItem(firstVisibleItemPosition);
                scrollToPosition(mRecyclerViewType, currPos, true, false);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (shouldScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                shouldScroll = false;
                scrollToPosition(mRecyclerViewNavigation, currPos, false, true);
            }
        }
    };

    private RecyclerView.OnScrollListener onTypeScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (shouldScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                shouldScroll = false;
                scrollToPosition(mRecyclerViewType, currPos, false, true);
            }
        }
    };

    private int getFirstVisibleItemPosition() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerViewNavigation.getLayoutManager();
        if (layoutManager == null) {
            return 0;
        }
        return layoutManager.findFirstVisibleItemPosition();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        selectItem(position);

        int firstVisibleItemPosition = getFirstVisibleItemPosition();
        if (currPos != firstVisibleItemPosition) {
            scrollToPosition(mRecyclerViewNavigation, currPos, true, true);
        }
    }

    private void scrollToPosition(RecyclerView recyclerView, int position, boolean needSmooth, boolean itemInScreenNeedScroll) {
        int firstItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
        int lastItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(recyclerView.getChildCount() - 1));
        if (position < firstItem) {
//            shouldScroll = true;
            //在屏幕上方，直接滚上去就是顶部
            if (needSmooth) {
                recyclerView.smoothScrollToPosition(position);
            } else {
                recyclerView.scrollToPosition(position);
            }
        } else if (position <= lastItem) {
            if (itemInScreenNeedScroll) {
                //在屏幕中，直接滚动到相应位置的顶部
                int movePosition = position - firstItem;
                if (movePosition >= 0 && movePosition < recyclerView.getChildCount()) {
                    //粘性头部，会占据一定的top空间，所以真是的top位置应该是减去粘性header的高度
                    int top = recyclerView.getChildAt(movePosition).getTop();
                    if (needSmooth) {
                        recyclerView.smoothScrollBy(0, top);
                    } else {
                        recyclerView.scrollBy(0, top);
                    }
                }
            }
        } else {
            //在屏幕下方，需要先滚动到屏幕内，在校验
            shouldScroll = true;
            if (needSmooth) {
                recyclerView.smoothScrollToPosition(position);
            } else {
                recyclerView.scrollToPosition(position);
            }
            currPos = position;
        }
    }

    private void selectItem(int position) {
        if (position < 0 || items.size() < position) {
            return;
        }
        items.get(currPos).setSelected(false);
        currPos = position;
        items.get(currPos).setSelected(true);
        mTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(@NotNull String msg) {

    }

    @Override
    public void hideLoading() {

    }
}
