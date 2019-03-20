package com.andysong.wanandroid.di.module;

import android.app.Activity;

import com.andysong.wanandroid.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
