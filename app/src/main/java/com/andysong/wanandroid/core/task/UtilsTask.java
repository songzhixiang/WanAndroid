package com.andysong.wanandroid.core.task;

import com.andysong.wanandroid.utils.task.Task;
import com.blankj.utilcode.util.Utils;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * @author andysong
 * @data 2019-06-26
 * @discription xxx
 */
public class UtilsTask extends Task {
    @Override
    public void run() {
        Utils.init(getApplication());
    }
}
