package com.example.lesson15_contentprovider;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/24.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult;
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvResult = (TextView) findViewById(R.id.result);
        uri = Uri.parse("content://custom/customs");
//        注册一个观察者给ContentResolver
        getContentResolver().registerContentObserver(uri,true,observer);
    }

    @Override
    protected void onDestroy() {
//        取消注册的观察者
        getContentResolver().unregisterContentObserver(observer);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ContentValues cv = new ContentValues();
                cv.put("first_name", "你");
                cv.put("last_name", "好");
                cv.put("address", "便宜");
                getContentResolver().insert(uri, cv);
                break;
            case R.id.query:
                queryData();
                break;
        }
    }

    private void queryData() {
        Cursor c = getContentResolver().query(uri, new String[]{"first_name", "last_name", "address"},
                null, null, null);
        StringBuffer sb = new StringBuffer();
        while (c != null && c.moveToNext()) {
            sb.append(c.getString(c.getColumnIndex("first_name")));
            sb.append(c.getString(c.getColumnIndex("last_name")));
            sb.append(c.getString(c.getColumnIndex("address")));
            sb.append("\n");
        }
        tvResult.setText(sb.toString());
    }

    //数据改变时发生的方法
    private ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queryData();
        }
    };
}
