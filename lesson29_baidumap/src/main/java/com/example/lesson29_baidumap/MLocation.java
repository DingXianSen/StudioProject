package com.example.lesson29_baidumap;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by 怪蜀黍 on 2016/12/22.
 */

public class MLocation {
    private LocationClient client;
    private Context context;
    public MLocation(Context context){
        this.context=context;
        client=new LocationClient(context);
//        注册一个监听
        client.registerLocationListener(bdLocationLis);
        initOptions(client);
        client.start();
//        请求位置
//        client.requestLocation();
    }

    public  void stop(){
        if (client!=null){
//            取消注册监听
            client.unRegisterLocationListener(bdLocationLis);
//           停止服务
            client.stop();
        }
    }

    private void initOptions(LocationClient client) {
//        设置定位设置
        LocationClientOption option=new LocationClientOption();
//        设置定位精度
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        设置坐标模式
        option.setCoorType("bd09ll");
//        使用GPS
        option.setOpenGps(true);
//        扫描间隔
        option.setScanSpan(5000);
//        不杀死进程
        option.setIgnoreKillProcess(true);
        option.setIsNeedAddress(false);
        option.setIsNeedLocationPoiList(false);
        option.setEnableSimulateGps(true);
        option.setNeedDeviceDirect(false);
        option.setLocationNotify(false);


//        设置给客户端
        client.setLocOption(option);
    }


    private BDLocationListener bdLocationLis=new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int type=bdLocation.getLocType();
            if (type==61||type==161||type==65||type==66){
                Log.e("aaaa",bdLocation.getLatitude()+"纬度==========="+bdLocation.getLongitude()+"经度============"+bdLocation.getAddress()+"地址");
                Intent in=new Intent("location");
                in.putExtra("bdLocation",bdLocation);
                context.sendBroadcast(in);
            }else{
                Log.e("aaaa","定位失败"+type);
            }
        }
    };
}
