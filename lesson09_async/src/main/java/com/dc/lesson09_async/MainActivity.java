package com.dc.lesson09_async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String str;
    private Object object;//当作锁
    private Executor executor;
    int a;

    private String u_name;
    private String u_pwd;
    private ImageView imageView;

    //获取文本框
    private EditText name;
    private EditText pwd;
    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        object = new Object();
        imageView = (ImageView) findViewById(R.id.image);

        /*******************************************/
        name= (EditText) findViewById(R.id.name);
        pwd= (EditText) findViewById(R.id.pwd);
        tv_info= (TextView) findViewById(R.id.text_info);

        //同时开多个线程
        //用到线程池Executors工具类Executor本身
//        创建出线程数为5个的线程池
        executor = Executors.newFixedThreadPool(5);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                /*=======================================================================================================================*/
//                new Thread() {
//                    @Override
//                    public void run() {
//                        //同步方法
//                        //同步代码块
//                        /**
//                         * 制定锁是谁，锁是object
//                         */
//                        synchronized (object) {
//                            for (int i = 0; i < 20; i++) {
//                                Log.e("aaa", "============Thread1============");
//                                try {
//                                    sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }.start();
//
//                new Thread() {
//                    @Override
//                    public void run() {
//                        synchronized (object) {
//                            for (int i = 0; i < 20; i++) {
//                                Log.e("aaa", "============Thread2============");
//                                try {
//                                    sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }.start();


/*=======================================================================================================================*/
//        new MyThread("Thread1").start();
//        new MyThread("Thread2").start();
/*=======================================================================================================================*/


//                调用线程池方法
                a++;
                executor.execute(r);
                break;
        }
    }
/*=======================================================================================================================*/
    /**
     * 静态方法，整个类都可以使用
     */
//    static class MyThread extends Thread{
//
//        public MyThread(){
//
//        }
//        public MyThread(String name){
//            super(name);
//        }
//
//        /**
//         * 同步方法
//         * @param name
//         */
////        注意如果没有加static会出现交互出现现象
//        private  static synchronized  void aa(String name){
//            for (int i = 0; i < 20; i++) {
//                Log.e("aaa", "============****============"+name);
//                try {
//                    sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        @Override
//        public void run() {
//            /**
//             * 参数getName()获取线程名称
//             */
//            aa(getName());
//        }
//    }
    /*=====.==================================================================================================================*/

    Runnable r = new Runnable() {
        @Override
        public void run() {
//            final int c = a;
//            Log.e("aaa", "==============start==============" + c);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.e("aaa", "==============end==============" + c);

              /*==========================================================网络访问==========================================================*/
//            网络访问
//            HttpClient6.0已经移除


            //获取用户名和密码
            u_name=name.getText().toString();
            u_pwd=pwd.getText().toString();
//            链接格式188L使用
            String params=String.format("name=%s&pwd=%s",u_name,u_pwd);
            HttpURLConnection conn = null;
            try {
//                http://192.168.72.2:8080/json.txt
                URL url = new URL("http://192.168.26.21:8080/AndroidWebService/android");//先创建访问地址
//            Connection连接
                conn = (HttpURLConnection) url.openConnection();
//            得到链接之后,设置请求方式，并且注意，双引号中的必须大写,参数字母全部大写
//                conn.setRequestMethod("GET");
 /*************************************************************************与java联系********************************************************************************/

                conn.setRequestMethod("POST");
//                上传在code之前
                conn.setDoOutput(true);//设置输出操作，默认为false，只要为true，就必须为POST，不能为GET
                conn.setDoInput(true);//默认为true
                conn.setConnectTimeout(10000);//设置连接超时时间 毫秒
                conn.setReadTimeout(10000);//读取超时时间
                /**上传*/
                conn.getOutputStream().write(params.getBytes("UTF-8"));//输出要进行上传的参数
/*************************************************************************与java联系********************************************************************************/
//            获取响应的结果码
                int code = conn.getResponseCode();
                if (code == 200) {//表示访问成功，和Webproject中的结果码一致，200，500，404
                    //                获取网络返回内容的输入流
                    InputStream is = conn.getInputStream();
                    /***************************图片处理*************************/

////                从流中加载位图Bitmap
//                  final  Bitmap bmp = BitmapFactory.decodeStream(is);
////                    //运行在UI线程
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            imageView.setImageBitmap(bmp);
//
//                        }
//                    });
                    /***************************图片处理*************************/
                    /***************************字节处理*************************/
    //                字节输出流,进行读取字节的临时存储
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] b=new byte[1024];
    //                读取的长度
                    int len;
                    while ((len=is.read(b))!=-1){//不等于-1表示还没有读到结尾
                        bos.write(b,0,len);//写入的字节流中
                    }
    //                将返回内容创建成字符串
                    str= new String (bos.toByteArray());
                    Log.e("aaaa","============================================================"+str);
//                    运行在主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_info.setText(str.toString());
                        }
                    });

                    //或者
//                    tv_info.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });
//                    延时2000毫秒后执行
//                    tv_info.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });
                    /***************************字节处理*************************/
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

        }
    };
}
