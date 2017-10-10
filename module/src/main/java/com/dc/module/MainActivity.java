package com.dc.module;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView tv_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_five= (TextView) findViewById(R.id.tv5);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,SeconedActivity.class);
        startActivity(intent);
    }
}
