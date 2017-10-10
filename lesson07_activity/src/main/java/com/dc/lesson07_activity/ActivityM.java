package com.dc.lesson07_activity;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/10.
 */

public class ActivityM {
    private static List<Activity> activityList=new LinkedList<>();

    /**
     * 退出程序的方法
     */
    public static void exit(){
        for(Activity activity:activityList){
            activity.finish();//将所有的Activity关闭
        }
        activityList.clear();//将集合清空
    }

    /**
     * 添加Activity方法，没打开一个Activity保存一个
     * @param activity
     */
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    /**
     * 移除一个Activity
     * @param activity
     */
    public static  void removeActivity(Activity activity){
        activityList.remove(activity);
    }
}
