package com.example.lesson20_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView image;
    private ProgressBar progress;
    private Button bt;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
//            switch (msg.what){
//                case 10:
////                    进度更新
//                    int pb=msg.arg1*100/msg.arg2;
////                    文件大小的转化，转换为3KB,3MB...
//                    String currsize= Formatter.formatFileSize(MainActivity.this,msg.arg1);
//                    String totlesize= Formatter.formatFileSize(MainActivity.this,msg.arg2);
//                    bt.setText(String.format("%s/%s",currsize,totlesize));
//                    progress.setProgress(pb);
//                    break;
//                case 12:
////                    进度值
//                    progress.setProgress(100);
////                    下载完成，进行数据操作
//                    bt.setText("加载完成");
//                    image.setImageBitmap((Bitmap) msg.obj);
//                    break;
//            }
            return  false;
        }
    });//在哪处理消息，在哪定义handler
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= (ImageView) findViewById(R.id.image);
        progress= (ProgressBar) findViewById(R.id.progress);
        bt= (Button) findViewById(R.id.bt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                bt.setEnabled(false);//点击一次
//                load();
//                用HttpThread类
               new HttpThread(new HttpThread.OnLoadListener() {
                    @Override
                    public void onUpdate(int current, int total) {
//                        进度更新
                        int pb=current*100/total;
//                    文件大小的转化，转换为3KB,3MB...
                        String currsize= Formatter.formatFileSize(MainActivity.this,current);
                        String totlesize= Formatter.formatFileSize(MainActivity.this,total);
                        bt.setText(String.format("%s/%s",currsize,totlesize));
                        progress.setProgress(pb);
                    }

                    @Override
                    public void onCommplete(Bitmap bmp) {
//                    进度值
                        progress.setProgress(100);
//                    下载完成，进行数据操作
                        bt.setText("加载完成");
                        image.setImageBitmap(bmp);
                    }
                }).start();
                break;
        }
    }

    private void load() {
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try {
                    URL url=new URL("https://www.baidu.com/img/bd_logo1.png") ;
                    conn=(HttpURLConnection)url.openConnection();
                    int code=conn.getResponseCode();
                    if (code==200){
                        InputStream is=conn.getInputStream();
                        ByteArrayOutputStream bos=new ByteArrayOutputStream();
                        byte[] b=new byte[1024];
                        int total=conn.getContentLength();//获取总长度
                        int curr=0;
                        int len;
                        while ((len=is.read(b))!=-1){
                            curr+=len;
    //                        传递消息
                            Message message= handler.obtainMessage();
                            message.what=10;//进行上边的匹配，看执行那个操作
                            message.arg1=curr;
                            message.arg2=total;
    //                        message.sendToTarget();//发送消息，或者
                            handler.sendMessage(message);
                            bos.write(b,0,len);
                            sleep(50);
                        }
                        b=bos.toByteArray();
                        Bitmap bmp= BitmapFactory.decodeByteArray(b,0,b.length);//转换为图片
                        Message msg=handler.obtainMessage();
                        msg.what=12;
                        msg.obj=bmp;
                        msg.sendToTarget();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (conn!=null){
                        conn.disconnect();//关闭链接
                    }
                }
            }
        }.start();
    }
}
