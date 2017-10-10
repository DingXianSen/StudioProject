package com.example.work15;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Uri uri;
    private ListView lv_data;
    private ArrayAdapter<String> adapter;
    private EditText tv_name,tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_data = (ListView) findViewById(R.id.lvdata);
        tv_id= (EditText) findViewById(R.id.id);
        tv_name= (EditText) findViewById(R.id.name);
        uri = Uri.parse("content://custom/customs");
        Log.e("aaaa","==========MainActivity==============onCreate======uri"+uri);
//        注册一个观察者给ContentResolver
        getContentResolver().registerContentObserver(uri,true,observer);
    }

    @Override
    protected void onDestroy() {
//         取消注册
        getContentResolver().unregisterContentObserver(observer);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add://增
                String name=tv_name.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("name",name);
                getContentResolver().insert(uri, cv);
                break;
            case R.id.update://改
                updateDataNameById();
                break;
            case R.id.delete://删
                break;
            case R.id.query://查
                queryData();
                Log.e("aaaa","===============onClick===========");
                break;
        }
    }

    /**
     * 根据id修改name
     */
    private void updateDataNameById(){
        String name=tv_name.getText().toString();
        String id=tv_id.getText().toString();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
//        int c=getContentResolver().update(uri,cv,"_id="+id,null);
        getContentResolver().update(uri,cv,"_id="+id,null);
        Log.e("aaaa","=============updateDataNameById================id"+id+"=====================uri"+uri+"=================cv.name"+cv.get("name"));
    }
    /**
     * 查询数据方法
     */
    private void queryData() {
        Cursor c = getContentResolver().query(uri, new String[]{"name"}, null, null, null);
        Log.e("aaaa","=============="+c.getColumnCount()+"================"+c.getCount());
        String[] data = new String[c.getCount()];
        int i = 0;
        while (c != null && c.moveToNext()) {
            data[i] = c.getString(c.getColumnIndex("name"));
            i++;
        }
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        lv_data.setAdapter(adapter);
    }

    //    数据改变时发生的方法
    private ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queryData();
        }
    };
}
