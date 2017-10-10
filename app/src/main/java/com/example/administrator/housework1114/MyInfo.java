package com.example.administrator.housework1114;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/14.
 */

public class MyInfo extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        textView = (TextView) findViewById(R.id.tv1);
    }
    public void onClick(View view){
        Intent in = new Intent(this,updatepwd.class);
        startActivityForResult(in,1);
    }
    public void houTui(View view){
        finish();
    }
    public void tuku(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1&&data!=null){
            String qd =data.getStringExtra("qd");
            textView.setText(qd);
        }
    }
}
