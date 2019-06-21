package com.andysong.wanandroid.di.component;

import android.app.Application;

import com.andysong.wanandroid.di.module.AppModule;
import com.andysong.wanandroid.di.module.HttpModule;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.db.RealmHelper;
import com.andysong.wanandroid.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    Application getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper(); // 提供数据库帮助类
}