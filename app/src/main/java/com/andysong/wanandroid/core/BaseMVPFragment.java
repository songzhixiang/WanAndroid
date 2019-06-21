package com.andysong.wanandroid.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andysong.wanandroid.di.component.DaggerFragmentComponent;
import com.andysong.wanandroid.di.component.FragmentComponent;
import com.andysong.wanandroid.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(SampleApplicationLike.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initInject();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();//释放资源
        }
        this.mPresenter = null;
    }
}