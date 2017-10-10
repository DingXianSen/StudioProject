package com.dc.work3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dc.work3.R;

import org.w3c.dom.Text;

/**
 * Created by 怪蜀黍 on 2016/11/3.
 */

public class MainActivity2 extends AppCompatActivity {
    //定义拿到的选中的信息展示Text View
    private TextView textviewinfo;
    //多选框
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;

    //全选框
    private CheckBox checkboxall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        textviewinfo = (TextView) findViewById(R.id.textviewinfo);

        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkbox6);



        //首先实现点击时，把复选框的值拿出，并绑定到TextvView
        checkBox1.setOnCheckedChangeListener(changeListener);

        //实现全选
        checkboxall = (CheckBox) findViewById(R.id.checkboxall);
        checkboxall.setOnCheckedChangeListener(changeListenerall);
        if(checkboxall.isChecked()){
            checkboxall.setChecked(false);
        }else{
            checkboxall.setChecked(false);
        }
    }

    /*===========================================================================================*/
    //全选
    public CompoundButton.OnCheckedChangeListener changeListenerall = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (checkboxall.isChecked()) {
                //如果全选，那么选中所有的 
                Toast.makeText(MainActivity2.this, "全选", Toast.LENGTH_SHORT).show();
                checkBox1.setChecked(true);
                checkBox2.setChecked(true);
                checkBox3.setChecked(true);
                checkBox4.setChecked(true);
                checkBox5.setChecked(true);
                checkBox6.setChecked(true);
//                & checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked() && checkBox6.isChecked()
//                if (checkboxall.isChecked() & checkBox1.isChecked()) {
//
//                }else{
//                    Toast.makeText(MainActivity2.this, "进入了else", Toast.LENGTH_SHORT).show();
//                    checkboxall.setChecked(false);
//                }
            } else {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                Toast.makeText(MainActivity2.this, "取消全选", Toast.LENGTH_SHORT).show();
            }
        }

    };
    /*===========================================================================================*/
    //多选框选择
    public CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // Toast.makeText(MainActivity2.this, "已经选中", Toast.LENGTH_SHORT).show();
                String info = buttonView.getText().toString();
                textviewinfo.setText("选中的信息：" + info);
                String id = buttonView.toString();
                Log.i("id", "===================================================" + id + info);
            } else {
                //Toast.makeText(MainActivity2.this, "出现错误", Toast.LENGTH_SHORT).show();
                textviewinfo.setText("选中的信息：");

                //checkboxall.setChecked(false);
            }

            if(checkboxall.isChecked()&&!checkBox1.isChecked()){
                Toast.makeText(MainActivity2.this, "全选，但是2没选中", Toast.LENGTH_SHORT).show();
                CheckBox all=(CheckBox)findViewById(R.id.checkboxall);
                all.setChecked(false);
            }
        }
    };
}