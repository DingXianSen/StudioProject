package com.dc.work08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.go_login);
        tv = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();//获取数据
        String phone = intent.getStringExtra("phone");
        String pwd = intent.getStringExtra("pwd");
        if (intent != null) {
            if (phone != null) {
                String result = "欢迎" + phone + "登录，你的密码是：" + pwd;
                tv.setText(result);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
