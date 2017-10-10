package com.dc.lesson08_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class BActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        info= (TextView) findViewById(R.id.info);
        info.setText("所在任务栈ID:"+getTaskId()+"\n实例信息:"+this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_b:
                Intent intent=new Intent(this,BActivity.class);
                startActivity(intent);
                break;
            case  R.id.to_a:
                intent=new Intent(this,BActivity.class);
                startActivity(intent);
                break;
        }
    }
}
