package com.andysong.wanandroid.ui.contract;

import com.andysong.wanandroid.core.BasePresenter;
import com.andysong.wanandroid.core.BaseView;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;

import java.util.List;

/**
 * @author AndySong on 2019/3/27
 * @Blog https://github.com/songzhixiang
 */
public interface NavigationContract {
    interface View extends BaseView {
        void showNavigationInfo(List<NavigationInfoEntity> navigationInfoEntities);
    }

    interface Presenter extends BasePresenter<NavigationContract.View> {
        void getNavigationInfo();
    }
}
