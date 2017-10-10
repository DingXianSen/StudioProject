package com.example.lesson23_notfication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NotificationManager manager;
    private CheckBox fullscreen, cantClean, autoClean, bigPicture;
    private Button progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullscreen = (CheckBox) findViewById(R.id.fullscreen);
        cantClean = (CheckBox) findViewById(R.id.cant_clean);
        autoClean = (CheckBox) findViewById(R.id.auto_clean);
        bigPicture = (CheckBox) findViewById(R.id.big_picture);
        progress = (Button) findViewById(R.id.progress);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        registerReceiver(receiver, new IntentFilter("broadcast"));
    }

    //接收通知
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "响应通知的点击", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification:
                Notification.Builder builder = getNotification();
                PendingIntent pi = PendingIntent.getBroadcast(this, 10, new Intent("broadcast"), PendingIntent.FLAG_UPDATE_CURRENT);
//                Intent in = new Intent(this, MainActivity.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                PendingIntent pi = PendingIntent.getActivity(this, 10, in, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);


//                悬挂式通知

                if (fullscreen.isChecked() && Build.VERSION.SDK_INT > 20) {//表示版本大于5.0
//                    pi = PendingIntent.getActivity(this, 10, in, PendingIntent.FLAG_UPDATE_CURRENT);
                    pi = PendingIntent.getBroadcast(this, 10, new Intent("broadcast"), PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setFullScreenIntent(pi, true);//参数2：是否高优先级
                }
//              大图片通知
                Bitmap big = BitmapFactory.decodeResource(getResources(), R.drawable.aaaa);
                if (bigPicture.isChecked() && Build.VERSION.SDK_INT > 20) {
                    builder.setStyle(new Notification.BigPictureStyle().bigPicture(big));
                } else {//低版本
//                    使用 V4或者V7的通知构建器
                    new NotificationCompat.Builder(this)
                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(big));
                }

                Notification notification = null;
                if (Build.VERSION.SDK_INT > 15) {
//                    或者Build.VERSION.SDK_INT>Build.VERSION_CODES.ICE_CREAM_SANDWICH_MRI
                    notification = builder.build();
                } else {
                    notification = builder.getNotification();
                }
                int flags = 0;
                if (autoClean.isChecked() && cantClean.isChecked()) {
                    flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_NO_CLEAR;
                } else if (autoClean.isChecked()) {
                    flags = Notification.FLAG_AUTO_CANCEL;
                } else if (cantClean.isChecked()) {
                    flags = Notification.FLAG_NO_CLEAR;
                }
                if (flags != 0) {
                    notification.flags = flags;
                }
                manager.notify(0, notification);//注意同一个id只能显示一条
                break;
            case R.id.progress:
                if (progressBuilder==null){
                    progressBuilder=getProgressBuilder();
                    v.post(r);
                }
                break;
        }
    }
    private int progresss;
    private Runnable r=new Runnable() {
        @Override
        public void run() {
            if (progressBuilder!=null){
//                参数1：最大值，参数2：进度值，参数3：是否是大略值
                progressBuilder.setProgress(100,progresss,false);
                Notification notification=progressBuilder.build();
                manager.notify(-1,notification);
                progresss++;
                if (progresss<=100){
                    autoClean.postDelayed(this,50);
                }else{
//                    取消通知显示
                    manager.cancel(-1);
                    progressBuilder=null;
                    progresss=0;
                }
            }
        }
    };

    private NotificationCompat.Builder progressBuilder;
    public NotificationCompat.Builder getProgressBuilder(){
        Bitmap big = BitmapFactory.decodeResource(getResources(), R.drawable.aaaa);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setLargeIcon(big)
                .setContentTitle("正在下载...")
                .setSmallIcon(R.mipmap.ic_launcher);
        return builder;
    }

    public Notification.Builder getNotification() {
        File sound = new File(Environment.getExternalStorageDirectory(), "Download/b.mp3");//必须是手机中有的
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("你这是要搞事情啊")
                .setLargeIcon(bmp)
                .setContentText("进击全明星MVP。。。。。。")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);//呼吸灯和震动都是默认的
                .setDefaults(Notification.DEFAULT_ALL);
//        改变声音
//                .setSound(Uri.fromFile(sound))
//                呼吸灯
//                .setLights(Color.GREEN, 1000, 1000)//绿色，亮1s,熄灭1s
//                .setVibrate(new long[] {500,1000,500,1000});//震动时间，奇数位为震动时间，偶数位为停止时间
        return builder;
    }
}
