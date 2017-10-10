package com.dc.work09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/15.
 */

public class BActivity extends Activity implements View.OnClickListener{
    private TextView tv_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        tv_update= (TextView) findViewById(R.id.tv_update);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text=data.getStringExtra("strong");
        tv_update.setText(text);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(BActivity.this,CActivity.class);
        startActivityForResult(intent,1);
    }
}
