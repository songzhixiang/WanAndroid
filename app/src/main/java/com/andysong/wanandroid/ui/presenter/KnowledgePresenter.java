package com.andysong.wanandroid.ui.presenter;

import com.andysong.wanandroid.core.RxPresenter;
import com.andysong.wanandroid.core.RxUtil;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.ui.contract.KnowledgeContract;
import com.andysong.wanandroid.utils.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class KnowledgePresenter extends RxPresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    DataManager mDataManager;

    @Inject
    public KnowledgePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getKnowledge() {
        addSubscribe(mDataManager.getKnowledgeTree()
                .compose(RxUtil.rxSchedulerHelper(null))
                .compose(RxUtil.handleResult())
                .subscribeWith(new CommonSubscriber<List<TreeEntity>>(mView,true) {
                    @Override
                    public void onNext(List<TreeEntity> treeEntityList) {
                        mView.showKnowledge(treeEntityList);
                    }
                }));
    }
}
