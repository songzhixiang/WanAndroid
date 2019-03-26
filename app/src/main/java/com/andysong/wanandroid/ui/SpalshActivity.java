package com.andysong.wanandroid.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.andysong.wanandroid.MainActivity;
import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class SpalshActivity extends BaseActivity {
    @Override
    protected int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        MainActivity.start(SpalshActivity.this);
                        finish();
                    }
                });

    }
}
