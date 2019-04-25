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
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;

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
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        instance = this;
        Utils.init(this);
        Realm.init(this);
        registerActivityLifecycleCallbacks(new AppManagerCall());
        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int i) {


            }

            @Override
            public void onConfigurationChanged(Configuration configuration) {
                //android:configChanges="keyboardHidden|orientation|screenSize"
                // 设置该配置属性会使 Activity在配置改变时不重启，只执行onConfigurationChanged（）
            }

            @Override
            public void onLowMemory() {
                //若想兼容Android 4.0 则使用，否知直接使用OnTrimMemory（）
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
