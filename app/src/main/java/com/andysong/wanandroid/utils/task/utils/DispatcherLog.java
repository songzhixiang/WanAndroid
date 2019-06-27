package com.andysong.wanandroid.utils.task.utils;

import android.util.Log;

/**
 * @author andysong
 * @data 2019-06-26
 * @discription xxx
 */
public class DispatcherLog {

    private static boolean sDebug = true;

    public static void i(String msg) {
        if (!sDebug) {
            return;
        }
        Log.i("task",msg);
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

}
