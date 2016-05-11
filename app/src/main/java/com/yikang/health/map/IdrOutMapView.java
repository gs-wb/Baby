package com.yikang.health.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.indoorun.mapapi.domain.OutMapPoint;

import java.util.ArrayList;
import java.util.List;

public class IdrOutMapView extends FrameLayout {

    // 百度地图
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private Activity activity = null;
    //保存所有的Overlay
    private List<Overlay> mOverlayList = new ArrayList<Overlay>();

    public IdrOutMapView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public IdrOutMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public IdrOutMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        activity = (Activity) context;
        mapView = new MapView(context, attrs, defStyle);
        addView(mapView);
        mapView.showZoomControls(false);
//        goneView();
        mBaiduMap = mapView.getMap();
        // 普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(null, true, null));
//        地图初始比例为50
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(15).build()));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
    }

    /**
     * 设置地图比例     {"50m","100m","200m","500m","1km","2km","5km","10km","20km","25km","50km","100km","200km","500km","1000km","2000km"}
     * @param level  18~3,
     */
    public void setMapStatus(float level){
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(level));
    }

    public BaiduMap getBaiduMap() {
        return mBaiduMap;
    }

//    private void goneView() {
//        // 隐藏缩放控件
//        int childCount = mapView.getChildCount();
//        View zoom = null;
//        for (int i = 0; i < childCount; i++) {
//            View child = mapView.getChildAt(i);
//            if (child instanceof ZoomControls) {
//                zoom = child;
//                break;
//            }
//        }
//        if(zoom!=null)zoom.setVisibility(View.GONE);
//    }

    /**
     * 将所有Overlay 从 地图上消除
     */
    private void removeFromMap() {
        if (mBaiduMap == null) {
            return;
        }
        for (Overlay marker : mOverlayList) {
            marker.remove();
        }
        mOverlayList.clear();
    }

    /**
     * 刷新地图
     */
    public void refresh() {
    }

    /**
     * Activity onDestory时调用
     */
    public void onDestory() {
        // 退出时销毁定位
        if (mapView != null && mBaiduMap != null) {
            removeFromMap();
            // 关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
            mapView.onDestroy();
            mapView = null;
        }
    }

    /**
     * Activity onStart时调用
     */
    public void onStart() {
        // 开启定位图层
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(true);
        }
    }

    /**
     * Activity onStop时调用
     */
    public void onStop() {
        // 开启定位图层
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
        }
    }

    /**
     * Activity onPause时调用
     */
    public void onPause() {
        if (mapView != null) {
            this.mapView.onPause();
        }
    }

    /**
     * Activity onResume时调用
     */
    public void onResume() {
        if (mapView != null) {
            this.mapView.onResume();
        }
    }


    // 添加文字标注物
    public void addMarker(OutMapPoint e, String text, int textSize, int color) {
        LatLng ll = new LatLng(e.latitude, e.longitude);
        OverlayOptions ooText = new TextOptions().bgColor(0xAAFFFF00)
                .fontSize(textSize).fontColor(color).text(text).rotate(0)
                .position(ll);
        mOverlayList.add(mBaiduMap.addOverlay(ooText));
    }

    // 添加文字标注物
    public void addMarker(OutMapPoint e, String text, int drawableId) {
        //定义Maker坐标点
        LatLng point = new LatLng(e.latitude, e.longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(drawableId);
        //构建MarkerOption，用于在地图上添加Marker
        MarkerOptions option = new MarkerOptions().position(point).icon(bitmap);
        TextView title = new TextView(activity);
        title.setText(text);
        title.setTextSize(18);
        //在地图上添加Marker，并显示
        mOverlayList.add(mBaiduMap.addOverlay(option));
        mBaiduMap.showInfoWindow(new InfoWindow(title, point, -100));
    }

    // 添加图片标注物
    public void addMarker(OutMapPoint e, int drawableId) {
        LatLng ll = new LatLng(e.latitude, e.longitude);
        BitmapDescriptor bd = BitmapDescriptorFactory
                .fromResource(drawableId);
        MarkerOptions oo = new MarkerOptions().position(ll).icon(bd).title(e.name);
        mOverlayList.add(mBaiduMap.addOverlay(oo));
    }


    /**
     * 室外地图放大、缩小
     *
     * @param ZoomType
     * @see IdrOutApplication
     */
    public void setMapZoom(int ZoomType) {
        switch (ZoomType) {
            case IdrOutApplication.OUTMAP_ZOOM_IN:
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.zoomIn());
//                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                break;
            case IdrOutApplication.OUTMAP_ZOOM_OUT:
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.zoomOut());
//                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                break;
        }
    }

    /**
     * 地图移动到当前所在位置
     */
    public void setCurrent(OutMapPoint point) {
        if (mBaiduMap == null) return;
        try {
            MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(new LatLng(point.latitude, point.longitude));
            mBaiduMap.animateMapStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMe(OutMapPoint point) {
        if (mBaiduMap == null) return;
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(point.radius)
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(point.latitude)
                .longitude(point.longitude).build();
        mBaiduMap.setMyLocationData(locData);
    }

    /**
     * 设置地图移动监听
     *
     * @param onIdrOutMapTouchListener
     */
    public void setIdrOutMapMoveListener(final OnIdrOutMapMoveListener onIdrOutMapTouchListener) {
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    onIdrOutMapTouchListener.onMoved();
                }
            }
        });
    }

    /**
     * 设置点击地图的监听
     *
     * @param onIdrOutMapClickListener
     */
    public void setIdrOutMapOnclickListener(final OnIdrOutMapClickListener onIdrOutMapClickListener) {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onIdrOutMapClickListener.onMapClick();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private Marker tempMarker;
    private int tempOldResId;

    /**
     * 设置地图点击marker 监听事件
     *
     * @param hasText                        点击之后是否显示 marker 上边的字
     * @param oldResId
     * @param newResId
     * @param onIdrOutMapMarkerClickListener
     */
    public void setOnMarkerClickListener(final boolean hasText, final int oldResId, final int newResId, final OnIdrOutMapMarkerClickListener onIdrOutMapMarkerClickListener) {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                initMarker(hasText, oldResId, newResId, marker);
                OutMapPoint point = new OutMapPoint(marker.getPosition().longitude, marker.getPosition().latitude, 0, marker.getTitle());
                return onIdrOutMapMarkerClickListener.onMarkerClick(point);
            }
        });
    }

    private void initMarker(boolean hasText, int oldResId, int newResId, Marker marker) {
        if (marker == null) return;
        BitmapDescriptor bitmap = null;
        if (oldResId == 0) {
            bitmap = marker.getIcon();
        } else {
            bitmap = BitmapDescriptorFactory.fromResource(oldResId);
        }
        tempOldResId = newResId;
        if (tempMarker != null) {
            BitmapDescriptor tempBitmap = null;
            if (newResId == 0) {
                tempBitmap = marker.getIcon();
            } else {
                tempBitmap = BitmapDescriptorFactory.fromResource(newResId);
            }
            tempMarker.setIcon(tempBitmap);
        }
        marker.setIcon(bitmap);
        tempMarker = marker;
        if (hasText) {
            TextView tv = new TextView(activity);
            tv.setText(marker.getTitle());
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setBackgroundColor(Color.parseColor("#ffffff"));
            tv.setPadding(10, 10, 10, 10);
            mBaiduMap.showInfoWindow(new InfoWindow(tv, marker.getPosition(), -120));
        }
    }
    public void performMarkerClick(boolean hasText, int oldResId, int newResId,OutMapPoint point){
        if(point == null)return;
        Marker marker = null;
        for (Overlay overlay : mOverlayList) {
            if (overlay instanceof Marker) {
                if(((Marker) overlay).getTitle().equals(point.name)){
                    marker = ((Marker)overlay);
                    break;
                }
            }
        }
        initMarker(hasText,oldResId,newResId,marker);
    }

    /**
     * 还原原来的marker状态
     */
    public void resetMarkerStutas() {
        if (tempMarker != null && tempOldResId != 0) {
            BitmapDescriptor tempBitmap = BitmapDescriptorFactory.fromResource(tempOldResId);
            tempMarker.setIcon(tempBitmap);
        }
    }

    /**
     * 地图移动 回调接口
     */
    public interface OnIdrOutMapMoveListener {
        void onMoved();
    }

    /**
     * 点击地图 回调接口
     */
    public interface OnIdrOutMapClickListener {
        void onMapClick();
    }

    /**
     * 地图点击marker 监听事件
     */
    public interface OnIdrOutMapMarkerClickListener {
        boolean onMarkerClick(OutMapPoint point);
    }
}
