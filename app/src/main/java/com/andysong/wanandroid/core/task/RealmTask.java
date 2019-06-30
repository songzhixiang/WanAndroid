package com.andysong.wanandroid.core.task;

import com.andysong.wanandroid.utils.task.MainTask;

import io.realm.Realm;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * @author andysong
 * @data 2019-06-26
 * @discription xxx
 */
public class RealmTask extends MainTask {

    /**
     * 是否必须要在onCreate执行完成
     * @return
     */
    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public void run() {
        Realm.init(getApplication());
    }
}
