package com.dc.work;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2016/11/5.
 */

public class Main2Activity2 extends AppCompatActivity {
    static Activity b;
    //定义RadioGroup
    private RadioGroup rg2;
    //返回上一页
    private EditText et;
    //退出程序
    private Button buttonexit;


    private RadioButton rb5;
    private RadioButton rb6;
    private RadioButton rb7;
    private RadioButton rb8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_w2);
        b = this;
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        et = (EditText) findViewById(R.id.et);
        buttonexit = (Button) findViewById(R.id.btexit);

        rb5 = (RadioButton) findViewById(R.id.rb5);
        rb6 = (RadioButton) findViewById(R.id.rb6);
        rb7 = (RadioButton) findViewById(R.id.rb7);
        rb8 = (RadioButton) findViewById(R.id.rb8);

        //单选的选中事件
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb5:
                        Toast.makeText(Main2Activity2.this, "选错了，是不是傻！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb6:
                        Toast.makeText(Main2Activity2.this, "选错了，是不是傻！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb7:
                        Toast.makeText(Main2Activity2.this, "选错了，是不是傻！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb8:
                        Toast.makeText(Main2Activity2.this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et.getText().toString();
                if (text.equals("exit")) {
                    //这里就不可以用finish方法了，要不会直接退出，只能是打开另一个页面的方式进入第一个页面
//                    Intent intent=new Intent(Main2Activity2.this,Main2Activity.class);
//                    startActivity(intent);
                    //Main2Activity2.this.finish();
                    b.finish();
                    et.setText("");
                }
            }
        });
        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Main2Activity2.this.finish();
                //Main2Activity2.this.finish();
                //实例化A
                //Main2Activity a = new Main2Activity();
               // a.ActivityA.finish();
                //System.exit(0);


                // Dalvik VM的本地方法
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(0);



//                ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
//
//                am.restartPackage(getPackageName());
            }
        });
    }
}
