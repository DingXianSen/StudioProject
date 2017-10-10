package com.example.lesson13_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CursorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPassword;
    private Button btLogin;
    private AutoCompleteTextView autoAccount;

    private DBHelper helper;
    private SQLiteDatabase db;


    private List<String> accunts;

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPassword = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.login);
        autoAccount = (AutoCompleteTextView) findViewById(R.id.auto_account);
//        第一步
//        参数2：数据库版本为第一个版本
        helper = new DBHelper(this, 2);
        helper.addUpdateSql("drop table Muser");
        helper.addUpdateSql( "create Table Muser(_id integer not null primary key autoincrement," +//id integer，不为空，主键，自增长，默认自增
                "account varchar(100) not null unique," +
                "password varchar(20) not null," +
                " nick varchar(100))");
//        第二步
        db = helper.getWritableDatabase();
//        第三步
        loadUsers();
//        第四步
//        使用类似Simple Adapter，数据变为Cursor，要求cursor中必须有名称为”_id“列 int/long
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, loadUsers(), new String[]{"account"}, new int[]{android.R.id.text1});
        autoAccount.setAdapter(adapter);
    }

    //查询所有的数据,在输入提示所有账号时使用
    public Cursor loadUsers() {
//    两种查询方式
        return db.rawQuery("select * from Muser", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                loging();
                break;
        }
    }

    private void loging() {
//        首相获取到账号密码
        String account = autoAccount.getText().toString();
        String pwd = etPassword.getText().toString();
        String sql = "Select count(0) from Muser where account=?";
        Cursor cursor = db.rawQuery(sql, new String[]{account});//返回游标,第二个参数，参数
        if (cursor != null && cursor.moveToFirst() && cursor.getInt(0) > 0) {
//        存在数据
            ContentValues cv = new ContentValues();
            cv.put("password", pwd);
//            按照账号密码执行更新操作
            int num = db.update("Muser", cv, "account=? and password!=?", new String[]{account, pwd});
            if (num > 0) {
                Toast.makeText(this, "更新密码成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "账号密码与原来一致", Toast.LENGTH_SHORT).show();
            }
        } else {
//         不存在，添加到数据库
//            sql="Insert into Muser values(null,?,?,'游客')";
//            第一种
//            db.execSQL(sql,new Object[]{account,pwd});
//            第二种
            ContentValues cv = new ContentValues();
//            key为列名，values为值
            cv.put("account", account);
            cv.put("password", pwd);
//            1：表名 2：可为空列的列名 3：要添加的列-值   返回值：id
            long id = db.insert("Muser", "nick", cv);
            if (id > 0) {
                Toast.makeText(this, "已记录账号密码", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "记录账号密码失败", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
//           重新查询，替换游标
        adapter.swapCursor( loadUsers());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        关闭数据库
        if (db != null) {
            db.close();
        }
    }
}
