package com.example.lesson25_recyclerview;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private List<Map<String, Object>> data;
    private List<Map<String,Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        参数：1上下文 2横向或者竖向 3数据反转
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        网格
//        RecyclerView.LayoutManager lm=new GridLayoutManager(this,3);
//        瀑布流
//        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        设置是否自动测量控件大小
        lm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(lm);//必须要有的，设置布局管理器
        createData();
//        recyclerView.setAdapter(MainActivity.this,data);
        recyclerView.setAdapter(new MyAdapter(this,data));
        Log.e("aaaa","================recylerView"+recyclerView.getAdapter().toString());
//        添加装饰，例qq头饰,添加每一项的装饰
        recyclerView.addItemDecoration(new MyDecotation(this));//添加了间隔,传入上下文
    }

//    //        //        初始化数据
private List<Map<String,Object>> createData() {
    List<Map<String, Object>> data = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("icon", R.mipmap.ic_launcher);
        map.put("title", "title" + i);
        map.put("catagroy", "catagroy" + i);
        data.add(map);
        Log.e("aaaa","=================data"+data);
    }
    return data;
}

//    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//        private RecyclerView.LayoutParams lp;
//
//        @Override
//        public int getItemViewType(int position) {
//            return position % 2;
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            if (viewType == 0) {
//                lp = new RecyclerView.LayoutParams(
//                        RecyclerView.LayoutParams.MATCH_PARENT,
//                        RecyclerView.LayoutParams.WRAP_CONTENT);
//                View v = getLayoutInflater().inflate(R.layout.layout_item_1, null);
//                v.setLayoutParams(lp);
//                return new MyHolder(v);
//            } else {
//                return new MyHolder1(getLayoutInflater().inflate(R.layout.layout_item_2, null));
//            }
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            Map<String, Object> map = data.get(position);
//            if (holder.getItemViewType() == 0) {
//                MyHolder h = (MyHolder) holder;
//                h.title.setText(map.get("title").toString());
//                h.catagroy.setText(map.get("catagroy").toString());
//                h.icon.setImageResource((Integer) map.get("icon"));
//            } else {
//                MyHolder1 h = (MyHolder1) holder;
//                h.title.setText(map.get("title").toString());
//                h.icon.setImageResource((Integer) map.get("icon"));
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return data != null ? data.size() : 0;
//        }
//    }
//
//
//  //    点击监听
//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
////            v.getTag();//取出标签，也就是进入坚挺之前的this
//            RecyclerView.ViewHolder holder= (RecyclerView.ViewHolder) v.getTag();
//            int position=holder.getAdapterPosition();
//            Map<String,Object> map= data.get(position);
//            Toast.makeText(MainActivity.this, map.get("title")+"====", Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    class MyHolder extends RecyclerView.ViewHolder {
//        ImageView icon;
//        TextView title, catagroy;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            icon = (ImageView) this.itemView.findViewById(R.id.icon);
//            title = (TextView) this.itemView.findViewById(R.id.title);
//            catagroy = (TextView) this.itemView.findViewById(R.id.catagroy);
//            this.itemView.setOnClickListener(clickListener);
//            this.itemView.setTag(this);//1111
//        }
//    }
//
//
//    class MyHolder1 extends RecyclerView.ViewHolder {
//        ImageView icon;
//        TextView title;
//
//        public MyHolder1(View itemView) {
//            super(itemView);
//            icon = (ImageView) this.itemView.findViewById(R.id.icon);
//            title = (TextView) this.itemView.findViewById(R.id.title);
//           this.itemView.setOnClickListener(clickListener);
//        }
//    }
}