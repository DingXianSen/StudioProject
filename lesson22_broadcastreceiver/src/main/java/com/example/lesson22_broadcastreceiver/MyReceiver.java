package com.example.lesson22_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by 怪蜀黍 on 2016/12/12.
 */

public class MyReceiver extends BroadcastReceiver {
    /**
     *
     * @param context
     * @param intent  接收到的广播
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();//
        if ("android.intent.action.BOOT_COMPLETED".equals(action)){//如果是开机广播
            Intent in=new Intent(context,MainActivity.class);
//            定义在新的栈中启动activity
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//添加额外的附加标志
            context.startActivity(in);
        }else if ("android.intent.action.DATE_CHANGED".equals(action)){//日期变化
            Calendar calendar=Calendar.getInstance();
            String str= DateFormat.format("yyyy-MM-dd",calendar).toString();
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
        else if ("android.intent.action.BATTERY_CHANGED".equals(action)){
            Log.e("aaaa","=============="+intent.getExtras().toString());
        }
    }
}
