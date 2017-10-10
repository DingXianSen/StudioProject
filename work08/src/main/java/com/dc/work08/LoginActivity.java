package com.dc.work08;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/14.
 */

/**
 * 登陆
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_phone,et_pwd;
    private Button btn_login;
    private TextView tv_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        et_phone = (EditText) findViewById(R.id.etphone);
        et_pwd = (EditText) findViewById(R.id.etpwd);

        btn_login = (Button) findViewById(R.id.btn_login);

        tv_register= (TextView) findViewById(R.id.tv_register);

    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登录
            case R.id.btn_login:
                String phone=et_phone.getText().toString();
                String pwd=et_pwd.getText().toString();
                Intent intent_login=new Intent(LoginActivity.this,MainActivity.class);
                intent_login.putExtra("phone",phone);
                intent_login.putExtra("pwd",pwd);
                startActivity(intent_login);
                break;
            case R.id.tv_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
