package com.andysong.wanandroid.core

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */

interface BasePresenter<T : BaseView>{

    abstract fun attachView(view: T)

    abstract fun detachView()
}