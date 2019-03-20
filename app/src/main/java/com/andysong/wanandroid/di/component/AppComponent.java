package com.andysong.wanandroid.di.component;

import com.andysong.wanandroid.core.App;
import com.andysong.wanandroid.di.module.AppModule;
import com.andysong.wanandroid.di.module.HttpModule;
import com.andysong.wanandroid.model.DataManager;
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

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类


}