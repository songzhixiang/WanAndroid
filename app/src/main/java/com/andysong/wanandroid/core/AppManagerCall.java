package com.andysong.wanandroid.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 并未选择在BaseActivtiy里面进行添加和移除，而且选择通过实现Application的Activity生命周期回调来实现，因为这样更便于控制，例如一些全局对话框等等
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class AppManagerCall implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AppManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.getInstance().finishActivity(activity);
    }
}

