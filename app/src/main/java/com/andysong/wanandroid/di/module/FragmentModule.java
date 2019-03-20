package com.andysong.wanandroid.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.andysong.wanandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}

