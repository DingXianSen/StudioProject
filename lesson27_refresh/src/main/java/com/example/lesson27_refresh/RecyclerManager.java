package com.example.lesson27_refresh;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/12/20.
 */

public class RecyclerManager {
    private RecyclerView recyclerView;
    private Activity mActivity;
    private MyAdapter adapter;

    public RecyclerManager(Activity mActivity) {
        this.mActivity = mActivity;
        init();
    }

    //    初始化
    private void init() {
        recyclerView = (RecyclerView) mActivity.findViewById(R.id.recycler_view);
//        设置管理者
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setAutoMeasureEnabled(true);//自动测量
        recyclerView.setLayoutManager(layoutManager);

        adapter=new MyAdapter(mActivity,null);

        adapter.setOnItemClickListener(clickListener);
        recyclerView.setAdapter(adapter);
    }
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder holder= (RecyclerView.ViewHolder) v.getTag();
            int position= holder.getAdapterPosition();//获取在适配器中的下标
//            然后按照下标清除
           String item= adapter.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(mActivity, item+"移除", Toast.LENGTH_SHORT).show();
        }
    };


    public void addAll(List<String> data){
        Log.e("aaaa","=============RecyclerManager中-----addAll"+data);
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }
    public void clear(){
        adapter.clear();
        adapter.notifyDataSetChanged();
    }
}
