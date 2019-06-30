package com.andysong.wanandroid.core.task;

import android.os.Looper;
import android.os.MessageQueue;

import com.andysong.wanandroid.utils.task.DispatchRunnable;
import com.andysong.wanandroid.utils.task.Task;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author andysong
 * @data 2019-06-27
 * @discription 延迟加载
 */
public class DelayInitDispatcher {

    private Queue<Task> mTasks = new LinkedList<>();


    private MessageQueue.IdleHandler mIdleHandler = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            if (!mTasks.isEmpty()){
                Task task = mTasks.poll();
                new DispatchRunnable(task).run();
            }

            return !mTasks.isEmpty();
        }
    };

    public DelayInitDispatcher addTask(Task task){
        mTasks.add(task);
        return this;
    }

    public void start(){
        Looper.myQueue().addIdleHandler(mIdleHandler);
    }

}
