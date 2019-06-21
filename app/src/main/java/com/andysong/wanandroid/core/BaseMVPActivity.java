package com.andysong.wanandroid.core;

import android.support.annotation.Nullable;

import com.andysong.wanandroid.di.component.ActivityComponent;
import com.andysong.wanandroid.di.component.DaggerActivityComponent;
import com.andysong.wanandroid.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    @Inject
    @Nullable
    protected P mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(SampleApplicationLike.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();//释放资源
        }
        this.mPresenter = null;
        super.onDestroy();

    }

    protected abstract void initInject();
}

