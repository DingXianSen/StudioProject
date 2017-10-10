package com.example.lesson18_viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Map;

/**
 * Created by 怪蜀黍 on 2016/11/29.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<Map<String, Object>> data;//标题和图片

    private Context context;//

    public MyPagerAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
//    以上两个方法还没有说显示那个
//     所以还要重写两个方法
    @Override//实例化
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image=new ImageView(context);//每次打开时都需要重新创建，所以，这种适合于简单的页面
        int id=(int)data.get(position).get("image");
        image.setImageResource(id);
        image.setScaleType(ImageView.ScaleType.FIT_XY);//图片拉伸显示
        container.addView(image);//添加到容器中
        return image;
    }

    @Override//销毁
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);//这个object就是返回的那个对象image
    }
//获取标题方法
//    public String getTitle(int position){
//    return (String)data.get(position).get("title");
//    }
//    或者重写

    @Override
    public CharSequence getPageTitle(int position) {
        return (String)data.get(position).get("title");
    }
}
