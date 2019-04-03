package com.andysong.wanandroid.model.db;

import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.History;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * @author AndySong on 2019/4/2
 * @Blog https://github.com/songzhixiang
 */
public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    @Override
    public void insertArticle(int id) {

    }

    @Override
    public void deleteArticle(int id) {

    }

    @NotNull
    @Override
    public List<ArticleEntity> queryAllArticle() {
        return null;
    }



    @Override
    public boolean deleteAllHistory() {
        boolean b = false;
        RealmResults<History> all = mRealm.where(History.class).findAll();
        mRealm.beginTransaction();
        if (null!=all&&!all.isEmpty()){
            b = all.deleteAllFromRealm();
        }
        mRealm.commitTransaction();

        return b;
    }

    @NotNull
    @Override
    public List<History> queryAllHistory() {
        return mRealm.where(History.class).findAll();
    }

    @Override
    public void insertHistory(String name) {
        History history = new History();
        history.setId(System.currentTimeMillis());
        history.setName(name);
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(history);
        mRealm.commitTransaction();
    }
}
