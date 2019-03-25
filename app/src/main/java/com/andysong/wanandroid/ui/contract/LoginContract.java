package com.andysong.wanandroid.ui.contract;

import com.andysong.wanandroid.core.BasePresenter;
import com.andysong.wanandroid.core.BaseView;

/**
 * @author AndySong on 2019/3/22
 * @Blog https://github.com/songzhixiang
 */
public interface LoginContract {

    interface View extends BaseView{
        void jumpToIndex();
    }

    interface Presenter extends BasePresenter<LoginContract.View>{
        void requestLogin();
    }
}
