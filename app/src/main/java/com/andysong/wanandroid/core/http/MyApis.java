package com.andysong.wanandroid.core.http;

import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public interface MyApis {
    String HOST = "https://www.wanandroid.com/";



    //获取banner
    @GET("banner/json")
    Flowable<WanAndroidHttpResponse<List<BannerEntity>>> getBanner();


    //获取首页文章
    @GET("article/list/0/json")
    Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getArticle();

    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Flowable<ResponseBody> login(@FieldMap Map<String, String> params);

    //登陆
    @POST("user/login")
    Flowable<ResponseBody> login2(@Body RequestBody requestBody);



}
