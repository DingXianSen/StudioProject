package com.dc.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2016/11/5.
 */

public class Main2Activity extends AppCompatActivity {

    Activity ActivityA;

    //定义一个RadioGroup,用rg来接受选中的单选框
    private RadioGroup rg;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    //点击事件
    private Button buttonnext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2_w1);
        //////////////////////////////////////////////////////////////
        ActivityA = this;


        rg = (RadioGroup) findViewById(R.id.rg3);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);

        buttonnext = (Button) findViewById(R.id.buttonnext);
        //radiobutton的点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        Toast.makeText(Main2Activity.this, "哎呦，不错哦！", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb2:
                        Toast.makeText(Main2Activity.this, "你数学是体育老师教的吗？", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb3:
                        Toast.makeText(Main2Activity.this, "你数学是语文老师教的吗？", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb4:
                        Toast.makeText(Main2Activity.this, "你确实是傻！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        //下一题的点击事件
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要进入的另一个界面

                Intent intent = new Intent(Main2Activity.this, Main2Activity2.class);
                startActivity(intent);


            }
        });
    }
}
