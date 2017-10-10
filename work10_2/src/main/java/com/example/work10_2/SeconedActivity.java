package com.example.work10_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 怪蜀黍 on 2016/11/17.
 */
public class SeconedActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_resu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        et_resu = (EditText) findViewById(R.id.et_result);
        String str=getIntent().getStringExtra("result");
        et_resu.setText(str);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String resu = et_resu.getText().toString();
        intent.putExtra("resu", resu);
        setResult(1,intent);
        finish();
    }
}
