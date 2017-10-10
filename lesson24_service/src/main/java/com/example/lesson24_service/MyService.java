package com.example.lesson24_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 怪蜀黍 on 2016/12/14.
 */

public class MyService extends Service {
    //    生命周期
    @Override//初始化
    public void onCreate() {
        super.onCreate();
        Log.e("aaaa", "============onCreate===========");
    }

    @Override//参数intent为启动service的intent
    public int onStartCommand(final Intent intent, int flags, final int startId) {
        Log.e("aaaa", "==========onStartCommand=====startId" + startId + "=======flags" + flags);
        int aciton = intent.getIntExtra("action", 0);
        switch (aciton) {
            case 1:
//                虽然是后台运行，但是在请求数据时也应该开一个新的线程
                new Thread(intent.getStringExtra("name") + startId) {//intent.getStringExtra("name")+startId作为线程的名称
                    int i = 0;

                    @Override
                    public void run() {
                        Intent intent1 = new Intent("jishi");
                        while (true) {
                            i++;
                            final int id = startId;
                            intent1.putExtra("i", i);
                            intent1.putExtra("witch", id);
                            sendBroadcast(intent1);
                            Log.e("aaaa", "==========" + getName() + i);
                            if (i == 100) {
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("aaaa", "============onBind===========");
        return new MyBinder();
    }

    @Override//返回值，unbind是否成功，intent和onBind中的intent是同一个，影响到和startService一起使用的时候
    public boolean onUnbind(Intent intent) {
        Log.e("aaaa", "============onUnbind===========");
        return super.onUnbind(intent);
    }

    @Override//只有onUnbind返回true时，这个方法才会执行，并且是解绑之后
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("aaaa", "============onDestroy===========");
    }

    public int cal(int a,int b){
        return a*b;
    }
    //    使用bind时使用
    class MyBinder extends Binder {
        public int cal(int a, int b) {
            return a + b;
        }
//        创建一个方法返回MyService对象
        public MyService getServiceInstance(){
            return MyService.this;
        }
    }
}
