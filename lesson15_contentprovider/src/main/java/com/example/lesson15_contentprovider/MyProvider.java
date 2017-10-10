package com.example.lesson15_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by 怪蜀黍 on 2016/11/24.
 */

public class MyProvider extends ContentProvider {
    private static final int CUSTOMS = 10;
    private static final int CUSTOM = 15;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    //    进行Uri的匹配操作
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());//获取Context的对象
//        参数，当Uri和UriMacher不匹配时放回的code
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        给UriMatcher添加可匹配的内容
        uriMatcher.addURI("custom", "customs", CUSTOMS);//custom在manifirst中注册
//        #表示匹配任意的数字
        uriMatcher.addURI("custom", "customs/#", CUSTOM);//custom在manifirst中注册
        return true;
    }

    private SQLiteDatabase getDB() {
        if (db == null) {
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        SQLiteDatabase db = getDB();
//        使用UriMatcher匹配URI
        switch (uriMatcher.match(uri)) {
            case CUSTOMS:
                c = db.query("custom", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CUSTOM:
//                取出uri中携带的id
                Long id = ContentUris.parseId(uri);
                String where = "_id=" + id;
                if (!TextUtils.isEmpty(selection)) {
                    where = where + "and" + selection;
                }
                c = db.query("custom", projection, where, selectionArgs, null, null, sortOrder);
                break;
        }
        if (c != null) {
//            给游标设置游标所对应的数据变化时通知的uri
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override//添加方法
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = getDB();
        long id = 0;
        switch (uriMatcher.match(uri)) {
            case CUSTOMS:
                id = db.insert("custom", "first_name", values);
                break;
        }
//        在uri的末尾追加id
        if (id > 0) {
            getContext().getContentResolver()
                    .notifyChange(uri, null);//通知变化
        }
        return ContentUris.withAppendedId(uri, id);
    }

    @Override//删除方法
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override//修改方法
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
