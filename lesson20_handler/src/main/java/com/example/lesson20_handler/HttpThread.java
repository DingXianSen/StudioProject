package com.example.lesson20_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 怪蜀黍 on 2016/12/1.
 */

public class HttpThread extends Thread {
    private OnLoadListener loadListener;
    //    Looper.getMainLooper()主线程
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 10:
                    if (loadListener != null) {
                        loadListener.onUpdate(msg.arg1, msg.arg2);
                    }
                    break;
                case 12:
                    if (loadListener != null) {
                        loadListener.onCommplete((Bitmap) msg.obj);
                    }
                    break;
            }
            return true;
        }
    });

    public HttpThread(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    //子线程
    @Override
    public void run() {
        super.run();
//        为本线程准备Lopper对象
//        Looper.prepare();//准备
////        Looper对象循环
//        Looper.loop();
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://192.168.146.1:8080/1.jpg");
            conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int total = conn.getContentLength();//获取总长度
                int curr = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    curr += len;
                    //                        传递消息
                    Message message = handler.obtainMessage();
                    message.what = 10;//进行上边的匹配，看执行那个操作
                    message.arg1 = curr;
                    message.arg2 = total;
                    //                        message.sendToTarget();//发送消息，或者
                    handler.sendMessage(message);
                    bos.write(b, 0, len);
                    sleep(50);
                }
                b = bos.toByteArray();
                Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);//转换为图片
                Message msg = handler.obtainMessage();
                msg.what = 12;
                msg.obj = bmp;
                msg.sendToTarget();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();//关闭链接
            }
        }
    }

    public interface OnLoadListener {
        void onUpdate(int current, int total);

        void onCommplete(Bitmap bmp);
    }
}
