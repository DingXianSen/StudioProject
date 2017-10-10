package com.example.work18;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 怪蜀黍 on 2016/11/29.
 */

public class MyPagerAdapter extends PagerAdapter {
    //    文本信息
    private List<Map<String, String>> titles;
    private Context context;

    public MyPagerAdapter(Context context, List<Map<String, String>> titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(context);
        String title = titles.get(position).get("title");
        textView.setText(title);
//        添加到容器中
        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
