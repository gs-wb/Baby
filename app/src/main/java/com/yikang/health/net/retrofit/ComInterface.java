package com.yikang.health.net.retrofit;

import com.yikang.health.constant.Constants;
import com.yikang.health.model.BabyLoreModel;
import com.yikang.health.model.Mp3Info;
import com.yikang.health.model.WeatherModel;
import com.yikang.health.net.retrofit.model.HttpLoreResult;
import com.yikang.health.net.retrofit.model.HttpWeatherResult;
import com.yikang.health.net.retrofit.model.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 通用接口
 * Created by zwb on 2016/5/9.
 */
public interface ComInterface {

    /**
     * 查询文章信息
     *
     * @param currentPage
     * @return
     */
    @POST("/content/getContentList")
    @FormUrlEncoded
    Observable<HttpResult> getContentList(@Field("currentPage") int currentPage);

    /**
     * 查询音频信息
     *
     * @param currentPage
     * @param book_id
     * @return
     */
    @POST("/content/getMp3List")
    @FormUrlEncoded
    Observable<HttpResult<List<Mp3Info>>> getMp3List(@Field("currentPage") int currentPage,
                                                     @Field("book_id") String book_id);

    /**
     * 查询天气信息
     *
     * @param currentCity
     * @return
     */
    @GET(Constants.COMMON_API+"/apistore/weatherservice/recentweathers")
    @Headers(Constants.API_KEY+":"+Constants.API_KEY_VALUE)
    Observable<HttpWeatherResult<WeatherModel>> getWeatherByGet(@Query("cityname") String currentCity);

    /**
     * 查询孕婴知识信息
     *
     * @param id
     * @return
     */
    @GET(Constants.COMMON_API+"/tngou/lore/list")
    @Headers(Constants.API_KEY+":"+Constants.API_KEY_VALUE)
    Observable<HttpLoreResult<List<BabyLoreModel>>> getHealthLoreByGet(@Query("id") String id);

}
