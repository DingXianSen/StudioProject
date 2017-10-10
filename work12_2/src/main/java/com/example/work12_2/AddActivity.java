package com.example.work12_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/19.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name,birthday,time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name= (TextView) findViewById(R.id.tv_name);
        birthday= (TextView) findViewById(R.id.tv_birthday);
        time= (TextView) findViewById(R.id.tv_time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_name:
//                弹出添加姓名对话框
                break;
            case R.id.tv_birthday:
                break;
            case R.id.tv_time:
                break;
        }
    }
}
