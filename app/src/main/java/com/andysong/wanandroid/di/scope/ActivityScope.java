package com.andysong.wanandroid.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}