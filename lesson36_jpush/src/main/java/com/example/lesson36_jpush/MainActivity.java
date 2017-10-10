package com.example.lesson36_jpush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private TextView tv;

//    应用程序使用频率统计

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();//获取MyReceiver的Intent
        String type = intent.getStringExtra("type");
        String json = intent.getStringExtra("extra");
        String content;
        if ("message".equals(type)) {
            content=intent.getStringExtra("content");
            tv.setText("type:"+type+"\njson:"+json+"\ncontent:"+content);
        } else if ("notification".equals(type)) {
            tv.setText("type:"+type+"\njson:"+json);
        } else {
            tv.setText("无数据");
        }
    }
}
