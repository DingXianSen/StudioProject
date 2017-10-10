package com.example.work15;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by 怪蜀黍 on 2016/11/25.
 */

public class MyProvider extends ContentProvider {
    private static final int CUSTOMS = 15;
    private static final int CUSTON = 10;
    //
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    //    Uri的匹配操作
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
//        获取Content对象
        dbHelper = new DBHelper(getContext());
//        当Uri和UriMatcher不匹配时返回的结果码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        给UriMatcher添加可匹配的内容
        uriMatcher.addURI("custom", "customs", CUSTOMS);
        uriMatcher.addURI("custom", "customs/#", CUSTON);
//        return要返回true
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
//         使用UriMatcher匹配Uri
        switch (uriMatcher.match(uri)) {
            case CUSTOMS:
                c = db.query("custom", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CUSTON:
//                取出uri中携带的id
                Long id = ContentUris.parseId(uri);
                String Id = "_id" + id;
                if (!TextUtils.isEmpty(selection)) {
                    Id = Id + "and" + selection;
                }
                c=db.query("custom",projection,Id,selectionArgs,null,null,sortOrder);
                break;
        }
        if (c!=null){
//            给游标设置游标对应的数据变化时的通知
            c.setNotificationUri(getContext().getContentResolver(),uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = getDB();
        long id = 0;
        switch (uriMatcher.match(uri)) {
            case CUSTOMS:
                id = db.insert("custom", "name", values);
                break;
        }
//        在uri的末尾追加id
        if (id > 0) {
            getContext().getContentResolver()
                    .notifyChange(uri, null);//通知变化
        }
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = getDB();
        Log.e("aaaa","=========================db"+db.getPath().toString());
        Log.e("aaaa","=========================uri"+uri+"=============values"+values.get("name")+"===========selection"+selection);
        int c=db.update("custom",values,selection,selectionArgs);
        if (c>0){
//            给游标设置游标对应的数据变化时的通知
            getContext().getContentResolver()
                    .notifyChange(uri, null);//通知变
        }
        return  db.update("custom",values,selection,selectionArgs);
    }
}
