package com.example.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sp;
    private ImageView image;
    int daojishi = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
//        首先判断SharedPreference中是否有数据
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        if (!sp.getAll().isEmpty()) {
            Toast.makeText(this, "sp不等于空" + sp.getAll(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "sp为空,3秒后跳转到InfoActivity界面", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final int[] jishi = {0};
                            for (final int[] i = {3}; i[0] >= 1; i[0]--) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                jishi[0] = i[0];
                                daojishi = jishi[0];
                                Toast.makeText(MainActivity.this, "倒计时：" + jishi[0], Toast.LENGTH_SHORT).show();
                                if (jishi[0] == 1) {
                                    Toast.makeText(MainActivity.this, "跳转", Toast.LENGTH_SHORT).show();
                                    //跳转方法
                                    IntentActivity();
                                }
                            }
                        }
                    });
                }
            }).start();
        }
    }

    private void IntentActivity() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivityForResult(intent, 1);

    }
}
