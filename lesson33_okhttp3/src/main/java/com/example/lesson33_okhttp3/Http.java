package com.example.lesson33_okhttp3;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 怪蜀黍 on 2016/12/29.
 */

public class Http {
    private static OkHttpClient client;
    private static String mediaType;

    public static void setMediaType(String mediaType) {
        Http.mediaType = mediaType;
    }

    static {
        client = new OkHttpClient();
    }

    public static void get(String url, Callback callback) {
        post(url, null, callback);
    }

    public static void post(String url, Map<String, Object> params, Callback callback) {

        Request.Builder builder = new Request.Builder();
        builder.url(url);//设置访问地址
        if (params == null) {
            builder.get();
        } else {
            RequestBody body = createRequestBody(params);//createRequestBody(params)；//创建body的方法
            builder.post(body);
        }
        Call call = client.newCall(builder.build());
        call.enqueue(callback);
    }

    private static RequestBody createRequestBody(Map<String, Object> params) {
        Iterator it = params.values().iterator();
        boolean isMultiper = false;
//        判断是否包含文件类型
        while (it.hasNext()) {
            if (it.next() instanceof File) {
                isMultiper = true;
                break;
            }
        }
        RequestBody body = null;
        if (isMultiper) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
//            遍历所有要穿上传的参数，添加到builder中
            Iterator<Map.Entry<String, Object>> entryIt = params.entrySet().iterator();
            while (entryIt.hasNext()) {
                Map.Entry<String, Object> entry = entryIt.next();
                if (entry.getValue() instanceof File) {
                    File file = (File) entry.getValue();
                    builder.addFormDataPart(entry.getKey(), file.getName()
                            , RequestBody.create(MediaType.parse(mediaType), file));
                } else {
                    builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
                }
            }
            body = builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            Iterator<Map.Entry<String, Object>> entryIt = params.entrySet().iterator();
            while (entryIt.hasNext()) {
                Map.Entry<String, Object> entry = entryIt.next();
//                IF是纯表单直接添加到key和value
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            body = builder.build();
        }
        return body;
    }
}
