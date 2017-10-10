package com.example.lesson20_handler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 怪蜀黍 on 2016/12/1.
 */

public class SeconedActivity extends AppCompatActivity {
    private NetworkImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconed);
        image= (NetworkImageView) findViewById(R.id.image);
        RequestQueue queue= Volley.newRequestQueue(this);
//        ImageLoader loader=new ImageLoader(queue, new ImageLoader.ImageCache() {
//            @Override
//            public Bitmap getBitmap(String s) {
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String s, Bitmap bitmap) {
//
//            }
//        });
        Http.init(this);
        image.setImageUrl("https://www.baidu.com/img/bd_logo1.png",Http.getImageLoader());


//          参数1：请求方式，参数2：地址，参数3：new Listener，参数4：ErrorListener
       /* StringRequest request=new StringRequest(Request.Method.GET, "http://www.weather.com.cn/data/cityinfo/101010100.html", new Response.Listener<String>() {
            @Override//拿到网络返回的字符串
            public void onResponse(String s) {
                Log.e("aaaa","=======onResponse=============s=========="+s);
//                得到数据之后添加到适配器
//                adapter.add();
//                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaaa","============onErrorResponse==========volleyError========"+volleyError);
            }
        });*/

        Map<String,String> params=new HashMap<>();
        params.put("account","aaaa");
        params.put("password","aaaa");
//        使用自定义类的时候
        StringRequest request=new MyStringRequest( "http://www.weather.com.cn/data/cityinfo/101010100.html",params, new Response.Listener<String>() {
            @Override//拿到网络返回的字符串
            public void onResponse(String s) {
                Log.e("aaaa","=======onResponse=============s=========="+s);
//                得到数据之后添加到适配器
//                adapter.add();
//                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaaa","============onErrorResponse==========volleyError========"+volleyError);
            }
        });
//        设置请求的内容是否缓存,默认为true
        request.setShouldCache(true);
//        添加请求，自动执行
//        queue.add(request);
//        使用Http类的时候
        Http.getQueue().add(request);
    }

    class MyStringRequest extends StringRequest {
        private Map<String,String> parms;
        public MyStringRequest( String url, Map<String,String> parms,Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(Method.POST, url, listener, errorListener);
            this.parms=parms;
        }

        public Map<String, String> getParms() {
            return parms;
        }
    }
}
