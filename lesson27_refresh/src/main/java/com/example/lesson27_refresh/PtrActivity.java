package com.example.lesson27_refresh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrHandler2;

public class PtrActivity extends AppCompatActivity {
//    private PtrFrameLayout ptrLayout;
    private PtrClassicFrameLayout ptrLayout;
    private RecyclerManager recyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_pulltorefresh);
        ptrLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr_refresh);
        recyclerManager = new RecyclerManager(this);
        recyclerManager.addAll(getData());
//        ptrLayout.setMode(PtrFrameLayout.Mode.REFRESH);//
        ptrLayout.setMode(PtrFrameLayout.Mode.BOTH);
        ptrLayout.setPtrHandler(handler);

//        设置显示上一次更新的时间
        ptrLayout.setLastUpdateTimeFooterKey("footer");
        ptrLayout.setLastUpdateTimeHeaderKey("header");

//        设置加载所需要的最小时间
        ptrLayout.setLoadingMinTime(1000);

        ptrLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
//                不能在onCreate中直接调用，否则无效，在onCreate中时，要延时
                ptrLayout.autoRefresh();//点击进入自动刷新
            }
        },50);

//        PtrClassicDefaultFooter footer=new PtrClassicDefaultFooter(this);
////       设置脚视图,,设置头试图和这个一样单词不一样而已
//        ptrLayout.setFooterView(footer);
    }

//    private PtrHandler handler = new PtrHandler() {
//        @Override//检查能否刷新
//        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//            return true;
//        }
//
//        @Override//开始刷新时
//        public void onRefreshBegin(PtrFrameLayout frame) {
//            ptrLayout.postDelayed(runnable,3000);
//        }
//    };

    /********************************************************************/
    private PtrHandler handler=new PtrHandler2() {
        @Override
        public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
            return true;
        }

        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            ptrLayout.postDelayed(runnable,3000);
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return true;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            ptrLayout.postDelayed(runnable,3000);
        }
    };
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            recyclerManager.clear();
            recyclerManager.addAll(getData());
            //设置刷新完成
            ptrLayout.refreshComplete();

        }
    };

    public List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item" + i);
        }
        Log.e("aaaa", "==================MainActivity中----getDate()" + data);
        return data;
    }
}
