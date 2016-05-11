package com.yikang.health.map;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.indoorun.mapapi.core.data.IndoorunSDKDataCenter;
import com.indoorun.mapapi.domain.OutMapPoint;
import com.indoorun.outapi.utils.LocationUtil;

/**
 * Created by admin on 2016/3/10.
 */
public class IdrOutLocateManager {
    private Context mContext = null;
    private LocationClient mLocClient;

    public IdrOutLocateManager(Context context) {
        mContext = context;
    }

    MyBDLocationListener mBDLocationListener = null;

    public void initLocation(final ILocationCallBack mLocationCallBack, int s) {
        // 定位初始化
        mLocClient = new LocationClient(mContext);
        mBDLocationListener = new MyBDLocationListener(mLocationCallBack);
        mLocClient.registerLocationListener(mBDLocationListener);
        LocationUtil.initLocation(mLocClient, s);//1000表示 每隔一秒调用一次
    }

    class MyBDLocationListener implements BDLocationListener {
        ILocationCallBack mLocationCallBack;

        public MyBDLocationListener(ILocationCallBack locationCallBack) {
            mLocationCallBack = locationCallBack;
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("定位结果描述：location = null");
                return;
            }
            switch (location.getLocType()) {
                case BDLocation.TypeGpsLocation:// GPS定位结果
                case BDLocation.TypeNetWorkLocation:// 网络定位结果
                    OutMapPoint point = new OutMapPoint(location.getLongitude(), location.getLatitude(), location.getRadius(), "");
//                    addrStr, city, cityCode, district, province, street, streetNumber
                    point.initAddrInfo(location.getAddrStr(), location.getCity(), location.getCityCode(), location.getDistrict(), location.getProvince(), location.getStreet(), location.getStreetNumber());
                    IndoorunSDKDataCenter.getInstance().setCurrentOutMapPoint(point);//自动记录当前坐标
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveLocation(point);
                    break;
                case BDLocation.TypeServerError:
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("定位结果描述：server定位失败，没有对应的位置信息");
                    break;
                case BDLocation.TypeNetWorkException:
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("网络连接失败，请检查手机是否能够正常上网");
                    break;
                case BDLocation.TypeCriteriaException:
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("无法定位结果,请关闭飞行模式或者打开wifi");
                    break;
                case BDLocation.TypeNone:
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("定位结果描述：无效定位结果--TypeNone");
                    break;
                default:
//					LogUtil.e("定位结果描述：无效定位结果--UnknowException");
                    if(mLocationCallBack!=null)mLocationCallBack.onReceiveFailed("无效定位结果--UnknowException");
                    break;
            }
        }
    }

    /**
     * Activity onDestory时调用
     */
    public void onDestory() {
        // 退出时销毁定位
        if (mLocClient != null) {
            if(mBDLocationListener!=null)mLocClient.unRegisterLocationListener(mBDLocationListener);
            mLocClient.stop();
        }
    }

    /**
     * 开启定位
     */
    public void startLocate() {
        if (mLocClient != null) mLocClient.start();
    }

    /**
     * 关闭定位
     */
    public void stopLocate() {
        if (mLocClient != null) mLocClient.stop();
    }

    /**
     * 定位成功回调接口
     */
    public interface ILocationCallBack {
        void onReceiveLocation(OutMapPoint point);

        void onReceiveFailed(String errMsg);
    }
}

