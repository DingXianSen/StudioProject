package com.example.lesson27_refresh;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerManager recyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        recyclerManager = new RecyclerManager(this);
        recyclerManager.addAll(getData());

//        设置指示标的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE,Color.YELLOW,Color.GREEN);
//        刷新监听
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    /**
     * 当下拉刷新时会调用这个方法
     */
    public SwipeRefreshLayout.OnRefreshListener listener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
//        模拟网络请求
            swipeRefreshLayout.postDelayed(runnable,3000);//延迟3秒
        }
    };
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
        recyclerManager.clear();
            recyclerManager.addAll(getData());
            //设置非正在刷新
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    public List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item" + i);
        }
        Log.e("aaaa","==================MainActivity中----getDate()"+data);
        return data;
    }
}
