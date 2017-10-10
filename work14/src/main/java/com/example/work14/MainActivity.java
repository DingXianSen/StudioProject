package com.example.work14;

import android.content.Context;
import android.media.Image;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv_data;
    private MyAdapter adapter;
//    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_data = (ListView) findViewById(R.id.lvdata);
        adapter = new MyAdapter(this, getUsers());
        lv_data.setAdapter(adapter);
        /****************************图片的加载方法**********************************/
//        load图片的地址
//        placeholder加载中显示的图片
//        error加载失败后显示的图片
//        into 给图片设置控件
 /*       Picasso picasso = Picasso.with(this);
        picasso.load(Uri.parse(""))//图片路径
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img);*/
        /****************************图片的加载方法**********************************/
    }

    public List<User> getUsers() {
        List<User> us = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            us.add(new User("数据1", 14 + i, "信息" + i));
        }
        return us;
    }

    @Override
    public void onClick(View v) {
//        点击加载按钮时
        MyTask task = new MyTask();
        task.execute("http://192.168.230.2:8080/json.json");//访问地址
    }

    /************************************************************************
     * MyAdapter
     *****************************************************************************/
    //    创建MyAdapter继承BaseAdapter
    class MyAdapter extends BaseAdapter {
        private LayoutInflater lif;
        //    定义数据集合
        private List<User> userList;
        private Picasso picasso;

        public MyAdapter(Context context, List<User> users) {
            this.userList = users;
            this.lif = LayoutInflater.from(context);
            picasso = Picasso.with(context);
        }

        @Override
        public int getCount() {
            return userList != null ? userList.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            优化，创建ViewHolder类
            ViewHolder holder;
            if (convertView == null) {
                convertView = lif.inflate(R.layout.activity_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            User u = (User) getItem(position);
            holder.tvName.setText(u.getName());
            holder.tvAge.setText(u.getAge().toString());
            holder.tvMessage.setText(u.getMessage());
//            加载服务器图片
//            holder.im_image.setImageResource();
            picasso.load(u.getPhotoUri())
                    .into(holder.im_image);
            return convertView;
        }

        //        刷新数据方法
        public void clear() {
            this.userList.clear();
            notifyDataSetChanged();
        }

        //        绑定数据
        public void AddAll(List<User> users) {
            this.userList = users;
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView tvName, tvAge, tvMessage;
        ImageView im_image;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAge = (TextView) itemView.findViewById(R.id.age);
            tvMessage = (TextView) itemView.findViewById(R.id.message);
            im_image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    /************************************************************************
     * 网络请求
     *****************************************************************************/
    class MyTask extends AsyncTask<String, Void, List<User>> {
        @Override
        protected List<User> doInBackground(String... params) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
//            获得网络返回请求码
                int code = conn.getResponseCode();
                if (code == 200) {
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int len;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1) {
                        bos.write(b, 0, len);
                    }
                    String json = bos.toString("utf-8");
                    Log.e("aaa", "=================json================json" + json);
                    Result<List<User>> result = JSON.parseObject(json, new TypeReference<Result<List<User>>>() {
                    }.getType());

                    return result.getData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            if (users != null) {
                adapter.clear();
                adapter.AddAll(users);
            }
        }
    }
}
