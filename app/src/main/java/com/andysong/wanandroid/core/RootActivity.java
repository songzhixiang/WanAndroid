package com.andysong.wanandroid.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andysong.wanandroid.widget.stateview.StateView;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public abstract class RootActivity<T extends BasePresenter> extends BaseMVPActivity<T> {

    private StateView mStateView;

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mStateView = StateView.inject(injectTarget());

        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                //do something
            }
        });
    }

    protected abstract View injectTarget();

    @Override
    public void stateEmpty() {
        mStateView.showEmpty();
    }


    @Override
    public void stateError() {
        mStateView.showRetry();
    }


    @Override
    public void stateLoading() {
        mStateView.showLoading();
    }


    @Override
    public void stateMain() {
        mStateView.showContent();
    }
}

