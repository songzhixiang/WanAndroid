package com.andysong.wanandroid.core.http

import io.reactivex.disposables.Disposable

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
interface IRxHttpManager<T>{
    /**
     * 添加
     *
     * @param tag        tag
     * @param disposable disposable
     */
    fun add(tag: T, disposable: Disposable)

    /**
     * 移除请求
     *
     * @param tag tag
     */
    fun remove(tag: T)

    /**
     * 取消某个tag的请求
     *
     * @param tag tag
     */
    fun cancel(tag: T)

    /**
     * 取消某些tag的请求
     * @param tags tags
     */
    fun cancel(vararg tags: T)

    /**
     * 取消所有请求
     */
    fun cancelAll()
}