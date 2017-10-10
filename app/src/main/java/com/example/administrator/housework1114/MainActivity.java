package com.example.administrator.housework1114;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loadbt;
    private EditText urlet;
    private ProgressBar loadprogress;
    private ImageView loadimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadbt = (Button) findViewById(R.id.bt1);
        loadbt.setOnClickListener(onClickListener);
        urlet = (EditText) findViewById(R.id.et1);
        loadprogress = (ProgressBar) findViewById(R.id.pb1);
        loadimg = (ImageView) findViewById(R.id.iv2);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = urlet.getText().toString();
            new LoadImgTask().execute(url);
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyInfo.class);
        startActivity(intent);
    }

    class LoadImgTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            loadprogress.setProgress(0);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection conn=null;
            try {
                //获取地址
                URL url = new URL(params[0]);
                //打开连接
                 conn = (HttpURLConnection) url.openConnection();
                //设置方法
                conn.setRequestMethod("GET");
                //获取返回的结果
                int result = conn.getResponseCode();
                if (result == 200) {
                    //返回的输入流
                    InputStream is = conn.getInputStream();
                    //输出
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] b = new byte[1024];
                    //每次读取的长度
                    int len;
                    while ((len = is.read(b)) != -1) {
                        bos.write(b, 0, len);
                        publishProgress(bos.size() * 100 / conn.getContentLength());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    b = bos.toByteArray();
                    return BitmapFactory.decodeByteArray(b,0,b.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap!=null){
                loadprogress.setProgress(100);
                loadimg.setImageBitmap(bitmap);
            }else {
                loadprogress.setProgress(0);
                Toast.makeText(MainActivity.this,"加载图片失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            loadprogress.setProgress(values[0]);
        }
    }
}
