package com.example.lesson20_handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by 怪蜀黍 on 2016/12/1.
 */

public class Http {
    private static RequestQueue queue;
    private static ImageLoader imageLoader;

    public static void init(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        if (imageLoader == null) {
            final int size = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);//将总缓存的8分之1作为图片缓存
            imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
                LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(size) {
                    @Override//重写计算每个缓存对象缓存大小的方法
                    protected int sizeOf(String key, Bitmap value) {
//                        value.getByteCount()//总字节数
                        return value.getByteCount() / 1024;//总字节数

                    }
                };

                @Override//取出缓存的位图
                public Bitmap getBitmap(String s) {
                    return cache.get(s);
                }

                @Override//存入要缓存的位图
                public void putBitmap(String s, Bitmap bitmap) {
                    cache.put(s,bitmap);
                }
            });
        }
    }

    public static RequestQueue getQueue() {
        if (queue==null){
            throw new ExceptionInInitializerError("queue未初始化，需要先调用inti()");
        }
        return queue;
    }

    public static ImageLoader getImageLoader() {
        if (imageLoader==null){
            throw new ExceptionInInitializerError("imageLoader未初始化，需要先调用inti()");
        }
        return imageLoader;
    }
}
