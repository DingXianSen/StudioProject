package com.example.work10;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button btn_day;
//                      城市          温度              风力      湿度          日出          建议
    private TextView day_city,day_temperature,day_wind,day_humidity,day_sunrise,advise;

    private String str;

    private LayoutInflater lif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_day= (Button) findViewById(R.id.btn_day);

        day_city= (TextView) findViewById(R.id.city);   //城市
        day_temperature= (TextView) findViewById(R.id.temperature); //温度
        day_wind= (TextView) findViewById(R.id.wind);   //风力
        day_humidity= (TextView) findViewById(R.id.humidity);   //湿度
        day_sunrise= (TextView) findViewById(R.id.sunrise); //日出
        advise= (TextView) findViewById(R.id.advise);   //建议

    }
    /**
     * 点击时间，点击是获取天气信息
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /*创建任务实例*/

    /**
     * 没有实现进度条，所以直接重写一个后台耗时操作，和修改操作，另外返回的数据是xml
     */
    class Getweather extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn=null;
            try {
                URL url=new URL(params[0]);//先创建访问地址，这个地址是onClick点击时，传递的地址，也就是访问天气的地址
                conn= (HttpURLConnection) url.openConnection();//创建连接，因为url.openConnection()返回的是不带HTTP协议的，所以请值类型转换
                conn.setRequestMethod("GET");//打开连接之后，设置请求方式
//                获取结果码
                int code=conn.getResponseCode();
//                如果访问成功
                if (code==200){
                    InputStream is=conn.getInputStream();//获取内容返回输入流
//                    将读取到的字符串进行临时存储
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] b=new byte[1024];
                    int len;
                    int totalLength=conn.getContentLength();
                    while ((len=is.read(b))!=-1){//len记录每次读取的长度
                        bos.write(b,0,len);
//                        b=bos.toByteArray();//将返回内容创建成字符串
                        str= new String (bos.toByteArray());
                        return str;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class MyAdapter extends BaseAdapter{
        public MyAdapter() {
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
