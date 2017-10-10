package com.example.work16;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 怪蜀黍 on 2016/11/28.
 */
public class SeconedActivity extends AppCompatActivity{
    private ListView listView;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        listView= (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,new Object[]{"aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb","aaaa","aaaaa","bbbbb"});
        listView.setAdapter(adapter);
    }
}
