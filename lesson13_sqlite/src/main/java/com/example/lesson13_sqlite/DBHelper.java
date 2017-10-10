package com.example.lesson13_sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/22.
 */

public class DBHelper extends SQLiteOpenHelper {
    //    有没有.db都可以
    public static String DB_NAME = "my.db";

    //    public String updateSql;
    public List<String> updateSqlList;

    //SQLiteDatabase.CursorFactory factory,用来创建游标的，第三个参数,null默认的游标工厂
//    version 数据库版本
    public DBHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }
//动态单条sql语句
//    public void setUpdateSql(String sql) {
//        this.updateSql=updateSql;
//    }

    public void addUpdateSql(String sql) {
        if (updateSqlList == null) {
            updateSqlList = new ArrayList<>();
        }
        this.updateSqlList.add(sql);
    }

    //    数据库的创建
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create Table Muser(_id integer not null primary key autoincrement," +//id integer，不为空，主键，自增长，默认自增
                "account varchar(100) not null unique," +
                "password varchar(20) not null," +
                " nick varchar(100))";
        db.execSQL(sql);//执行SQL语句
//        添加的两种方式
//        sql="insert into Muser values(null,'admin','admin','无形装X_最为致命')";
        sql = "insert into Muser values(?,?,?,?)";
        db.execSQL(sql, new Object[]{null, "admin", "admin", "无形装X_最为致命"});
    }

    //数据库的更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.beginTransaction();//开始
            for (String sql : updateSqlList) {
                db.execSQL(sql);
            }
//        设置事物成功
            db.setTransactionSuccessful();//要想提交必须有这句，如果没有这句话，在end时会进行回滚操作
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束
        }
    }
}
