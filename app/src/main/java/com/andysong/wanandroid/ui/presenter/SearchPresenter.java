package com.andysong.wanandroid.ui.presenter;

import com.andysong.wanandroid.core.RxPresenter;
import com.andysong.wanandroid.core.RxUtil;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.BannerEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.ui.contract.SearchContract;
import com.blankj.utilcode.util.LogUtils;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author AndySong on 2019/4/1
 * @Blog https://github.com/songzhixiang
 */
public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter {

    DataManager mDataManager;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void search(String searchText, int page) {
        RequestBody requestBody =  new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("k",searchText)
                .build();
        mDataManager.insertHistory(searchText);
        addSubscribe(mDataManager.getSearchArticles(page,requestBody)
                .compose(RxUtil.rxSchedulerHelper(null))
                .compose(RxUtil.handleResult())
                .subscribe(new Consumer<PageList<ArticleEntity>>() {
                    @Override
                    public void accept(PageList<ArticleEntity> articleEntityPageList) throws Exception {
                        mView.showSearchResult(articleEntityPageList.getData());
                    }
                }));
    }

    @Override
    public void queryHistory() {
        mView.showSearchHistroy(mDataManager.queryAllHistory());
    }

    @Override
    public void deleteAllHistory() {
        if (mDataManager.deleteAllHistory()){
            queryHistory();
        }
    }
}
