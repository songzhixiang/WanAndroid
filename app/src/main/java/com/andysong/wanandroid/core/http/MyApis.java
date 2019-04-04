package com.andysong.wanandroid.core.http;

import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.bean.LoginResultEntity;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.bean.TreeEntity;
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
import retrofit2.http.Path;

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
    @GET("article/list/{page}/json")
    Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getArticle(@Path("page") int page);

    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Flowable<WanAndroidHttpResponse<LoginResultEntity>> login(@FieldMap Map<String, String> params);

    //登陆
    @POST("user/login")
    Flowable<ResponseBody> login2(@Body RequestBody requestBody);


    /*=======知识体系======*/

    @GET("tree/json")
    Flowable<WanAndroidHttpResponse<List<TreeEntity>>> getKnowledgeTree();


    /*=======导航======*/
    @GET("navi/json")
    Flowable<WanAndroidHttpResponse<List<NavigationInfoEntity>>> getNavigationInfo();

    /*=======搜索======*/
    @POST("article/query/{page}/json")
    Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>>  getSearchArticles(@Path("page") int page,@Body RequestBody requestBody);


}
