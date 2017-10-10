package com.example.lesson_19_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/30.
 */

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new MyAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);

//        要在viewPage设置Adapter后调用
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager, true);
//        tabLayout.setOnTabSelectedListener();//过时
        tabLayout.addOnTabSelectedListener(listener);//替代

//
//        tabLayout.addTab(tabLayout.newTab()
//                .setText("这是标签显示的内容")
//                .setIcon(R.mipmap.ic_launcher)//图标
//                .setTag("tag"));
//        tabLayout.addTab(tabLayout.newTab()
//                .setText("这是标签显示的内容")
//                .setIcon(R.mipmap.ic_launcher)//图标
//                .setTag("tag"));//标签
//                .setCustomView(null));//自己定义视图作为指示器

    }

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override//标签选中时
        public void onTabSelected(TabLayout.Tab tab) {
//            tabLayout.getSelectedTabPosition();//选中标签下标
//            tab.getPosition();//获取标签的下标
//            tabLayout.getTabAt(index);//根据下标获取标签
        }

        @Override//标签变为未被选中时
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override//重复选中时
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MessageFragment());
        fragments.add(new CanstactFragment());
        fragments.add(new ActivityFragment());
//        多个时设置mode可以使其为滚动
//        fragments.add(new MessageFragment());
//        fragments.add(new CanstactFragment());
//        fragments.add(new ActivityFragment());
//        fragments.add(new MessageFragment());
//        fragments.add(new CanstactFragment());
//        fragments.add(new ActivityFragment());
        return fragments;
    }

    class MyAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments != null ? fragments.size() : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "新闻";
        }
    }
}
