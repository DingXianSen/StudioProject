package com.example.asyncwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by 怪蜀黍 on 2016/11/15.
 */

public class SeconedActivity extends AppCompatActivity  implements View.OnClickListener{
    private ProgressBar pb;
    private ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        pb= (ProgressBar) findViewById(R.id.progress);
        image= (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
//调用的方法
         new Myasync().execute("地址");
    }

    /*****************************************************************************************************************************************/
//    第一步创建一个任务实例/

    /**
     * 注意三个参数分别对应的
     */
    class Myasync extends AsyncTask<String,Integer,Bitmap>{

        /**************************************************************************************************/
//        重写super.onPreExecute();方法，执行前的初始化操作，可以在这个方法中初始化控件
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            进度条设置为0
            pb.setProgress(0);
//            设置ImageView的初始图片
            image.setImageResource(R.mipmap.ic_launcher);
        }

        /**
         * 后台进行耗时操作
         * @param params   参数可以是很多，或者没有
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection conn =null;
            try {
                URL url=new URL(params[0]);//先创建访问地址
                //            创建链接
                conn=(HttpURLConnection)url.openConnection();
//                得到连接之后，设置请求方式，要注意，括号里的参数要全部大写
                conn.setRequestMethod("GET");
//                获取响应的结果吗，比如200（正常），404（错误），500
                int code= conn.getResponseCode();
                if(code==200){//code等于200表示数据访问正常
//                    获取内容返回输入流
                    InputStream is= conn.getInputStream();
//                    进行读取字节的临时存储
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] b=new byte[1024];
//                    定义len，读取的长度
                    int len;
//                    获取内容的总长度
                    int tatalLength= conn.getContentLength();
                    while ((len=is.read(b))!=-1){  //不等于-1表示还没有读完
//                        写入到bos中
                        bos.write(b,0,len);
//                        循环一次，更新一次,读到的占中的百分之多少
                        publishProgress(bos.size()*100/tatalLength);
//                        因为执行速度太快，所以让其强制休眠1s
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        将返回内容创建成字符串
                        b=bos.toByteArray();
//                        参数1：图片的字节数组，参数2：从第几字节开始，参数3：共读取多长
                        return BitmapFactory.decodeByteArray(b,0,b.length);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
//                把conn关闭
                if(conn!=null){
                    conn.disconnect();
                }
            }
            return null;
        }

        /**
         * 执行结果后的UI操作
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null){
//                读取成功后，进度条设置为100%
                pb.setProgress(100);
                image.setImageBitmap(bitmap);
            }else{
//                读取失败后，提示信息
                pb.setProgress(0);//进度条设置为0
//                提示信息
                Toast.makeText(SeconedActivity.this, "图片读取失败", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 执行中更新UI操作
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);//每次循环后的百分数
        }
    }
}
