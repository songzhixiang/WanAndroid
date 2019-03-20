package com.andysong.wanandroid.di.component;

import android.app.Activity;

import com.andysong.wanandroid.MainActivity;
import com.andysong.wanandroid.di.module.ActivityModule;
import com.andysong.wanandroid.di.scope.ActivityScope;

import dagger.Component;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();



    void inject(MainActivity mainActivity);


}
