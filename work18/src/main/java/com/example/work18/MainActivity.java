package com.example.work18;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tv_title;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private String[] titlesdata = new String[]{"这是信息界面", "这是联系人界面", "这是活动界面"};
    private RadioGroup rg;
    private RadioButton message;
    private RadioButton canstact;
    private RadioButton activity;
    private int lastPosition;//上一个位置，用来记录那个位置


    private RadioGroup rgoup;

    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(lis);

        message = (RadioButton) findViewById(R.id.message);
        canstact = (RadioButton) findViewById(R.id.canstact);
        activity = (RadioButton) findViewById(R.id.activity);
        rl = (RelativeLayout) findViewById(R.id.rgtop);

        tv_title = (TextView) findViewById(R.id.title);
        adapter = new MyPagerAdapter(this, getData());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(listener);
        tv_title.setText(titlesdata[0]);


        }

        public RadioGroup.OnCheckedChangeListener lis = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.e("bbbb","===========================================group"+group);
            switch (checkedId) {
                case R.id.message:
                    Log.e("aaaa","=======checkedId========message"+checkedId);
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.canstact:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.activity:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    };
    public ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override//改变界面时，的发生
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e("aaaa", "===========position" + position + "============positionOffset" + positionOffset + "===============positionOffsetPixels" + positionOffsetPixels);
//            if (position==0){
//                //第一项选中
////                message.setSelected(true);
//                message.setChecked(true);
//
//            }else if(position==1){
//                //第二项选中
////                canstact.setSelected(true);
//                canstact.setChecked(true);
//            }else if(position==2){
//                //第三项
////                activity.setSelected(true);
//                activity.setChecked(true);
//            }
        }

        @Override
        public void onPageSelected(int position) {
            ((RadioButton) rg.getChildAt(position)).setChecked(true);
//            rg.getChildAt(position).setSelected(true);
//            lastPosition=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    /**
     * 获取标题的方法
     *
     * @return
     */
    private List<Map<String, String>> getData() {
        List<Map<String, String>> titles = new ArrayList<>();
        for (int i = 0; i < titlesdata.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", titlesdata[i]);
            titles.add(map);
        }
        return titles;
    }

}
