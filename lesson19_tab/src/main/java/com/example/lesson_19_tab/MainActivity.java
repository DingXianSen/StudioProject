package com.example.lesson_19_tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        使用FragmentTabHost是需要注意一下几点
//        1,先调用setup
//        2,必须添加tab
        tabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        tabHost.setup(this, getSupportFragmentManager(), R.id.container);
//        参数1,tab标签,参数2，标签标题，
//        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1"),MessageFragment.class,null);
//        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2"),CanstactFragment.class,null);
//        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("TAB3"),ActivityFragment.class,null);
//        自定义布局时的写法，getTab()。这个是自定义方法，拿到标题和图片
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getTabView("消息", R.mipmap.ic_launcher)), MessageFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getTabView("好友", R.mipmap.ic_launcher)), CanstactFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(getTabView("朋友圈", R.mipmap.ic_launcher)), ActivityFragment.class, null);
//        常用的方法
//        tabHost.setCurrentTab();//使用下表
        tabHost.setCurrentTabByTag("tab2");//使用tab标签
        tabHost.setOnTabChangedListener(tabChangeListener);
//        设置标签项间的间隔图片
        tabHost.getTabWidget().setDividerDrawable(null);//把间隔图片去掉
    }


    //    设置标签变化监听
    private TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
        @Override//参数，tab标签，也就是上边的tab1
        public void onTabChanged(String tabId) {
            Toast.makeText(MainActivity.this, "============tab" + tabId, Toast.LENGTH_SHORT).show();
        }
    };

    private View getTabView(String tab, int resid) {
        View v = getLayoutInflater().inflate(R.layout.layout_tab, null);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView tv = (TextView) v.findViewById(R.id.tab);
        image.setImageResource(resid);
        tv.setText(tab);
        return v;
    }
}
