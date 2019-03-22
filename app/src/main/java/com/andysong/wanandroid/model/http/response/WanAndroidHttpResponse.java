package com.andysong.wanandroid.model.http.response;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class WanAndroidHttpResponse<T> {

    private String errorMsg;
    private int errorCode;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
