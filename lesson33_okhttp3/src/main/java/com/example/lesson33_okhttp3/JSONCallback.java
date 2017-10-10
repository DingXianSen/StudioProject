package com.example.lesson33_okhttp3;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 怪蜀黍 on 2016/12/29.
 */

/**
 * 由主线程到子线程
 *
 * @param <T>
 */
public abstract class JSONCallback<T> implements Callback {
    private static final String TAG = "JsonCallback";
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    private Type type;
    private Call call;
    //   创建主线程处理消息的Handler
//    以将onFailure和onResponse方法的执行发送到主线程的方法执行
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    onFailure(call, msg.arg1, (Exception) msg.obj);
                    break;
                case FAILURE:
                    onSuccess(call, (T) msg.obj);
                    break;
            }
            return true;
        }
    });

    public JSONCallback() {
        Type superClass = this.getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public JSONCallback(Type type) {
        this.type = type;
    }

    //    发送消息
    @Override
    public void onFailure(Call call, IOException e) {
        this.call = call;
        Message msg = handler.obtainMessage();
        msg.what = FAILURE;
        msg.arg1 = 0;
        msg.obj = e;
        handler.sendMessage(msg);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        this.call = call;
        if (response.code() == 200) {
            String json = response.body().string();
            Log.e(TAG, "json---->>>>>>" + json);
            T t = JSON.parseObject(json, type);
            Log.e(TAG, "result---->>>>>>" + t);


            Message msg = handler.obtainMessage();
            msg.what = SUCCESS;
            msg.obj = t;//传递结果或者错误
            handler.sendMessage(msg);
        } else {
            Log.e(TAG, "error code---->>>>>>" + response.code());

            Message msg = handler.obtainMessage();
            msg.what = FAILURE;
            msg.arg1 = response.code();
            msg.obj = new Exception("网络访问失败");
            handler.sendMessage(msg);
        }
    }

    public abstract void onSuccess(Call call, T result);

    public void onFailure(Call call, int code, Exception e) {

    }
}
