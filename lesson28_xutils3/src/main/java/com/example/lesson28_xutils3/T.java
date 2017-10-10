package com.example.lesson28_xutils3;

import android.support.annotation.StringRes;
import android.widget.Toast;

import org.xutils.x;

/**
 * Created by 怪蜀黍 on 2016/12/21.
 */

/**
 * Toast工具类
 */
public class T {
    public static void show(String text) {
//        x.app()获取Application
        Toast.makeText(x.app(), text, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int resId) {
        Toast.makeText(x.app(), resId, Toast.LENGTH_SHORT).show();
    }
}
