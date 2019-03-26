package com.andysong.wanandroid.ui.presenter;

import com.andysong.wanandroid.core.RxPresenter;
import com.andysong.wanandroid.core.RxUtil;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.ui.contract.KnowledgeContract;

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
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(new Consumer<List<TreeEntity>>() {
                    @Override
                    public void accept(List<TreeEntity> treeEntities) throws Exception {
                        mView.showKnowledge(treeEntities);
                    }
                }));
    }
}
