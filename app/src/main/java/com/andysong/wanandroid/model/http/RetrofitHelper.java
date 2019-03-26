package com.andysong.wanandroid.model.http;

import com.andysong.wanandroid.core.http.GoldApis;
import com.andysong.wanandroid.core.http.MyApis;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class RetrofitHelper implements HttpHelper {
    private GoldApis mGoldApiService;
    private MyApis mMyApiService;

    @Inject
    public RetrofitHelper(GoldApis goldApiService, MyApis myApiService) {
        mGoldApiService = goldApiService;
        mMyApiService = myApiService;
    }


    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<BannerEntity>>> getBanner() {
        return mMyApiService.getBanner();
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getArticle(int page) {
        return mMyApiService.getArticle(page);
    }

    @NotNull
    @Override
    public Flowable<ResponseBody> login(@NotNull Map<String, String> map) {
        return mMyApiService.login(map);
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<TreeEntity>>> getKnowledgeTree() {
        return mMyApiService.getKnowledgeTree();
    }
}
