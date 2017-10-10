package com.example.work18_2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyFraementAdapter adapter;
    private RadioGroup radioGroup;
    private RadioButton info, act, tan;
    private int[] fragment = new int[]{R.layout.fragment_message, R.layout.fragment_canstact, R.layout.fragment_activity};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vp1);
        radioGroup = (RadioGroup) findViewById(R.id.rg2);
        info = (RadioButton) findViewById(R.id.info);
        act = (RadioButton) findViewById(R.id.active);
        tan = (RadioButton) findViewById(R.id.constact);
        radioGroup.setOnCheckedChangeListener(listener);

        adapter = new MyFraementAdapter(getSupportFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    public ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    public RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.info:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.constact:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.active:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    };

    private List<Fragment> list = new ArrayList<>();

    private List<Fragment> getFragments() {
        list.add(new MessageFragment());
        list.add(new CanstactFragment());
        list.add(new ActiveFragment());
        return list;
    }

    class MyFraementAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        public MyFraementAdapter(FragmentManager fm, List<Fragment> fragments) {
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
