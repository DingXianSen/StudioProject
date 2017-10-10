package com.example.lesson25_recyclerview;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import static android.R.attr.data;

/**
 * Created by 怪蜀黍 on 2016/12/16.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.LayoutParams lp;
    private Context context;
    private LayoutInflater lif;
    private List<Map<String,Object>> data;

    public MyAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        lif=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            lp = new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT);
            View v = lif.inflate(R.layout.layout_item_1, null);
            v.setLayoutParams(lp);
            return new MyHolder(v);
        } else {
            return new MyHolder1(lif.inflate(R.layout.layout_item_2, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Map<String, Object> map = data.get(position);
        if (holder.getItemViewType() == 0) {
            MyHolder h = (MyHolder) holder;
            h.title.setText(map.get("title").toString());
            h.catagroy.setText(map.get("catagroy").toString());
            h.icon.setImageResource((Integer) map.get("icon"));
        } else {
            MyHolder1 h = (MyHolder1) holder;
            h.title.setText(map.get("title").toString());
            h.icon.setImageResource((Integer) map.get("icon"));
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void addAll(List<Map<String, Object>> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, catagroy;

        public MyHolder(View itemView) {
            super(itemView);
            icon = (ImageView) this.itemView.findViewById(R.id.icon);
            title = (TextView) this.itemView.findViewById(R.id.title);
            catagroy = (TextView) this.itemView.findViewById(R.id.catagroy);
//            this.itemView.setOnClickListener(clickListener);
            this.itemView.setTag(this);//1111
        }
    }


    class MyHolder1 extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public MyHolder1(View itemView) {
            super(itemView);
            icon = (ImageView) this.itemView.findViewById(R.id.icon);
            title = (TextView) this.itemView.findViewById(R.id.title);
//            this.itemView.setOnClickListener(clickListener);
        }
    }
}
