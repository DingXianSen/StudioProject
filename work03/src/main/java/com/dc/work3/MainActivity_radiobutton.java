package com.dc.work3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by 怪蜀黍 on 2016/11/4.
 */

public class MainActivity_radiobutton extends AppCompatActivity {
    private RadioGroup rg;

    private Spinner spinner;

    //重写一个参数的处事方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_radiobutton);

        rg= (RadioGroup) findViewById(R.id.rg1);
        rg.setOnCheckedChangeListener(changeLis);
        //如果没有任何选中项，返回-1
      //  rg.getCheckedRadioButtonId();
        //选中指定项
       // rg.check(R.id.rb1);
        //清除选中项
       // rg.clearCheck();


        ImageView img=(ImageView) findViewById(R.id.iv);
//        设置图片
        img.setImageResource(R.mipmap.ic_launcher);
        //设置缩放类型
        img.setScaleType(ImageView.ScaleType.CENTER);

        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }
    private AdapterView.OnItemSelectedListener itemSelectedListener =new AdapterView.OnItemSelectedListener() {
        @Override//当项选中时  参数1：触发事件的控件 2：选中的视图 3：选中视图的id
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }
        @Override//当没有项选中时
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private RadioGroup.OnCheckedChangeListener changeLis=new RadioGroup.OnCheckedChangeListener() {
        @Override //参数2：选中者的id
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //获取选中项的数据
            String str=spinner.getSelectedItem().toString();
            //获取选中项的下标
            int position=spinner.getSelectedItemPosition();
            //获取选中项的id
            long id= spinner.getSelectedItemId();
            Log.e("aaaa",str+"========="+position+"========="+id);
        }
    };
}
