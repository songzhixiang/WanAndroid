package com.andysong.wanandroid.di.component;

import android.app.Activity;

import com.andysong.wanandroid.IndexFragment;
import com.andysong.wanandroid.di.module.FragmentModule;
import com.andysong.wanandroid.di.scope.FragmentScope;

import dagger.Component;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(IndexFragment indexFragment);


}