package com.example.work13_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 怪蜀黍 on 2016/11/23.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "mydb.db";

    public DBHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table mydata(_id integer not null primary key autoincrement,textinfo varchar(100))";
        db.execSQL(sql);
        db.execSQL("insert into mydata values(null,'数据1')");
        db.execSQL("insert into mydata values(null,'数据2')");
        db.execSQL("insert into mydata values(null,'数据3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table mydata");
        onCreate(db);
    }
}
