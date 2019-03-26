package com.andysong.wanandroid.ui.contract;

import com.andysong.wanandroid.core.BasePresenter;
import com.andysong.wanandroid.core.BaseView;
import com.andysong.wanandroid.model.bean.TreeEntity;

import java.util.List;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public interface KnowledgeContract {
    interface View extends BaseView {
        void showKnowledge(List<TreeEntity> treeEntityList);
    }

    interface Presenter extends BasePresenter<KnowledgeContract.View> {
        void getKnowledge();
    }
}
