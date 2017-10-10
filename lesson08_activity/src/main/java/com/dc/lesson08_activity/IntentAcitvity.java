package com.dc.lesson08_activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 怪蜀黍 on 2016/11/11.
 */

public class IntentAcitvity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_a_by_component:
                //1
                Intent intent=new Intent(this,AActivity.class);
                /**
                 * 第二个参数，组件的名称
                 */
//                intent.setClassName(this,AActivity.class.getName());
//                intent.setClassName(this,"com.dc.lesson08_activity.AActivity");
                //第一个参数：程序的包名
                intent.setClassName("com.dc.lesson08_activity","com.dc.lesson08_activity.AActivity");

//                intent.setClass(this,AActivity.class);
//                ComponentName name=new ComponentName("com.dc.lesson08_activity","com.dc.lesson08_activity.AActivity");
//                intent.setComponent(name);
                //2.
                Intent intent1=new Intent();
                break;
        }
    }
}
