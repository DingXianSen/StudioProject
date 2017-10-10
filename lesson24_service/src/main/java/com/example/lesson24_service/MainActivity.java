package com.example.lesson24_service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnClickListener {
private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll= (LinearLayout) findViewById(R.id.container);
        registerReceiver(receiver,new IntentFilter("jishi"));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent in = null;
        switch (v.getId()) {
            case R.id.start_service:
                in = new Intent(this, MyService.class);
                in.putExtra("name","======卡牌大师=====");
                in.putExtra("action",1);
                startService(in);
                break;
            case R.id.stop_service:
//                只要制定的是MyService,可以在任何一个界面停止
                in = new Intent(this, MyService.class);
                stopService(in);
                break;
            case R.id.bind_service:
                in = new Intent(this, MyService.class);
                bindService(in,conn,BIND_AUTO_CREATE );//第三个参数，自动创建，标识
                break;
            case R.id.unbind_service:
                //如果想在onDestroy()中调用，则在super之前调用
                unbindService(conn);//解除绑定
                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override//参数1：目标组件名称
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("aaaa","========onServiceConnected=======");
            MyService.MyBinder myBinder= (MyService.MyBinder) service;
            int num=myBinder.cal(30,90);
            Log.e("aaaa","========onServiceConnected==========MyService--MyBinder--cal--"+num);
            MyService myService=myBinder.getServiceInstance();
            int num2=myService.cal(20,50);
            Log.e("aaaa","========onServiceConnected==========MyService--cal--"+num2);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Map<Integer,TextView> map;
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Integer witch=intent.getIntExtra("witch",-1);
            if (map == null) {
                map=new HashMap<>();
            }
            if (map.containsKey(witch)){
                TextView tv=map.get(witch);
                tv.setText(""+intent.getIntExtra("i",0));
            }else{
                TextView tv=new TextView(MainActivity.this);
                tv.setPadding(20,20,20,20);
                tv.setText(""+intent.getIntExtra("i",0));
                map.put(witch,tv);
                ll.addView(tv);
            }
        }
    };
}
