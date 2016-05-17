package com.yikang.health.net;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.yikang.health.R;
import com.yikang.health.YIKApplication;
import com.yikang.health.constant.Constants;
import com.yikang.health.interfaces.TaskExpandListener;

/**
 * @author zwb
 */

public class PublicHttpData {

    /**
     * 单例对象实例
     */
    private static PublicHttpData instance = null;

    public static PublicHttpData getInstance() {
        if (instance == null) {
            instance = new PublicHttpData();
        }
        return instance;
    }

    private Map<String, Object> map = null;

    /**
     * 验证安卓版本
     * system/checkAndroidVersion
     */
    public void checkAndroidVersion(Context context, TaskExpandListener listener) {
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.CHECK_VERSION, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));
    }

    /**
     * 轮播图列表
     * system/bannerList
     */
    public void getAdBannerList(Context context, TaskExpandListener listener) {
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.AD_BANNER_LIST, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));
    }

    /**
     * 注册新用户
     * user/addUser
     * [username] 用户名
     * [password] 密码
     * [code] 验证码
     */
    public void addUser(Context context, String username, String password, String code, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        map.put("code", code);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.ADD_USER, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 获取校验码
     * user/addUser
     * [username] 用户名(手机号)
     */
    public void addCode(Context context, String username, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("username", username);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.ADD_CODE, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 用户登录
     * user/getUserLogin
     * [username] 用户名  [password] 密码  [mobileToken] 移动端标识
     */
    public void getUserLogin(Context context, String username, String password, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
//		map.put("mobileToken", "");
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_USER_LOGIN, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 添加意见反馈信息
     * user/addFeedback
     * [user_id] 用户索引ID
     * [title] 评论标题
     * [content] 评论内容
     */
    public void addFeedback(Context context, String user_id, String title, String content, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("userId", user_id);
        map.put("title", title);
        map.put("content", content);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.ADD_FEED_BACK, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }


    /**
     * 首页行业资讯列表
     * /user/getNoticeList
     * [currentPage] 当前页
     */
    public void getNoticeList(Context context, String currentPage, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("currentPage", currentPage);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_NOTICE_LIST, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 首页行业资讯详情
     * /user/getNoticeDetail
     * [noticeId] 行业资讯编号
     */
    public void getNoticeDetail(Context context, String noticeId, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("noticeId", noticeId);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_NOTICE_DETAIL, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 查询文章信息
     * content/getContentList
     * [currentPage] 当前页
     */
    public void getContentList(Context context, String currentPage, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("currentPage", currentPage);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_CONTENT_LIST, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 查询音频信息
     * /content/getMp3List
     * [currentPage] 当前页
     */
    public void getMp3List(Context context, String currentPage, String book_id, TaskExpandListener listener) {
        map = new HashMap<String, Object>();
        map.put("currentPage", currentPage);
        map.put("book_id", book_id);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_MP3_LIST, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 查询视频信息
     * /content/getVideoList
     * [currentPage] 当前页
     */
    public void getVideoList(Context context, String currentPage, TaskExpandListener listener) {
        map = new HashMap<>();
        map.put("currentPage", currentPage);
        ServerConnect.getInstance().requestToServer(context, listener,
                Constants.GET_VIDEO_LIST, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }


    /**
     * 查询天气
     */
    public void getWeatherByGet(Context context, String currentCity, TaskExpandListener listener) {
        map = new HashMap<>();
        map.put(Constants.API_KEY_VALUE, Constants.API_KEY);
        String url = Constants.COMMON_API + "apistore/weatherservice/cityname?" + Constants.CITY_NAME + "=" + currentCity;
        ServerConnect.getInstance().requestToServer(context, listener, url,
                Constants.GET_WEATHER_DATA, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 孕婴手册 知识列表
     */
    public void getBabyLoresByGet(Context context, String loreclass, TaskExpandListener listener) {
        map = new HashMap<>();
        map.put(Constants.API_KEY_VALUE, Constants.API_KEY);
        String url = Constants.COMMON_API + "tngou/lore/list?id=" + loreclass + "&rows=5";
        ServerConnect.getInstance().requestToServer(context, listener, url,
                Constants.GET_BABYLORE_DATA, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }

    /**
     * 孕婴手册 知识详情
     */
    public void getBabyLoreDetailByGet(Context context, String id, TaskExpandListener listener) {
        map = new HashMap<>();
        map.put(Constants.API_KEY_VALUE, Constants.API_KEY);
        String url = Constants.COMMON_API + "tngou/lore/show?id="+id;
        ServerConnect.getInstance().requestToServer(context, listener, url,
                Constants.GET_BABYLORE_DETAIL_DATA, map, YIKApplication.getContext().getResources()
                        .getString(R.string.request_dialog_loading_content));

    }
}
