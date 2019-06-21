package com.andysong.wanandroid.di.module;

import android.app.Application;

import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.db.DBHelper;
import com.andysong.wanandroid.model.db.RealmHelper;
import com.andysong.wanandroid.model.http.HttpHelper;
import com.andysong.wanandroid.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper,RealmHelper realmHelper) {
        return new DataManager(httpHelper,realmHelper);
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper){
        return realmHelper;
    }
}