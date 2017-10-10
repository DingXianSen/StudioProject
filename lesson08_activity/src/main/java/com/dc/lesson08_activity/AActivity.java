package com.dc.lesson08_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info= (TextView) findViewById(R.id.info);
        info.setText("所在任务栈ID:"+getTaskId()+"\n实例信息:"+this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_a:
                Intent intent=new Intent(this,AActivity.class);
                startActivity(intent);
                break;
            case  R.id.to_b:
                intent=new Intent(this,BActivity.class);
                startActivity(intent);
                break;
        }
    }
}
