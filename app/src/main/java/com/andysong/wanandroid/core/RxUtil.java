package com.andysong.wanandroid.core;

import com.andysong.wanandroid.model.http.exception.ApiException;
import com.andysong.wanandroid.model.http.response.WanAndroidHttpResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class RxUtil {

    /**
     * 无参数
     *
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return switchSchedulers(null);
    }

    /**
     * 带参数  显示loading对话框
     *
     * @param baseview baseview
     * @param <T>         泛型
     * @return 返回Observable
     */
    public static <T> ObservableTransformer<T, T> switchSchedulers(final BaseView baseview) {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (baseview != null) {
                        baseview.stateLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally((Action) () -> {
                    if (baseview != null) {
                        baseview.stateMain();
                    }
                });
    }

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper(final BaseView baseView) {    //compose简化线程
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (baseView != null) {
                        baseView.stateLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete((Action) () -> {
                    if (baseView != null) {
                        baseView.stateMain();
                    }
                });
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<WanAndroidHttpResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<WanAndroidHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<WanAndroidHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<WanAndroidHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(WanAndroidHttpResponse<T> wanAndroidHttpResponse) {
                        if(wanAndroidHttpResponse.getErrorCode()== 0) {
                            return createData(wanAndroidHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
