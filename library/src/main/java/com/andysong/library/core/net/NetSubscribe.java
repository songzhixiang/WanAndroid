package com.andysong.library.core.net;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetSubscribe {
    NetType netType() default NetType.AUTO;
}
