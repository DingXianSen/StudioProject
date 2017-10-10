package com.example.lesson25_recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 怪蜀黍 on 2016/12/16.
 */

public class SlidingActivity extends AppCompatActivity {
    private SlidingPaneLayout sliding;
    private FrameLayout menu;
    private CoordinatorLayout body;

    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
//    private List<Map<String, Object>> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slidepanlayout);
        menu = (FrameLayout) findViewById(R.id.menu);
        body = (CoordinatorLayout) findViewById(R.id.body);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        sliding = (SlidingPaneLayout) findViewById(R.id.sliding);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        设置视差距离
        sliding.setParallaxDistance(200);
        sliding.setPanelSlideListener(listener);



        tabLayout.addTab(tabLayout.newTab().setText("全部").setTag("all"));
        tabLayout.addTab(tabLayout.newTab().setText("已发货").setTag("send"));
        tabLayout.addTab(tabLayout.newTab().setText("未评价").setTag("comment"));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SlidingActivity.this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
//            adapter = new MyAdapter(this,createData());
        adapter=new MyAdapter(SlidingActivity.this,createData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyDecotation(SlidingActivity.this));
    }

    //        初始化数据
    private List<Map<String,Object>> createData() {
        List<Map<String,Object>> data=new ArrayList<>();
        int position=tabLayout.getSelectedTabPosition();
        TabLayout.Tab tab=tabLayout.getTabAt(position);
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("icon", R.mipmap.ic_launcher);
            map.put("title", tab.getText().toString()+i);
            map.put("catagroy", "catagroy" + i);
            data.add(map);
        }
        return data;
    }

     private TabLayout.OnTabSelectedListener tabSelectedListener=new TabLayout.OnTabSelectedListener() {
         @Override
         public void onTabSelected(TabLayout.Tab tab) {
            adapter.addAll(createData());
         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

         @Override
         public void onTabReselected(TabLayout.Tab tab) {

         }
     };
    private SlidingPaneLayout.PanelSlideListener listener = new SlidingPaneLayout.PanelSlideListener() {
        @Override//在滑动中持续调用
        public void onPanelSlide(View panel, float slideOffset) {
//      计算缩放比例值
            float scaleBody = 1 - (slideOffset * 0.2f);
//            规定缩放中心
            body.setPivotX(0);
            body.setPivotY(body.getHeight() / 2);
            body.setScaleX(scaleBody);
            body.setScaleY(scaleBody);
//            菜单部分从小到大
            float scaleMenu = 0.8f + (slideOffset * 0.2f);
            menu.setPivotY(menu.getWidth());
            menu.setPivotY(menu.getHeight() / 2f);
            menu.setScaleX(scaleMenu);
            menu.setScaleY(scaleMenu);
//            设置透明度变化
            menu.setAlpha(slideOffset);
//            sliding.setSliderFadeColor(Color.parseColor("#00ffff"));//滑动部分的覆盖色
//            sliding.setCoveredFadeColor(Color.RED);//设置菜单滑动的颜色设置
//            sliding.openPane();//打开
//            sliding.closePane();//关闭

        }

        @Override//打开后
        public void onPanelOpened(View panel) {

        }

        @Override//关闭后
        public void onPanelClosed(View panel) {

        }
    };
}
