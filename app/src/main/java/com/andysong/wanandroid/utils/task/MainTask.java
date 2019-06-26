package com.andysong.wanandroid.utils.task;

/**
 * @author andysong
 * @data 2019-06-26
 * @discription 运行在主线程的Task
 */
public abstract class MainTask extends Task {

    @Override
    public boolean runOnMainThread() {
        return true;
    }
}
