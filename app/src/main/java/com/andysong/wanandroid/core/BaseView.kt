package com.andysong.wanandroid.core

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
interface BaseView{
    abstract fun showErrorMsg(msg: String)

    abstract fun stateError()

    abstract fun stateEmpty()

    abstract fun stateLoading()

    abstract fun hideLoading()

    abstract fun stateMain()
}