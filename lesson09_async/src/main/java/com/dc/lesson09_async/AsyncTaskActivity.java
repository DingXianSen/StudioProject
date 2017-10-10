package com.dc.lesson09_async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 怪蜀黍 on 2016/11/15.
 */

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar pb;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        pb = (ProgressBar) findViewById(R.id.progress);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
        //点击时执行异步操作,execute参数可以多个，就是String...
        new MyTask().execute("http://192.168.26.21:8080/tomcat.png");
    }

    /**
     * 如果没有参数是，可以是Void
     * 一个任务实例只能执行一次
     */
    class MyTask extends AsyncTask<String, Integer, Bitmap> {
        /**
         * 执行前的初始化操作
         */
        @Override
        protected void onPreExecute() {
//            进度条设置为0
            pb.setProgress(0);
//            设置初始图片
            image.setImageResource(R.mipmap.ic_launcher);
        }

        /**
         * 后台进行耗时操作
         *
         * @param params
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... params) {
//            publishProgress();//给这个参数，会传递给onProgressUpdate方法
            HttpURLConnection conn = null;
            try {
//                params使用方法和数组方法一样
                URL url = new URL(params[0]);//先创建访问地址
//            Connection连接
                conn = (HttpURLConnection) url.openConnection();
//            得到链接之后,设置请求方式，并且注意，双引号中的必须大写,参数字母全部大写
                conn.setRequestMethod("GET");
//                上传在code之前
//            获取响应的结果码
                int code = conn.getResponseCode();
                if (code == 200) {//表示访问成功，和Webproject中的结果码一致，200，500，404
                    //                获取网络返回内容的输入流
                    InputStream is = conn.getInputStream();
//                  进行读取字节的临时存储
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] b = new byte[1024];
                    //                读取的长度
                    int len;
//                    获取内容长度
                    int totalLength = conn.getContentLength();
                    while ((len = is.read(b)) != -1) {//不等于-1表示还没有读到结尾

                        bos.write(b, 0, len);//写入的字节流中
                        publishProgress(bos.size() * 100 / totalLength);//读取一次，更新一次 bos.size(),读取的字节数
//                        因为太快
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //                将返回内容创建成字符串
                    b = bos.toByteArray();
//                    参数1：图片的字节数组，2：从第几字节开始，3：共读去多长
                    return BitmapFactory.decodeByteArray(b, 0, b.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
//                    断开连接
                    conn.disconnect();
//                    流管不管都可以
                }
            }
            return null;
        }

        /**
         * 执行后结果的UI操作
         *
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                pb.setProgress(100);//读取成功后，进度条为100
                image.setImageBitmap(bitmap);
            } else {
                pb.setProgress(0);
                Toast.makeText(AsyncTaskActivity.this, "图片读取失败", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 执行中UI更新操作
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);//每次while后，得到的百分数
        }
    }
}
