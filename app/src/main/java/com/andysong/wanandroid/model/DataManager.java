package com.andysong.wanandroid.model;

import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.http.HttpHelper;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<BannerEntity>>> getBanner() {
        return mHttpHelper.getBanner();
    }
}
