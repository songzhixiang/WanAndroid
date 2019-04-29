package com.andysong.wanandroid.ui.presenter;

import com.andysong.wanandroid.core.RxPresenter;
import com.andysong.wanandroid.core.RxUtil;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.ui.contract.IndexContract;
import com.andysong.wanandroid.utils.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author AndySong on 2019/3/22
 * @Blog https://github.com/songzhixiang
 */
public class IndexPresenter extends RxPresenter<IndexContract.View> implements IndexContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public IndexPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getArticle(int page) {
        addSubscribe(mDataManager.getArticle(page)
        .compose(RxUtil.rxSchedulerHelper(null))
        .compose(RxUtil.handleResult())
        .subscribeWith(new CommonSubscriber<PageList<ArticleEntity>>(mView, "获取失败",true) {

            @Override
            public void onNext(PageList<ArticleEntity> articleEntityList) {
                mView.showArticle(articleEntityList);
            }
        }));
    }

    @Override
    public void getKnowledge(int page, int cid) {
        addSubscribe(mDataManager.getKnowledgeTreeArtcile(page, cid)
        .compose(RxUtil.rxSchedulerHelper(null))
        .compose(RxUtil.handleResult())
        .subscribeWith(new CommonSubscriber<PageList<ArticleEntity>>(mView,"获取失败",true){
            @Override
            public void onNext(PageList<ArticleEntity> articleEntityPageList) {
                mView.showArticle(articleEntityPageList);
            }
        }));
    }
}
