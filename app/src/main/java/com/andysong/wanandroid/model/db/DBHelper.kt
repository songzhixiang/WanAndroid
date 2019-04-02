package com.andysong.wanandroid.model.db

import com.andysong.wanandroid.model.bean.ArticleEntity
import com.andysong.wanandroid.model.bean.History

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
interface DBHelper {

    //浏览历史
    fun insertArticle(id:Int)

    fun deleteArticle(id:Int)

    fun queryAllArticle():List<ArticleEntity>

    //搜索历史
    fun insertHistory(name:String)

    fun deleteAllHistory():Boolean

    fun queryAllHistory():List<History>


}