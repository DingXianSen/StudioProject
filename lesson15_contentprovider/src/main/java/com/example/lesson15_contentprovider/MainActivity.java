package com.example.lesson15_contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private SimpleCursorAdapter adapter;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        emptyView = (TextView) findViewById(R.id.empty_view);
        adapter = new SimpleCursorAdapter(this, R.layout.layout_item, getImages()
                , new String[]{
                MediaStore.Images.Media.DATA,//数据
                MediaStore.Images.Media.DATE_ADDED,//时间
                MediaStore.Images.Media.DISPLAY_NAME//名称
        }, new int[]{R.id.image, R.id.data, R.id.title});
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.data) {
                    Date date = new Date(cursor.getLong(columnIndex) * 1000);
                    ((TextView) view).setText(sdf.format(date));
                }
                return false;
            }
        });
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);

//        Uri uri = Uri.parse("content://custom/customs");
//        Cursor c = getContentResolver().query(uri, new String[]{"_id", "first_name", "last_name"}, null, null, null);
//        while (c!=null&&c.moveToNext()){
//            Log.e("aaaa","===="+c.getString(1)+c.getString(2));
//        }
        Uri uri = Uri.parse("content://custom/customs");
        Cursor c = getContentResolver().query(uri,
                new String[]{"_id", "first_name", "last_name"}, null, null, null);
        while (c != null && c.moveToNext()) {
            Log.e("aaaa", "====" + c.getString(1) + c.getString(2));
        }
    }

    public Cursor getImages() {
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,//数据
                MediaStore.Images.Media.DATE_ADDED,//时间
                MediaStore.Images.Media.DISPLAY_NAME//名称}
        };
        Uri uri = new MediaStore.Images.Media().EXTERNAL_CONTENT_URI;
        ContentResolver cr = getContentResolver();
        return cr.query(uri, projection, null, null, MediaStore.Images.Media.DATE_ADDED + " desc");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.empty_view:
                break;
        }
    }
}
