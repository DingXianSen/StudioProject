package com.example.lesson29_baidumap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.BikingRouteOverlay;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextureMapView mapView;
    private BaiduMap baiduMap;
    private MLocation location;
    private boolean isFirst = true;
    private CheckBox cbMayType;
    private CheckBox cbTranffic;
    private EditText edit;
    private Button bt_search;
    private PoiSearch poiSearch;
    private BitmapDescriptor icon;

    private RoutePlanSearch rpSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化地图，参数要使用全局上下文
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (TextureMapView) findViewById(R.id.mapview);
        cbMayType = (CheckBox) findViewById(R.id.map_type);
        cbTranffic = (CheckBox) findViewById(R.id.tranffic);

        edit = (EditText) findViewById(R.id.edit);
        bt_search = (Button) findViewById(R.id.search);
        //获取地图管理者
        baiduMap = mapView.getMap();
        //开启显示我的位置信息
        baiduMap.setMyLocationEnabled(true);

        location = new MLocation(getApplicationContext());
        registerReceiver(receiver, new IntentFilter("location"));
        cbMayType.setOnCheckedChangeListener(changeLis);
        cbTranffic.setOnCheckedChangeListener(changeLis);

        //点击时间
//        地图的点击事件
        baiduMap.setOnMapClickListener(mapClickLis);
//        MarkerOption的点击事件
        baiduMap.setOnMarkerClickListener(markerClickList);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(porLis);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.search:
                        poiSearchs();
                        break;
                }
            }
        });

        icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);

        rpSearch = RoutePlanSearch.newInstance();
        rpSearch.setOnGetRoutePlanResultListener(rprLis);

    }

    private OnGetRoutePlanResultListener rprLis = new OnGetRoutePlanResultListener() {
        @Override//步行
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override//驾车
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override//公交
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        @Override//地铁
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

        }

        @Override//室内
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override//自行车
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            if (bikingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                BikingRouteOverlay overlay = new BikingRouteOverlay(baiduMap);
                overlay.setData(bikingRouteResult.getRouteLines().get(0));
                overlay.addToMap();
            }
        }
    };

    //    地图点击事件
    private BaiduMap.OnMapClickListener mapClickLis = new BaiduMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
//        隐藏InfoWindow
            baiduMap.hideInfoWindow();
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            Toast.makeText(MainActivity.this, mapPoi.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    private BaiduMap.OnMarkerClickListener markerClickList = new BaiduMap.OnMarkerClickListener() {

        @Override
        public boolean onMarkerClick(Marker marker) {
            PoiInfo info = marker.getExtraInfo().getParcelable("poi");
            if (info != null) {
                Button bt = new Button(MainActivity.this);
                bt.setText("到这里");
                bt.setTag(info.location);
                bt.setOnClickListener(toThereLis);
                //参数1：要显示的内容  2：显示的位置 ， 3在位置y向的偏移量
                InfoWindow window = new InfoWindow(bt, info.location, -50);
                baiduMap.showInfoWindow(window);
            }
            return false;
        }
    };
//    private BaiduMap.OnMarkerClickListener markerClickList = new BaiduMap.OnMarkerClickListener() {
//        @Override
//        public boolean onMarkerClick(Marker marker) {
//            PoiInfo info = marker.getExtraInfo().getParcelable("poi");
//            if (info != null) {
//                Button bu = new Button(MainActivity.this);
//                bu.setText("到这里");
//                bu.setTag(info.location);
//                bu.setOnClickListener(toThereLis);
////                参数1：要显示的内容，2：显示的位置，3：在位置y向的偏移量
//                InfoWindow window = new InfoWindow(bu, info.location, -50);
////                显示infowindow
//                baiduMap.showInfoWindow(window);
//            }
//            return false;
//        }
//    };

    private View.OnClickListener toThereLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LatLng ll = (LatLng) v.getTag();
            if (ll != null) {
                PlanNode start = PlanNode.withCityNameAndPlaceName("北京", "龙泽");
                PlanNode end = PlanNode.withLocation(ll);
                rpSearch.bikingSearch(new BikingRoutePlanOption()
                        .from(start).to(end));
                Toast.makeText(MainActivity.this, "正在搜索", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private OnGetPoiSearchResultListener porLis = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
//            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
////                    创建覆盖物工具类对象
////                PoiOverlay poiOverlay = new PoiOverlay(baiduMap);
//////                    设置搜索结果数据
////                poiOverlay.setData(poiResult);
//////                  添加到地图上
////                poiOverlay.addToMap();
//
//                /***************************自己添加覆盖物时****************************/
//                List<PoiInfo> results = poiResult.getAllPoi();
////                清除所有覆盖物
//                baiduMap.clear();
//                for (PoiInfo info : results) {
//                    Bundle b = new Bundle();
//                    b.putParcelable("poi", info);
//                    OverlayOptions oo = new MarkerOptions()
//                            .position(info.location)
//                            .icon(icon);
//                    baiduMap.addOverlay(oo);//添加覆盖物
//                }
            Log.e("aaa", poiResult.error + "");
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                //创建覆盖物工具类对象
                PoiOverlay poiOverlay = new PoiOverlay(baiduMap);
                //设置搜索结果数据
                poiOverlay.setData(poiResult);
                //添加到地图
                poiOverlay.addToMap();
                List<PoiInfo> result = poiResult.getAllPoi();
                baiduMap.clear();//清除所有覆盖物
                for (PoiInfo info : result) {
                    Bundle b = new Bundle();
                    b.putParcelable("poi", info);
                    OverlayOptions oo = new MarkerOptions()
                            .position(info.location)
                            .icon(icon)
                            .extraInfo(b);
                    //添加覆盖物
                    baiduMap.addOverlay(oo);
                }
            } else {

                Toast.makeText(MainActivity.this, "检索失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    private void poiSearchs() {
        String key = edit.getText().toString();
        Log.e("aaaa", "================key" + key);
//        if (isFirst) {
//            Toast.makeText(this, "未定位成功", Toast.LENGTH_SHORT).show();
//            return;
//        }
        poiSearch.searchNearby(new PoiNearbySearchOption()
                .keyword(key)
                .location(new LatLng(40.055, 116.308))
                .radius(10000));//范围

    }

    private CompoundButton.OnCheckedChangeListener changeLis = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.map_type:
                    if (isChecked) {
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    } else {
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }
                    break;
                case R.id.tranffic:
                    if (isChecked) {
                        baiduMap.setTrafficEnabled(true);
                    } else {
                        baiduMap.setTrafficEnabled(false);
                    }
                    break;
            }
        }
    };


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BDLocation location = intent.getParcelableExtra("bdLocation");
            //创建我的位置数据
            MyLocationData data = new MyLocationData.Builder()
                    .longitude(location.getLongitude())
                    .latitude(location.getLatitude())
                    .accuracy(location.getRadius())//精度范围
                    .build();
            //设置我的位置数据
            baiduMap.setMyLocationData(data);
            if (isFirst) {
                isFirst = false;
                //经纬度+缩放
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15);
                //动画更新新地图状态
                baiduMap.animateMapStatus(update);
            }
        }
    };

    /**
     * 界面暂停交互的时候百度地图也暂停交互
     */
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    /**
     * 恢复地图服务
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 销毁服务
     */
    @Override
    protected void onDestroy() {

        if (poiSearch != null) {
            poiSearch.destroy();
            poiSearch = null;
        }
        unregisterReceiver(receiver);
        if (location != null)
            location.stop();
        mapView.onDestroy();
        super.onDestroy();
    }
}