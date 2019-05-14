package com.andysong.wanandroid.ui.presenter;

import com.andysong.wanandroid.core.RxPresenter;
import com.andysong.wanandroid.core.RxUtil;
import com.andysong.wanandroid.model.DataManager;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.andysong.wanandroid.ui.contract.NavigationContract;
import com.andysong.wanandroid.utils.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author AndySong on 2019/3/27
 * @Blog https://github.com/songzhixiang
 */
public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter {

    DataManager mDataManager;

    @Inject
    public NavigationPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getNavigationInfo() {
        addSubscribe(mDataManager.getNavigationInfo()
                .compose(RxUtil.rxSchedulerHelper(mView))
                .compose(RxUtil.handleResult())
                .subscribeWith(new CommonSubscriber<List<NavigationInfoEntity>>(mView,true) {
                    @Override
                    public void onNext(List<NavigationInfoEntity> navigationInfoEntities) {
                        mView.showNavigationInfo(navigationInfoEntities);
                    }
                }));
    }
}
