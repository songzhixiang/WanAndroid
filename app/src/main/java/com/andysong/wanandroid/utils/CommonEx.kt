package com.andysong.wanandroid.utils

import android.content.Context
import android.graphics.PorterDuff
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andysong.wanandroid.core.App
import java.io.File

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */

infix fun Context.takeColor(colorId: Int) = ContextCompat.getColor(this, colorId)

var View.scale: Float
    get() = Math.min(scaleX, scaleY)
    set(value) {
        scaleY = value
        scaleX = value
    }
val PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data"

fun Int.toPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}

fun View.onClick(function: () -> Unit) {
    setOnClickListener {
        function()
    }
}

fun <T> unSafeLazy(initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        initializer()
    }
}

inline fun delay(milliseconds: Long, crossinline action: () -> Unit) {
    Handler().postDelayed({
        action()
    }, milliseconds)
}

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)



fun ImageView.tint(colorId: Int) {
    setColorFilter(context.takeColor(colorId), PorterDuff.Mode.SRC_IN)
}