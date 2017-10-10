package com.example.lesson27_refresh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class PtrHActivity extends AppCompatActivity {
    private PtrFrameLayout ptrLayout;
    private RecyclerManager recyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_pulltorefresh_h);
        ptrLayout = (PtrFrameLayout) findViewById(R.id.ptr_refresh);
        recyclerManager = new RecyclerManager(this);
        recyclerManager.addAll(getData());
//        ptrLayout.setMode(PtrFrameLayout.Mode.REFRESH);//
        ptrLayout.setMode(PtrFrameLayout.Mode.BOTH);
        ptrLayout.setPtrHandler(handler);


        StoreHouseHeader header=new StoreHouseHeader(this);
        StoreHouseHeader footer=new StoreHouseHeader(this);
//        创建自己的文字
        header.initWithPointList(getCustom());
        footer.initWithStringArray(R.array.footer);
//        header.initWithString("Loading...");
//        footer.initWithString("More");
        header.setTextColor(Color.BLACK);
        footer.setTextColor(Color.RED);
//        设置头脚
        ptrLayout.setHeaderView(header);

        ptrLayout.setFooterView(footer);

//        头和脚的UI更新
        ptrLayout.addPtrUIHandler(header);
        ptrLayout.addPtrUIHandler(footer);


        ptrLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
//                不能在onCreate中直接调用，否则无效，在onCreate中时，要延时
                ptrLayout.autoRefresh();//点击进入自动刷新
            }
        },50);
    }

    public ArrayList<float[]> getCustom(){
        ArrayList<float[]> data=new ArrayList<>();
        data.add(new float[]{0,50,100,50});
        return data;
    }

    private PtrHandler handler=new PtrHandler2() {
        @Override
        public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
//            并不能直接写为true，要自己进行判断，比如，有20条数据，但是20条数据并没有都显示出来，，但是当滑动时，他会进行刷新
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
