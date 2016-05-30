package com.yikang.health.net.retrofit;

import com.yikang.health.model.Mp3Info;
import com.yikang.health.net.retrofit.model.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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

//    /**
//     * 初始化
//     *
//     * @param appKey    appKey
//     * @param phoneUUID 手机ID
//     * @return 响应
//     */
//    @POST("/ck/initSessionKey.html")
//    @FormUrlEncoded
//    Observable<ResCheckModel> check(@Field("appKey") String appKey, @Field("phoneUUID") String phoneUUID);
//
//    /**
//     * 登录
//     *
//     * @param appKey      appKey
//     * @param phoneUUID   手机ID
//     * @param userAccount 用户名
//     * @param pwdSign     md5 32位加密(会话ID+md5 32位加密(密码))
//     * @param sessionKey  会话ID
//     * @return 响应
//     */
//    @POST("/ck/login.html")
//    @FormUrlEncoded
//    Observable<ResLoginModel> login(@Field("appKey") String appKey,
//                                    @Field("phoneUUID") String phoneUUID,
//                                    @Field("userAccount") String userAccount,
//                                    @Field("pwdSign") String pwdSign,
//                                    @Field("sessionKey") String sessionKey);
//
//    /**
//     * 初始化会话
//     *
//     * @param appKey    appKey
//     * @param phoneUUID 手机ID
//     * @return 响应
//     */
//    @POST("/ck/initSessionKey.html")
//    @FormUrlEncoded
//    Observable<ResInitSessionModel> initSession(@Field("appKey") String appKey,
//                                                @Field("phoneUUID") String phoneUUID);
//
//
//    /**
//     * 检查状态
//     *
//     * @param appKey      appKey
//     * @param phoneUUID   手机ID
//     * @param sessionKey  会话ID
//     * @param regionId    区域ID
//     * @param checkStatus 检查状态
//     * @return 响应
//     */
//    @POST("/ck/updateRegionStatus.html")
//    @FormUrlEncoded
//    Observable<ResUpdateCheckStatusModel> updateCheckStatus(@Field("appKey") String appKey,
//                                                            @Field("phoneUUID") String phoneUUID,
//                                                            @Field("sessionKey") String sessionKey,
//                                                            @Field("regionId") String regionId,
//                                                            @Field("checkStatus") String checkStatus);
//
//    /**
//     * 根据用户所在位置的区域信息，也就是楼层列表
//     *
//     * @param appKey            appKey
//     * @param phoneUUID         手机ID
//     * @param sessionKey        会话ID
//     * @param internalDetection 测试用 1 标识测试
//     * @return 响应
//     */
//    @POST("/ck/getRegionsOfUser.html")
//    @FormUrlEncoded
//    Observable<ResRegionListModel> getRegionListOfUser(@Field("appKey") String appKey,
//                                                       @Field("phoneUUID") String phoneUUID,
//                                                       @Field("sessionKey") String sessionKey,
//                                                       @Field("internalDetection") String internalDetection);
//
//    /**
//     * 获取区域所有信息
//     *
//     * @param regionId          区域ID
//     * @param unitFlag          是否返回unit信息，可选，为0不输出，不存在或者为非0，则输出
//     * @param pathStructureFlag 是否返回行走路径信息，可选，0不输出，不存在或者其他值输出
//     * @return 响应
//     */
//    @POST("/ck/getRegionAllInfo.html")
//    @FormUrlEncoded
//    Observable<ResRegionInfoModel> getRegionAllInfo(@Field("regionId") String regionId,
//                                                    @Field("unitFlag") String unitFlag,
//                                                    @Field("pathStructureFlag") String pathStructureFlag);
//
//    /**
//     * 获取区域所有信息
//     *
//     * @param regionId   区域ID
//     * @param withFloors 是否返回floor信息，可选，为0不输出，不存在或者为非0，则输出
//     * @return 响应
//     */
//    @POST("/ck/getRegionInfo.html")
//    @FormUrlEncoded
//    Observable<ResRegionInfoModel> getRegionInfo(@Field("regionId") String regionId,
//                                                 @Field("withFloors") String withFloors);
//
//    /**
//     * 根据楼层来获取单元列表
//     *
//     * @param appKey     appKey
//     * @param phoneUUID  手机ID
//     * @param sessionKey 会话ID
//     * @param floorId    楼层ID
//     * @return 响应
//     */
//    @POST("/ck/getUnitsByFloorId.html")
//    @FormUrlEncoded
//    Observable<ResRegionFloorUnitModel> getUnitListByFloorId(@Field("appKey") String appKey,
//                                                             @Field("phoneUUID") String phoneUUID,
//                                                             @Field("sessionKey") String sessionKey,
//                                                             @Field("floorId") String floorId);
//
//
//    /**
//     * 根据楼层来获取采集线列表
//     *
//     * @param appKey     appKey
//     * @param phoneUUID  手机ID
//     * @param sessionKey 会话ID
//     * @param floorId    楼层ID
//     * @return 响应
//     */
//    @POST("/ck/getFloorCollectLines.html")
//    @FormUrlEncoded
//    Observable<ResFloorLinesModel> getFloorCollectLines(@Field("appKey") String appKey,
//                                                        @Field("phoneUUID") String phoneUUID,
//                                                        @Field("sessionKey") String sessionKey,
//                                                        @Field("floorId") String floorId);
//
//    /**
//     * 获取单个region中多楼层的电梯，扶梯，楼梯，的贯通设置，返回多条贯通线路，每个线路中包含多个楼层中的电扶梯unit信息，这些电扶梯可以相互到达。
//     * 对于多楼层中电扶梯贯通无特别清空且对位比较整齐的region可不使用此方法，仍用以前的方法。该信息同事也可从getRegionAllInfo方法中获取。该信息的设置在管理后台项目列表的“楼层贯通”
//     *
//     * @param appKey     appKey
//     * @param phoneUUID  手机ID
//     * @param sessionKey 会话ID
//     * @param regionId   区域ID
//     * @return 响应
//     */
//    @POST("/ck/getRegionFloorPassData.html")
//    @FormUrlEncoded
//    Observable<ResPassModel> getRegionFloorPassData(@Field("appKey") String appKey,
//                                                    @Field("phoneUUID") String phoneUUID,
//                                                    @Field("sessionKey") String sessionKey,
//                                                    @Field("regionId") String regionId);
//
//    /**
//     * 根据关键字查询unit列表
//     *
//     * @param appKey      appKey
//     * @param phoneUUID   手机ID
//     * @param sessionKey  会话ID
//     * @param regionId    区域ID ，必传
//     * @param floorId     楼层ID ，可选
//     * @param unitNameKey 必须，当其为空时，匹配所有unit
//     * @return 响应
//     */
//    @POST("/ck/findUnitsByNameKey.html")
//    @FormUrlEncoded
//    Observable<ResRegionFloorUnitModel> findUnitsByNameKey(@Field("appKey") String appKey,
//                                                           @Field("phoneUUID") String phoneUUID,
//                                                           @Field("sessionKey") String sessionKey,
//                                                           @Field("regionId") String regionId,
//                                                           @Field("floorId") String floorId,
//                                                           @Field("unitNameKey") String unitNameKey);
//
//    /**
//     * 获取某个region下的导航定位基础数据,用于app离线定位
//     *
//     * @param regionId 区域id
//     * @return 响应
//     */
//    @POST("/ck/getNaviBaseDataOfRegion.html")
//    @FormUrlEncoded
//    Observable<ResNaviBaseDataModel> getNaviBaseDataModel(@Field("regionId") String regionId);
//
//    /**
//     * 获取楼层地图
//     *
//     * @param floorId 楼层
//     * @return 响应
//     */
//    @POST("/ck/getFloorMap.html")
//    @FormUrlEncoded
//    Observable<JsonElement> getRegionFloorMap(@Field("floorId") String floorId);
//
//    /**
//     * 按条件查询区域列表
//     *
//     * @param regionType 区域类型
//     * @param cityId     城市ID
//     * @param regionArea 区域范围
//     * @param regionName 区域模糊查询key
//     * @return 区域列表
//     */
//    @POST("/ck/queryRegions.html")
//    @FormUrlEncoded
//    Observable<ResRegionListModel> queryRegions(@Field("appKey") String appKey,
//                                                @Field("phoneUUID") String phoneUUID,
//                                                @Field("sessionKey") String sessionKey,
//                                                @Field("regionType") String regionType,
//                                                @Field("cityId") String cityId,
//                                                @Field("regionArea") String regionArea,
//                                                @Field("regionName") String regionName);
//
//    /**
//     * 根据unit的热度返回unit列表,用于展会,商场,等region中,返回的是有热度的unit,没有点击过或者导航到的unit不返回,unit的热度是有周期的,目前只统计当前10天内的点击数或者导航数;返回的unit按热度排排序,热度最高的在前
//     *
//     * @param appKey     appKey
//     * @param phoneUUID  手机ID
//     * @param sessionKey 会话ID
//     * @param regionId   区域Id,必传
//     * @param floorId    楼层Id,可选,不传,则返回该region下所有符合的unit
//     * @param maxCount   返回的最大unit数量,可选
//     * @param minHeat    最小热度值,可选
//     * @return
//     */
//    @POST("/ck/getUnitsByHeatOfRegion.html")
//    @FormUrlEncoded
//    Observable<ResRegionFloorUnitModel> getUnitsByHeatOfRegion(@Field("appKey") String appKey,
//                                                               @Field("phoneUUID") String phoneUUID,
//                                                               @Field("sessionKey") String sessionKey,
//                                                               @Field("regionId") String regionId,
//                                                               @Field("floorId") String floorId,
//                                                               @Field("maxCount") String maxCount,
//                                                               @Field("minHeat") String minHeat);
//
//    /**
//     * 获取某region下的所有外部出口，返回的是外部出口unit对象列表。该外部出口列表在getRegionInfo和getRegionAllInfo方法中也会输出，属性名称为outerExitList。
//     *
//     * @param appKey     appKey
//     * @param phoneUUID  手机ID
//     * @param sessionKey 会话ID
//     * @param regionId   区域Id,必传
//     * @return 外部出口unit对象列表
//     */
//    @POST("/ck/getOuterExitListOfRegion.html")
//    @FormUrlEncoded
//    Observable<ResOuterExitListModel> getOuterExitListOfRegion(@Field("appKey") String appKey,
//                                                               @Field("phoneUUID") String phoneUUID,
//                                                               @Field("sessionKey") String sessionKey,
//                                                               @Field("regionId") String regionId);
}
