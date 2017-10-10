package com.dc.work08;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 怪蜀黍 on 2016/11/14.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText re_phone,re_pwd;
    private Button btn_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        re_phone= (EditText) findViewById(R.id.etre_phone);
        re_pwd= (EditText) findViewById(R.id.etre_pwd);

        btn_register= (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View v) {
        String phone=re_phone.getText().toString();
        String pwd=re_pwd.getText().toString();
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        intent.putExtra("phone",phone);
        intent.putExtra("pwd",pwd);
        startActivity(intent);
    }
}
