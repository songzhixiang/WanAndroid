package com.andysong.wanandroid.core;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.andysong.wanandroid.di.component.AppComponent;
import com.andysong.wanandroid.di.component.DaggerAppComponent;
import com.andysong.wanandroid.di.module.AppModule;
import com.andysong.wanandroid.di.module.HttpModule;
import com.blankj.utilcode.util.Utils;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class App extends Application {
    private static App instance;
    public static AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        registerActivityLifecycleCallbacks(new AppManagerCall());
        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int i) {

            }

            @Override
            public void onConfigurationChanged(Configuration configuration) {

            }

            @Override
            public void onLowMemory() {

            }
        });
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    public static synchronized App getInstance() {
        return instance;
    }
}
