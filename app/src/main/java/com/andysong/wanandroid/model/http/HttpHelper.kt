package com.andysong.wanandroid.model.http

import com.andysong.wanandroid.model.bean.*
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse
import io.reactivex.Flowable
import okhttp3.RequestBody
import okhttp3.ResponseBody

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
interface HttpHelper{

     fun getBanner(): Flowable<WanAndroidHttpResponse<List<BannerEntity>>>

     fun getArticle(page:Int): Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>>

     fun login(map:Map<String,String>): Flowable<WanAndroidHttpResponse<LoginResultEntity>>

     fun getKnowledgeTree():Flowable<WanAndroidHttpResponse<List<TreeEntity>>>

     fun getNavigationInfo(): Flowable<WanAndroidHttpResponse<List<NavigationInfoEntity>>>

     fun getSearchArticles(page: Int,requestBody: RequestBody):Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>>
}