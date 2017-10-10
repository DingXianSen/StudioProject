package com.dc.lesson07_works;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 *
 *         2016年11月10日 下午5:10:09
 */
public class SeconedActivity extends Activity {
    EditText edit;
    String source;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        initView();

    }

    private void initView() {
        source = getIntent().getStringExtra("source");
        edit = (EditText) findViewById(R.id.edit);
        btn = (Button) findViewById(R.id.btn);
        //获取启动此界面的意图
        Intent intent = getIntent();
        //获取启动此界面时存放的数据
        switch (source) {
            case "name":
                String naems=intent.getStringExtra("tv_name");
                edit.setText(naems);
                Toast.makeText(this, "==========="+naems, Toast.LENGTH_SHORT).show();
                edit.setHint("请输入用户名");
                break;
            case "phone":
                String phones=intent.getStringExtra("tv_phone");
                edit.setText(phones);
                Toast.makeText(this, "==========="+phones, Toast.LENGTH_SHORT).show();
                edit.setHint("请输入电话");
                break;
            case "email":
                String emails=intent.getStringExtra("tv_email");
                edit.setText(emails);
                Toast.makeText(this, "==========="+emails, Toast.LENGTH_SHORT).show();
                edit.setHint("请输入邮箱");
                break;
        }

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content", edit.getText().toString());
                switch (source) {
                    case "name":
                        setResult(1, intent);
                        break;
                    case "phone":
                        setResult(2, intent);
                        break;
                    case "email":
                        setResult(3, intent);
                        break;
                }

                finish();
            }
        });
    }
}
