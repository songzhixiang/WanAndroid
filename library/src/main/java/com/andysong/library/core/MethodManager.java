package com.andysong.library.core;

import com.andysong.library.core.net.NetType;

import java.lang.reflect.Method;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
public class MethodManager  {

    //参数类型
    private Class<?> parameterClz;

    //网络类型
    private NetType annotationNetType;

    //需要执行的方法
    private Method mMethod;

    public MethodManager(Class<?> parameterClz, NetType annotationNetType, Method method) {
        this.parameterClz = parameterClz;
        this.annotationNetType = annotationNetType;
        mMethod = method;
    }


    public Class<?> getParameterClz() {
        return parameterClz;
    }

    public void setParameterClz(Class<?> parameterClz) {
        this.parameterClz = parameterClz;
    }

    public NetType getAnnotationNetType() {
        return annotationNetType;
    }

    public void setAnnotationNetType(NetType annotationNetType) {
        this.annotationNetType = annotationNetType;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method method) {
        mMethod = method;
    }
}
