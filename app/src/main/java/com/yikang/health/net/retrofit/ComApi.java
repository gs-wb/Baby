package com.yikang.health.net.retrofit;

import com.yikang.health.model.Mp3Info;
import com.yikang.health.net.retrofit.model.HttpResult;
import com.yikang.health.net.retrofit.utils.RxUtil;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * 通用接口
 * Created by zwb on 2016/5/30.
 */
public class ComApi {
    private ComInterface mComInterface;
    public static ComApi sInstance;

    public static ComApi getInstance() {
        synchronized (ComApi.class) {
            if (sInstance == null) {
                sInstance = new ComApi();
            }
        }
        return sInstance;
    }

    public ComApi() {
        mComInterface = NetClient.newRetrofit().create(ComInterface.class);
    }
    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (!httpResult.isSuccess()) {
                return null;
            }
            return httpResult.getData();
        }
    }
    /**
     * 查询音频信息
     */
    public Observable<List<Mp3Info>> getMp3List(int currentPage, String book_id) {
        return mComInterface.getMp3List(currentPage,book_id)
                .map(new HttpResultFunc<>())
                .compose(RxUtil.background());
    }


    public Observable<HttpResult> getContentList(int currentPage) {
        return mComInterface.getContentList(currentPage).compose(RxUtil.background());
    }

}
