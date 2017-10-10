package com.example.lesson14_listview_json;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/23.
 */

public class JsonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

//        有对象转换为字符串
        User user = new User();
        user.setPhone("11111111");
        user.setNmae("张三");
        user.setPhone("twtwtwtwtwtwtwtwtwtwtwtwtwtwtwtwtw");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        user = new User("ggggggggg", "3333333333", "0000000");
        users.add(user);


//        属性过滤器
        PropertyFilter pf = new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                Log.e("aaaa", o + "========o========" + s + "============s=========" + o1 + "==============o1===========");
                if ("name".equals(s)) {
                    return false;
                }

                return true;
            }
        };

        //        fastjson集合
//        String jsonList = JSON.toJSONString(users);
//        String jsonList = JSON.toJSONString(users,SerializerFeature.DisableCircularReferenceDetect);//禁用循环引用
//        过滤
        String jsonList = JSON.toJSONString(users, pf);
        Log.e("aaaa", "===========fastjson集合==========" + jsonList);


        //        fastjson,对象转换为json
        String json = JSON.toJSONString(user);
        Log.e("aaaa", "===========fastjson==========" + json);


        //        字符串转换成对象
        User u = JSON.parseObject(json, User.class);
        Log.e("aaaa", "========字符串转换成对象=======" + u.getNmae());


            List<User> us = JSON.parseArray(json, User.class);
        Log.e("aaaa", "===========集合转换==========" + us.size());
        Log.e("aaaa", "===========集合转换==========" + us.get(0).getNmae());


//        List<User> uu=JSON.parseObject(json,new TypeReference<List<User>>(){}.getType());

        //        gson
        Gson gson = new Gson();
        json = gson.toJson(user);
        Log.e("aaaa", "===========Gson==========" + json);


//        gson转换
        u = gson.fromJson(json, User.class);
        Log.e("aaaa", "========gson转换字符串转换成对象=======" + u.getNmae());

        List<User> ugs =gson.fromJson(json,new TypeToken<List<User>>(){}.getType());
        Log.e("aaaa","========="+ugs.size());
        Log.e("aaaa","========="+ugs.get(0).getNmae());

                //        gson集合
                Gson gsonList = new Gson();
        json = gson.toJson(users);
        Log.e("aaaa", "===========Gson集合==========" + gsonList);


    }


}
