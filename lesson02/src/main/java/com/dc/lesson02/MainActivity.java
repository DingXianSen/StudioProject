package com.dc.lesson02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String Tag="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //修饰符为public 方法返回值为void 必须是View的参数
    public void click(View v){
        Log.e(Tag,"==========click==========");
    }
}
