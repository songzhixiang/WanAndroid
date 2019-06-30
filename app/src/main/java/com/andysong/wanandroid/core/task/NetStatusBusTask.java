package com.andysong.wanandroid.core.task;

import com.andysong.library.core.net.NetStatusBus;
import com.andysong.wanandroid.utils.task.Task;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * @author andysong
 * @data 2019-06-26
 * @discription 需要在Utils初始化后在执行的
 */
public class NetStatusBusTask extends Task {


    /**
     * 需要依赖的方法，能保证依赖的方法在当前任务之前执行
     * @return
     */
    @Override
    public List<Class<? extends Task>> dependsOn() {
        List<Class<? extends Task>> task = new ArrayList<>();
        task.add(UtilsTask.class);
        return task;
    }

    @Override
    public void run() {
        NetStatusBus.getInstance().init(getApplication());
    }
}
