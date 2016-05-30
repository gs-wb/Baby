package com.yikang.health.net.retrofit.utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * rx工具
 * Created by sharyuke on 16-3-11.
 */
public class RxUtil {

    /**
     * 后台线程执行同步，主线程执行异步操作
     *
     * @param <T> 数据类型
     * @return 观察者
     */
    public static <T> Observable.Transformer<T, T> background() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 后台线程执行同步，主线程执行异步操作，并且拦截所有错误
     *
     * @param <T> 数据类型
     * @return Transformer
     */
    public static <T> Observable.Transformer<T, T> backError() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.compose(background())
                        .compose(handlerError());
            }
        };
    }

    /**
     * 并且拦截所有错误，不让app崩溃
     *
     * @param <T> 数据类型
     * @return Transformer
     */
    public static <T> Observable.Transformer<T, T> handlerError() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.onErrorResumeNext(Observable.empty());
            }
        };
    }

    /**
     * 很多toList不执行的原因是因为没有complete，如果发现toList之后不执行，用此方法包装一下
     *
     * @param src 原来的observable
     * @param <T> 泛型
     * @return 新的observable
     */
    public static <T> Observable<T> wrapComplete(Observable<T> src) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.add(src
                        .doOnNext(subscriber::onNext)
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(T tableSearchHistories) {
                                subscriber.onCompleted();
                            }
                        })
                        .subscribe());
            }
        });
    }

    /**
     * 很多toList不执行的原因是因为没有complete，如果发现toList之后不执行，用此方法包装一下
     *
     * @param <T> 泛型
     * @return 转换器
     */
    public static <T> Observable.Transformer<T, T> wrapComplete() {
        return observable -> Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.add(observable
                        .doOnNext(subscriber::onNext)
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(T tableSearchHistories) {
                                subscriber.onCompleted();
                            }
                        })
                        .subscribe());
            }
        });
    }

}
