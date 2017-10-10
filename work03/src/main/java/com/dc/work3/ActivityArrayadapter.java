package com.dc.work3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/7.
 */

public class ActivityArrayadapter extends AppCompatActivity {
    private Spinner spinner;
    private AutoCompleteTextView auto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_arrayadapter);

        spinner = (Spinner) findViewById(R.id.spinner);
        auto=(AutoCompleteTextView)findViewById(R.id.auto);
        //调用android的布局
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"aaabbb", "aabb", "abfaa", "abcaa", "aaacccc", "aaaggfff", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa"});
        //使用自己的布局,这个布局中，选项的前边会有图片显示
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_list_item,R.id.textview, new String[]{"aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa", "aaa"});
        spinner.setAdapter(adapter);
        auto.setAdapter(adapter);

        //这些是解决如果下边提示没有时，上边的会没有显示
        spinner.setOnItemSelectedListener(itemSelectedLis);

    }
    private AdapterView.OnItemSelectedListener itemSelectedLis=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        //没有任何一项时触发
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.e("aaa","===================onNothingSelected===================");
        }
    };
}
