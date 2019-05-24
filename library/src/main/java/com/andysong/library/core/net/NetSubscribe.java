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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NetSubscribe {
    Mode mode() default Mode.AUTO;
}
