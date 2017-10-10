package com.example.work13;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_add, et_delete;
    private ListView lv_data;
    private DBHelper helper;
    private SQLiteDatabase db;
    private List<String> accunts;
    private ArrayAdapter<String> adapter;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_add = (EditText) findViewById(R.id.etAdd);
        et_delete = (EditText) findViewById(R.id.etDelete);
        lv_data = (ListView) findViewById(R.id.lvData);
//        第一步
        helper = new DBHelper(this, 1);
//        第二步
        db = helper.getWritableDatabase();

//        查询所有数据方法，绑定到List View
//        getAllData();
        loadUsers();
        helper.addUpdateSql("drop table TbWork");
        helper.addUpdateSql("create Table TbWork(_id integer not null primary key autoincrement," +//id integer，不为空，主键，自增长，默认自增
                "data varchar(100) not null unique)");
//        第二步
        db = helper.getWritableDatabase();
//        第三步
//        getAllData();
//        第四步
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accunts);
        lv_data.setAdapter(adapter);
    }

    //查询所有的数据,在输入提示所有账号时使用
    public void loadUsers() {
//    两种查询方式
        Cursor cursor = db.rawQuery("select * from TbWork", null);
//    参数一：查询的表  参数二：查询的列
//       Cursor cursor= db.query("Muser", new String[]{"_id", "account", "password", "nick"}, null, null, null, null, null);
//         password=? and nick like ?   ， new String[]{占位符的值} ， 其他条件的添加
        while (cursor != null && cursor.moveToNext()) {
//            第二种 =============
            if (accunts == null) {//如果为空新建，否则清空
                accunts = new ArrayList<>();
            } else {
                accunts.clear();
            }
//            第一种使用map
//            Map<String, Object> map = new HashMap<>();
//            按照列名获取下标cursor.getInt(cursor.getColumnIndex("_id")),然后把拿到的数据存到map集合中
//            map.put("id", cursor.getInt(cursor.getColumnIndex("_id")));
//            map.put("account",cursor.getString(cursor.getColumnIndex("account")));
//            map.put("password",cursor.getString(cursor.getColumnIndex("password")));
//            第二种使用List集合
            accunts.add(cursor.getString(cursor.getColumnIndex("data")));
        }
//        关闭游标
        cursor.close();
    }

    /**
     * 查询所有的数据方法
     */
//    private void getAllData() {
//        //    两种查询方式
//        Cursor cursor = db.rawQuery("select * from TbWork", null);
////    参数一：查询的表  参数二：查询的列
////       Cursor cursor= db.query("Muser", new String[]{"_id", "account", "password", "nick"}, null, null, null, null, null);
////         password=? and nick like ?   ， new String[]{占位符的值} ， 其他条件的添加
//        while (cursor != null && cursor.moveToNext()) {
////            第二种 =============
//            if (accunts == null) {//如果为空新建，否则清空
//                accunts = new ArrayList<>();
//            } else {
//                accunts.clear();
//            }
////            第一种使用map
////            Map<String, Object> map = new HashMap<>();
////            按照列名获取下标cursor.getInt(cursor.getColumnIndex("_id")),然后把拿到的数据存到map集合中
////            map.put("id", cursor.getInt(cursor.getColumnIndex("_id")));
////            map.put("account",cursor.getString(cursor.getColumnIndex("account")));
////            map.put("password",cursor.getString(cursor.getColumnIndex("password")));
////            第二种使用List集合
//            accunts.add(cursor.getString(cursor.getColumnIndex("data")));
//        }
////        关闭游标
//        cursor.close();
//    }

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

    /**
     * 删除方法
     */
    private void deleteData() {
    }

    /**
     * 添加数据方法
     */
    private void addData() {
        String adddata = et_add.getText().toString();
//        首先执行查询，看看数据库中有没有要插入的数据，防止重复
//        Cursor cusor= db.query("TbWork",new String[]{"data"},"data=?",new String[]{adddata},null,null,null);
        String sql = "Select * from TbWork where data=?";
        Cursor cursor = db.rawQuery(sql, new String[]{adddata});
        if (cursor != null) {//表示该数据已经存在
            Toast.makeText(this, "该数据已经存在", Toast.LENGTH_SHORT).show();
        } else {
            //添加到数据库
            ContentValues cv = new ContentValues();
            cv.put("data", adddata);
            long id = db.insert("TbWork", "data", cv);
            if (id > 0) {
//                如果id大于0表示添加成功
                Toast.makeText(this, "数据存储成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "数据存储失败", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
//        重新调用查询全部方法
        loadUsers();
//        清除适配器数据
        adapter.clear();
//        添加全部数据
        adapter.addAll(accunts);
    }
}
