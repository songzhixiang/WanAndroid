package com.andysong.wanandroid.model.http

import com.andysong.wanandroid.model.bean.ArticleEntity
import com.andysong.wanandroid.model.bean.BannerEntity
import com.andysong.wanandroid.model.bean.PageList
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse
import io.reactivex.Flowable

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
interface HttpHelper{

     fun getBanner(): Flowable<WanAndroidHttpResponse<List<BannerEntity>>>

     fun getArticle(): Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>>
}