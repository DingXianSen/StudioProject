package com.example.lesson18_viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llContainer;
    private TextView tvTitle;
    private int[] images = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f};//图片数组
    private float density;
    private MyPagerAdapter adapter;
    private int lastPosition;//上一个位置，用来记录点的位置

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llContainer = (LinearLayout) findViewById(R.id.container);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tvTitle= (TextView) findViewById(R.id.title);
//        设置padding时是像素，要换算成dp
//        获取屏幕密度比值
        density = getResources().getDisplayMetrics()//获取屏幕的物理信息
                .density;//比例值
        adapter = new MyPagerAdapter(this, getData());//上下文，数据源
        viewPager.setAdapter(adapter);
//        调用添加点的方法
        addDian();
        viewPager.setOnPageChangeListener(pageChangeListener);
        tvTitle.setText(adapter.getPageTitle(0));//初始化的时候，显示默认的第0个
//        自动滚动操作,定时
        tvTitle.postDelayed(r,3000);
    }

     private Runnable r=new Runnable() {
        @Override
        public void run() {
//            先获取当前页面下标
            int curr=viewPager.getCurrentItem();
            if (curr+1<adapter.getCount()) {
//            设置当前显示项
                viewPager.setCurrentItem(curr + 1);
            }else{
                viewPager.setCurrentItem(0);
            }
//            然后在页面改变时发生变化，实现一直滚动
        }
    };
//      改变图片时，点发生变化
    private ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override//当页面滚动时调用 参数1：第几个页面当前 参数2：百分比 参数3：像素
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override//页面被选中时调用
        public void onPageSelected(int position) {
//            Log.e("aaaa","===========onPageScrollStateChanged=========state"+position);
//            根据页面下标获取点，设置点为选中
            llContainer.getChildAt(lastPosition).setSelected(false);
            llContainer.getChildAt(position).setSelected(true);
            lastPosition=position;
            tvTitle.setText(adapter.getPageTitle(position));
        }

        @Override//页面的滚动状态发生变化
        public void onPageScrollStateChanged(int state) {
//            Log.e("aaaa","===========onPageScrollStateChanged=========state"+state);
            if (state==0){
                tvTitle.postDelayed(r,3000);
            }else if(state==1){//表示手指滑动
//                移除已定时未执行的r的操作
                tvTitle.removeCallbacks(r);
            }
        }
    };

    //      获取数据的方法
    private List<Map<String, Object>> getData() {
        List<Map<String,Object>>data=new ArrayList<>();
        for (int i=0;i<images.length;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("image",images[i]);
            map.put("title","你愁啥..."+i);
            data.add(map);
        }
        return  data;
    }

    private void addDian() {
        int size = (int) (density * 10);
        for (int i = 0; i < images.length; i++) {
//            动态新建imageView
            ImageView dian = new ImageView(this);
            dian.setImageResource(R.drawable.select_dian);
            dian.setSelected(i == 0);//这时第一项是选中的，已显示点的选中状态
            dian.setPadding(size, 0, 0, 0);//直接写的话是像素px,左上右下
//            添加到容器中
            llContainer.addView(dian);
        }
    }
}
