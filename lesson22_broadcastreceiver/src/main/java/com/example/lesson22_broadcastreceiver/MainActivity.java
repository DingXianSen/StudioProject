package com.example.lesson22_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        动态广播注册
//        registerReceiver(receiver,new IntentFilter("broadcast"));//这个“broadcast”和发送的是一致的
//        registerReceiver(receiver1,new IntentFilter("broadcast"));//这个“broadcast”和发送的是一致的
//        有序
        IntentFilter filter = new IntentFilter("orderbroadcast");
        filter.setPriority(600);
        registerReceiver(receiver, filter);

        IntentFilter filter1 = new IntentFilter("orderbroadcast");
        filter1.setPriority(400);//优先级
        registerReceiver(receiver1, filter1);

        filter = new IntentFilter("localbroadcast");
        filter.setPriority(600);
        filter1 = new IntentFilter("localbroadcast");
        filter1.setPriority(800);//
        manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(receiver, filter);
        manager.registerReceiver(receiver1, filter1);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.send_broadcast:
                in = new Intent("broadcast");
                in.putExtra("age", "20");
                in.putExtra("name", "污水");
                sendBroadcast(in);
                break;
            case R.id.send_orderboradcast:
                in = new Intent("orderbroadcast");
                in.putExtra("age", "20");
                in.putExtra("name", "污水");
                sendOrderedBroadcast(in, null);//发送有序广播
                break;
            case R.id.send_localboradcast://本地广播
                in = new Intent("localbroadcast");
                in.putExtra("age", "19");
                in.putExtra("name", "污水");
                manager.sendBroadcast(in);
                break;
            case R.id.send_local_sync_boradcast://本地同步广播
                in = new Intent("localsyncbroadcast");
                in.putExtra("age", "20");
                in.putExtra("name", "污水");
                manager.sendBroadcastSync(in);
                break;
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("localbroadcast")) {
                String age = intent.getStringExtra("age");
                String name = intent.getStringExtra("name");
                Log.e("aaaa", "=========receiver=======name========" + name + "age===========" + age);
//                abortBroadcast();//终止广播，只能在有序广播中调用，结果只有一条，低优先级的接收不到
//                setResultCode(10);
//                setResultData("http://www.baidu.com");
//                Bundle bundle=new Bundle();
//                bundle.putString("name","湖南省");
//                bundle.putInt("age",18);
//                setResultExtras(bundle);

            }
        }
    };
    private BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("localbroadcast")) {
                String age = intent.getStringExtra("age");
                String name = intent.getStringExtra("name");
                Log.e("aaaa", "=========receiver1=======name========" + name + "age===========" + age);
//                if (getResultCode()==10){
//                    String data=getResultData();
////                    参数：表示如果获取时不存在Bundle，则true自动创建bundle，否则不创建
//                    Bundle bundle=getResultExtras(true);
//                     name= bundle.getString("name");
//                    int ageInt= bundle.getInt("age");
//                    Log.e("aaaa", "=========data=="+data+"=====name========" + name + "age===========" + ageInt);
//                }
//
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);//在super之前调用，取消注册
        unregisterReceiver(receiver1);//在super之前调用，取消注册
        manager.unregisterReceiver(receiver);
        manager.unregisterReceiver(receiver1);
        super.onDestroy();
    }
}
