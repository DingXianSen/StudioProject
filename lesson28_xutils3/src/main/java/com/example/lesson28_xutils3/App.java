package com.example.lesson28_xutils3;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 怪蜀黍 on 2016/12/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        初始化XUtils
        x.Ext.init(this);
//       设置为调试模式，发布应用时应该将其改为false
        x.Ext.setDebug(true);
    }
}
