package com.yikang.health.net.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yikang.health.net.retrofit.model.HttpResult;
import com.yikang.health.utils.LogUtil;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络客户端
 * Created by zwb on 2016/5/9.
 */
public class NetClient {
    /**
     * 获取Retrofit适配器，此方法非单例，每次得到的都是一个全新的对象，所以调用此方法的对象，需要是单例对象。
     *
     * @return 网络适配器
     */
    public static Retrofit newRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://120.76.136.195:20165")
                .client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static Gson getGson(){
        return new GsonBuilder().registerTypeAdapter(HttpResult.class, (JsonDeserializer<HttpResult>)new JsonDeserializer<HttpResult>() {
            @Override
            public HttpResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject= json.getAsJsonObject();
                HttpResult resBaseModel=new HttpResult();
                resBaseModel.setMessage("哈哈");
                return resBaseModel;
            }
        }).create();
    }
    /**
     * 获取 OkHttpClient
     * @return  OkHttpClient
     */
    public static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(300, TimeUnit.SECONDS);
        LogUtil.setLevel(LogUtil.DEBUG);
        builder.addInterceptor(new HttpLoggingInterceptor(LogUtil::i).setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder;
    }
}
