package com.andysong.wanandroid.widget.stateview;

import android.animation.Animator;
import android.view.View;

/**
 * @author AndySong on 2019/3/7
 * @Blog https://github.com/songzhixiang
 */
public interface AnimatorProvider {

    Animator showAnimation(View view);

    Animator hideAnimation(View view);
}
