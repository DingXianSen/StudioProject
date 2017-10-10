package com.dc.work3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 怪蜀黍 on 2016/11/4.
 */

/**
 * ListView的使用，ListView用来显示大数据 ，注意，数组中的数据无法直接传递给ListView需要借助适配器来完成
 */
public class Activity_listview extends AppCompatActivity {
    //定义一个数组，用来存储List VIew显示的数据
    private String[] data={"aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa","aaa",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listview);

        //适配器
        /**
         * 注意：其中android.R.layout.simple_list_item.1是作为ListView子项布局的id，这是一个Android内置的布局文件，里面只有一个TextView用于简单的显示一段
         * 文本，
         */
        /*对应的参数，第一个参数当前类，第二个参数*/
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_listview.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}