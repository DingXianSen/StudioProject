package com.example.work15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 怪蜀黍 on 2016/11/25.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "work15.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table custom(_id integer not null primary key autoincrement,name varchar(20))");
        db.execSQL("insert into custom values(null,'红茶')");
        db.execSQL("insert into custom values(null,'茉莉花茶')");
        db.execSQL("insert into custom values(null,'绿茶')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("if exist drop table custom");
//        onCreate(db);
    }
}
