package com.andysong.wanandroid.ui.contract;


import com.andysong.wanandroid.core.BasePresenter;
import com.andysong.wanandroid.core.BaseView;
import com.andysong.wanandroid.model.bean.ArticleEntity;

import java.util.List;

/**
 * @author AndySong on 2019/3/22
 * @Blog https://github.com/songzhixiang
 */
public interface IndexContract {

    interface View extends BaseView{
        void showArticle(List<ArticleEntity> articleEntityList);
    }

    interface Presenter extends BasePresenter<View>{
        void getArticle(int page);
    }
}
