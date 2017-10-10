package com.example.work12_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * 主界面，点击加号时跳转添加页面
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
//                跳转到添加页面
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                break;
        }
    }
}
