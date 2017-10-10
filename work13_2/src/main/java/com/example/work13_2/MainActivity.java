package com.example.work13_2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_add, et_delete;
    private ListView lv_data;
    private DBHelper helper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        init();
        query();
    }

    private void bind() {
        helper = new DBHelper(this, 1);
        lv_data = (ListView) findViewById(R.id.lvData);
    }

    private void init() {
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_text, null, new String[]{"textinfo"}, new int[]{R.id.item_tv_text});
        lv_data.setAdapter(cursorAdapter);
    }

    private void query() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from mydata", null);
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
//                添加
                addData();
                break;
            case R.id.btDelete:
//                删除
                deleteData();
                break;
        }
    }

    private void deleteData() {
    }

    private void addData() { String adddata = et_add.getText().toString();
//        首先执行查询，看看数据库中有没有要插入的数据，防止重复
//        Cursor cusor= db.query("TbWork",new String[]{"data"},"data=?",new String[]{adddata},null,null,null);
        String sql = "Select * from mydata where data=?";
        Cursor cursor = db.rawQuery(sql, new String[]{adddata});
        if (cursor != null) {//表示该数据已经存在
            Toast.makeText(this, "该数据已经存在", Toast.LENGTH_SHORT).show();
        } else {
            //添加到数据库
            ContentValues cv = new ContentValues();
            cv.put("data", adddata);
            long id = db.insert("mydata", "data", cv);
            if (id > 0) {
//                如果id大于0表示添加成功
                Toast.makeText(this, "数据存储成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "数据存储失败", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
//        重新调用查询全部方法
        query();
//        清除适配器数;
//        添加全部数据
//        cursorAdapter.addAll(accunts);
    }
}
