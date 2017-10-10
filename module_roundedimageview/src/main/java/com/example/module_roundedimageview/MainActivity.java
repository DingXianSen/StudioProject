package com.example.module_roundedimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RoundImageView imageView;
    private RoundImageView imageView2;
    private Button bt;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (RoundImageView) findViewById(R.id.img1);
        imageView2= (RoundImageView) findViewById(R.id.img2);
        bt= (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时切换图片
                if (flag){
                    imageView2.setImageResource(R.mipmap.ic_launcher);
                    flag=false;
                }else {
                    imageView2.setImageResource(R.mipmap.img);
                    flag=true;
                }
            }
        });
    }
}