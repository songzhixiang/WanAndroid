package com.andysong.wanandroid.model.prefs

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */

 interface PreferencesHelper {

    abstract fun getNightModeState(): Boolean

    abstract fun setNightModeState(state: Boolean)

    abstract fun getNoImageState(): Boolean

    abstract fun setNoImageState(state: Boolean)

    abstract fun getAutoCacheState(): Boolean

    abstract fun setAutoCacheState(state: Boolean)

    abstract fun getCurrentItem(): Int

    abstract fun setCurrentItem(item: Int)

    abstract fun getLikePoint(): Boolean

    abstract fun setLikePoint(isFirst: Boolean)

    abstract fun getVersionPoint(): Boolean

    abstract fun setVersionPoint(isFirst: Boolean)

    abstract fun getManagerPoint(): Boolean

    abstract fun setManagerPoint(isFirst: Boolean)

}
