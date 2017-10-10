package com.example.lesson36_jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by 怪蜀黍 on 2017/1/4.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override//在onReceive中最好不要有超过10秒的操作
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(action)) {//JPushInterface.ACTION_REGISTRATION_ID获取ID
//            去看帮助文档
//            JPushInterface.setTags(context,new HashSet<String>(), new TagAliasCallback() {
//                @Override
//                public void gotResult(int i, String s, Set<String> set) {
//
//                }
//            });
            Log.e("aaaa", "==============id=================" + bundle.getString(JPushInterface.ACTION_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(action)) {//通知打开
//            对被打开的通知进行统计
            JPushInterface.reportNotificationOpened(context,bundle.getString(JPushInterface.EXTRA_MSG_ID));//上报通知被打开了



            String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent in=new Intent(context,MainActivity.class);
            in.putExtra("extra",json);
            in.putExtra("type","notification");
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(in);
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)){
            String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent in=new Intent(context,MainActivity.class);
            in.putExtra("extra",json);
            in.putExtra("type","message");
            in.putExtra("content",bundle.getString(JPushInterface.EXTRA_MESSAGE));//取出消息的内容
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(in);
        }
    }
}
