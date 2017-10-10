package com.dc.lesson08_h_work;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/14.
 */

public class CActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 动态创建布局
         */
        //表示通过构造方法新建出来的
        TextView tv=new TextView(this);
        setContentView(tv);
        tv.setText("layout_CActivity============");
        tv.setTextSize(30);
        tv.setTextColor(Color.GRAY);
    }
}
