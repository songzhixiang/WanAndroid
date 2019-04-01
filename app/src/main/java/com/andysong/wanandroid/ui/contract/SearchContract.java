package com.andysong.wanandroid.ui.contract;

import com.andysong.wanandroid.core.BasePresenter;
import com.andysong.wanandroid.core.BaseView;
import com.andysong.wanandroid.model.bean.ArticleEntity;

import java.util.List;

/**
 * @author AndySong on 2019/4/1
 * @Blog https://github.com/songzhixiang
 */
public interface SearchContract {

    interface View extends BaseView{

        void showSearchResult(List<ArticleEntity> articleEntityList);

        void showSearchHistroy();

        void showEditClear();

        void hideEditClear();

        void showSearchFail(String failMsg);
    }

    interface Presenter extends BasePresenter<SearchContract.View>{

        void search(String searchText, int page);

        void queryHistory();

        void deleteAllHistory();
    }
}
