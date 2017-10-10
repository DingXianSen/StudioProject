package com.example.lesson27_refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/12/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private View.OnClickListener clickListener;
    private List<String> data;
    private LayoutInflater lif;
    private Context context;

    public MyAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        this.lif = LayoutInflater.from(context);
    }

    //点击事件的方法
    public void setOnItemClickListener(View.OnClickListener listener) {
        clickListener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(lif.inflate(android.R.layout.simple_list_item_1, null));
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }
    //注意tag和Text
//    @Override
//    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.textView.setTag(data.get(position));
//    }

    /**
     * 添加所有数据
     *
     * @param data
     */
    public void addAll(List<String> data) {
        if (this.data == null) {
            this.data = new LinkedList<>();
        }
        Log.e("aaaa","============MyAdapter中-------addAll传进的data"+data.size());
        this.data.addAll(data);
    }

    /**
     * 添加单个数据
     *
     * @param data
     */
    public void add(String data) {
        if (this.data == null) {
            this.data = new LinkedList<>();
        }
        this.data.add(data);
    }

    /**
     * 移除数据
     *
     * @param position
     * @return
     */
    public String remove(int position) {
        return this.data.remove(position);
    }

    /**
     * 清空数据
     */
    public void clear() {
        this.data.clear();
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;//三元表达式
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
            itemView.setTag(this);
            itemView.setOnClickListener(clickListener);//设置点击事件
        }
    }

}
