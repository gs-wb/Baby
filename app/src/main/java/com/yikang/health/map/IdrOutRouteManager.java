package com.yikang.health.map;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.indoorun.mapapi.domain.OutMapPoint;
import com.indoorun.outapi.domain.DrivingRouteOverlay;

/**
 * Created by admin on 2016/3/15.
 */
public class IdrOutRouteManager {
    private RoutePlanSearch mSearch;
    private MyRoutePlanResultListener listener;
    private IRoutePlanCallBack mCallBack;
    private IdrOutMapView mMapView;
    private DrivingRouteOverlay overlay;
    private int sResId;
    private int eResId;

    public IdrOutRouteManager(IdrOutMapView mapView) {
        mMapView = mapView;
        init();
    }

    private void init() {
        mSearch = RoutePlanSearch.newInstance();
        listener = new MyRoutePlanResultListener();
        mSearch.setOnGetRoutePlanResultListener(listener);
    }

    public void setCallBack(IRoutePlanCallBack callBack) {
        mCallBack = callBack;
    }

    public void setStartAndEndIcon(int sResId, int eResId) {
        this.sResId = sResId;
        this.eResId = eResId;
    }

    public void startPlanRoute(OutMapPoint sPoint, OutMapPoint ePoint) {
        if (sPoint == null || ePoint == null) {
            mCallBack.onRoutePlanFailed("坐标点为空");
            return;
        }
        PlanNode stNode = PlanNode.withLocation(new LatLng(sPoint.latitude, sPoint.longitude));
        PlanNode enNode = PlanNode.withLocation(new LatLng(ePoint.latitude, ePoint.longitude));
        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));
    }

    public void stopPlanRoute() {
        if (overlay != null)
            overlay.removeFromMap();
        if (mSearch != null)
            mSearch.destroy();
    }

    private class MyRoutePlanResultListener implements OnGetRoutePlanResultListener {
        public MyRoutePlanResultListener() {

        }

        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            //获取步行线路规划结果
        }

        public void onGetTransitRouteResult(TransitRouteResult result) {
            //获取公交换乘路径规划结果
        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //获取驾车线路规划结果
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                mCallBack.onRoutePlanFailed("线路规划失败");
            } else if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                mCallBack.onRoutePlanFailed("起终点或途经点地址有岐义");
            } else if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                if (mMapView != null) {
                    overlay = new MyDrivingRouteOverlay(mMapView);
                    mCallBack.onRoutePlanSuccess();
                    //设置驾车路线规划数据
                    overlay.setData(result.getRouteLines().get(0));
                    //将驾车路线规划覆盖物添加到地图中
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }
            }
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    }

    class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(IdrOutMapView mapView) {
            super(mapView.getBaiduMap());
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (sResId != 0)
                return BitmapDescriptorFactory.fromResource(sResId);
            else
                return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (eResId != 0)
                return BitmapDescriptorFactory.fromResource(eResId);
            else
                return null;
        }
    }

    /**
     * 定位成功回调接口
     */
    public interface IRoutePlanCallBack {
        void onRoutePlanSuccess();

        void onRoutePlanFailed(String msg);
    }
}
