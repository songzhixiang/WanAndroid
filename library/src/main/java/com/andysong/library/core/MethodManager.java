package com.andysong.library.core;

import com.andysong.library.core.net.Mode;
import com.andysong.library.core.net.NetType;

import java.lang.reflect.Method;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
public class MethodManager {

    //参数类型
    private Class<?> parameterClazz;

    //订阅类型
    private Mode mode;

    //需要执行的方法
    private Method method;

    public MethodManager(Class<?> clazz, Mode mode, Method method) {
        this.parameterClazz = clazz;
        this.mode = mode;
        this.method = method;
    }

    public Class<?> getParameterClazz() {
        return parameterClazz;
    }

    public void setParameterClazz(Class<?> parameterClazz) {
        this.parameterClazz = parameterClazz;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
