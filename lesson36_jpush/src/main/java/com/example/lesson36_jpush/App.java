package com.example.lesson36_jpush;

import android.app.Application;
import android.app.Notification;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 怪蜀黍 on 2017/1/3.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        初始化极光推送
        JPushInterface.init(getApplicationContext());
//        开启调试
        JPushInterface.setDebugMode(true);

//       修改通知样式
//        默认通知
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.notificationDefaults = Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE;
        JPushInterface.setDefaultPushNotificationBuilder(builder);


        builder = new BasicPushNotificationBuilder(this);
        builder.notificationFlags = Notification.FLAG_NO_CLEAR | Notification.FLAG_AUTO_CANCEL;//不可清除，但是点击之后可以自动清除
        JPushInterface.setPushNotificationBuilder(1, builder);

        CustomPushNotificationBuilder b = new CustomPushNotificationBuilder(this,
                R.layout.layout_notification_2, R.id.icon, R.id.title, R.id.content);
        JPushInterface.setPushNotificationBuilder(2, b);
    }
}
