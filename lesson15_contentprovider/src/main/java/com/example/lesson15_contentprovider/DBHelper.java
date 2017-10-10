package com.example.lesson15_contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 怪蜀黍 on 2016/11/24.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "text_provider", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table custom(_id integer not null primary key autoincrement," +
                "first_name varchar(100)," +
                "last_name varchar(100)," +
                "address varchar(200))");
        db.execSQL("insert into custom values(null,'李','四','你们那嘎达的')");
        db.execSQL("insert into custom values(null,'王','四','你们那嘎达的')");
        db.execSQL("insert into custom values(null,'赵','四','你们那嘎达的')");
        db.execSQL("insert into custom values(null,'胡','四','你们那嘎达的')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
