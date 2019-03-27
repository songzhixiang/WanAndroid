package com.andysong.wanandroid.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.widget.stateview.StateView;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public abstract class RootFragment<T extends BasePresenter> extends BaseMVPFragment<T> {

    private StateView mStateView;


    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        if (getView() == null) {
            return;
        }
//        mStateView = getView().findViewById(R.id.stateView);
        if (mStateView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'view_main'.");
        }
        if (!(mStateView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        mStateView.setOnRetryClickListener(this::onRetry);
    }

    protected abstract void onRetry();

    @Override
    public void stateMain() {
        mStateView.showContent();
    }

    @Override
    public void stateLoading() {
        mStateView.showLoading();
    }

    @Override
    public void stateError() {
        mStateView.showRetry();
    }

    @Override
    public void stateEmpty() {
        mStateView.showEmpty();
    }
}

