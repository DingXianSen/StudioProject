package com.dc.layoutwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = (Switch) findViewById(R.id.sw);
        //如果控件是保存过的
        if(savedInstanceState!=null){//将recreate前保存的值重新赋值给控件
            sw.setChecked(savedInstanceState.getBoolean("swith"));
        }
        sw.setOnCheckedChangeListener(changeListener);
    }
    private CompoundButton.OnCheckedChangeListener changeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                //如果开启
                setTheme(R.style.AppTheme_Night);
            } else {
                //关闭
                setTheme(R.style.AppTheme);
            }
            setContentView(R.layout.activity_main);
            sw = (Switch) findViewById(R.id.sw);
            //注意一定要把监听放在46的后边，不然会出错
            sw.setChecked(isChecked);
            sw.setOnCheckedChangeListener(changeListener);
        }
    };
    /**
     * 保存控件状态
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存复选框的选中状态
        outState.putBoolean("swith", sw.isChecked());
        super.onSaveInstanceState(outState);
    }
}