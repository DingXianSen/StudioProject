package com.example.lesson14_listview_json;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private List<User> users;

    private int j = 0;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter(this, getUsers());
        listView.setAdapter(adapter);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        int i = j;
        j = j + 20;
        for (; i < j; i++) {
            users.add(new User("hhhhh", "张三" + i, "100000000" + i));
        }
        return users;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            刷新
            case R.id.refresh:
                j = 0;//刷新的时候重新计数
                adapter.clear();
                adapter.addAll(getUsers());
                break;
//            加载更多
            case R.id.load_more:
//                adapter.addAll(getUsers());
                MyTask task=new MyTask();
                task.execute("http://192.168.230.2:8080/json.json");
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        private List<User> userList;//数据集合
        private LayoutInflater lif;

        public MyAdapter(Context context, List<User> users) {
            this.userList = users;
            this.lif = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return userList != null ? userList.size() : 0;
        }

        @Override
        public User getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            原来的写法
//            View v = lif.inflate(R.layout.layout_item, null);
//            TextView tvPhone = (TextView) v.findViewById(R.id.phone);
//            ImageView image = (ImageView) v.findViewById(R.id.image);
//            TextView tvTitle = (TextView) v.findViewById(R.id.title);
//            User u=getItem(position);
//            tvPhone.setText(u.getPhone());
//            tvTitle.setText(u.getNmae());
//            image.setImageResource(R.mipmap.ic_launcher);
//            优化后的写法
            ViewHolder holder;
            if (convertView == null) {
                convertView = lif.inflate(R.layout.layout_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            User u = getItem(position);
            holder.tvName.setText(u.getNmae());
            holder.tvPhone.setText(u.getPhone());
            holder.image.setImageResource(R.mipmap.ic_launcher);
            return convertView;
        }

        //刷新数据
        public void clear() {
            this.userList.clear();
            notifyDataSetChanged();
        }

        public void addAll(List<User> users) {
            this.userList.addAll(users);
//            通知数据集合改变了 引起数据视图刷新
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView tvName;
        TextView tvPhone;
        ImageView image;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            tvPhone = (TextView) itemView.findViewById(R.id.phone);
        }
    }


    //    网络访问
    class MyTask extends AsyncTask<String, Void, List<User>> {

        @Override
        protected List<User> doInBackground(String... params) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                int code = conn.getResponseCode();
                if (code == 200) {
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int len;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1) {
                        bos.write(b, 0, len);
                    }
                    String json = bos.toString("UTF-8");
                    Log.e("aaaa","========"+json);
                    Result<List<User>> result = JSON.parseObject(json, new TypeReference<Result<List<User>>>() {
                    }.getType());
                    if (result.getState().equals("ok")) {//json中的state
                        return result.getData();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            if (users!=null) {
                adapter.clear();
                adapter.addAll(users);
            }
        }
    }
}
