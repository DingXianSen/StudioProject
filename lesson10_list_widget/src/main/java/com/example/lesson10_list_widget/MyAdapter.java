package com.example.lesson10_list_widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/16.
 */

public class MyAdapter extends BaseAdapter {
    private List<Student> students;
    private LayoutInflater lif;

    public MyAdapter(Context context , List<Student> students) {
        this.students = students;
        lif=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return students != null ? students.size() : 0;
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        解析xml文件
//        将给定的布局加载成View，参数2：父容器，null，表示无父容器
        View view= lif.inflate(R.layout.layout_item,null);
//        绑定数据
        ImageView image= (ImageView) view.findViewById(R.id.images);
        TextView tv= (TextView) view.findViewById(R.id.textwoshi);
        CheckBox cb1= (CheckBox) view.findViewById(R.id.cb1);
        CheckBox cb2= (CheckBox) view.findViewById(R.id.cb2);
//        给控件设置数据
        image.setImageResource(R.mipmap.ic_launcher);
        tv.setText(getItem(position).getName());
        cb1.setText(getItem(position).getPhone());
        cb2.setChecked(position%2==0);
//        返回加载好的视图
        return view;
    }
}
