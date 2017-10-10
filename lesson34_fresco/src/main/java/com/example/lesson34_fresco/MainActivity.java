package com.example.lesson34_fresco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private SimpleDraweeView sdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        sdv= (SimpleDraweeView) findViewById(R.id.sdv);
//        网络请求
//        sdv.setImageURI("https://www.baidu.com/img/bd_logo1.png");

//        本地assets
        sdv.setImageURI("asset://com.example.lesson34_fresco/aaaa.jpg");//或者
//        sdv.setImageURI("asset:///aaaa.jpg");

//        SDCard中的
//        sdv.setImageURI("file:///sdcard/Download/aaaa.jpg");

        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri("http://img3.imgtn.bdimg.com/it/u=801683617,2879332742&fm=21&gp=0.jpg")
                .setAutoPlayAnimations(true)
                .build();
        sdv.setController(controller);
    }

    @Override
    public void onClick(View v) {
    }
}
