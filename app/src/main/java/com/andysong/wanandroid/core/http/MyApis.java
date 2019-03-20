package com.andysong.wanandroid.core.http;

import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public interface MyApis {
    String HOST = "https://www.wanandroid.com/";

    @GET("banner/json")
    Flowable<WanAndroidHttpResponse<List<BannerEntity>>> getBanner();


}
