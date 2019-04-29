package com.andysong.wanandroid.model;

import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.bean.History;
import com.andysong.wanandroid.model.bean.LoginResultEntity;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.model.db.DBHelper;
import com.andysong.wanandroid.model.http.HttpHelper;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class DataManager implements HttpHelper, DBHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;


    public DataManager(HttpHelper httpHelper, DBHelper dbHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<BannerEntity>>> getBanner() {
        return mHttpHelper.getBanner();
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getArticle(int page) {
        return mHttpHelper.getArticle(page);
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<LoginResultEntity>> login(@NotNull Map<String, String> map) {
        return mHttpHelper.login(map);
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<TreeEntity>>> getKnowledgeTree() {
        return mHttpHelper.getKnowledgeTree();
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<List<NavigationInfoEntity>>> getNavigationInfo() {
        return mHttpHelper.getNavigationInfo();
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getSearchArticles(int page, @NotNull RequestBody requestBody) {
        return mHttpHelper.getSearchArticles(page,requestBody);
    }

    @Override
    public void insertArticle(int id) {

    }

    @Override
    public void deleteArticle(int id) {

    }

    @NotNull
    @Override
    public List<ArticleEntity> queryAllArticle() {
        return null;
    }

    @Override
    public void insertHistory(@NotNull String name) {
        mDbHelper.insertHistory(name);
    }

    @Override
    public boolean deleteAllHistory() {
        return mDbHelper.deleteAllHistory();
    }

    @NotNull
    @Override
    public List<History> queryAllHistory() {
        return mDbHelper.queryAllHistory();
    }

    @NotNull
    @Override
    public Flowable<WanAndroidHttpResponse<PageList<ArticleEntity>>> getKnowledgeTreeArtcile(int page, int cid) {
        return mHttpHelper.getKnowledgeTreeArtcile(page,cid);
    }
}
