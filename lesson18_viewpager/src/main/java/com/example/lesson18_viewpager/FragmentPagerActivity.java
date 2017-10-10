package com.example.lesson18_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/29.
 */

public class FragmentPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyFragmentAdapter adapter;
    private MessageFragment mf;
    private ActivityFragment af;
    private CanstactFragment cf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter=new MyFragmentAdapter(getSupportFragmentManager(),getFragments());
        viewPager.setAdapter(adapter);
    }

    private List<Fragment> getFragments() {
        mf=new MessageFragment();
        af=new ActivityFragment();
        cf=new CanstactFragment();
        List<Fragment> fragments=new ArrayList<>();
            fragments.add(mf);
            fragments.add(af);
            fragments.add(cf);
        return fragments;
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }
}

