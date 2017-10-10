package com.example.work16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button bt_sport;
    private Button bt_photoRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
    }
//平移
    private Animation translate;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sport:
//                抖动
                translate=new TranslateAnimation(
                        Animation.ZORDER_TOP,0,
                        Animation.ZORDER_TOP,0.1f,
                        Animation.ZORDER_BOTTOM,0,
                        Animation.ZORDER_BOTTOM,0.1f);
                translate.setDuration(5);//间隔
                translate.setRepeatMode(Animation.REVERSE);//重复模式，反向
                translate.setRepeatCount(10);//重复次数10
                imageView.startAnimation(translate);
                break;
            case R.id.photoRun:
//                先x轴平移，再y轴平移
                translate= AnimationUtils.loadAnimation(this,R.anim.anim_translate_x);//x轴平移
               translate.setFillAfter(true);
                imageView.startAnimation(translate);
                translate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.e("aaaa","==========setAnimationListener=====================onAnimationStart=============="+animation.toString());
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        animation.setFillAfter(true);
                        Log.e("aaaa","==========setAnimationListener=====================onAnimationEnd=============="+animation.toString());
                        if (animation.getFillAfter()){
                                    translate=AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_translate_y);
                                    translate.setFillAfter(true);
                            imageView.startAnimation(translate);
                            Log.e("aaaa","==========setAnimationListener=====================isFillEnabled=============="+translate.toString());
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.e("aaaa","==========setAnimationListener=====================onAnimationRepeat=============="+animation.toString());
                    }
                });
                break;
            case R.id.next:
                Intent intent=new Intent(this,SeconedActivity.class);
                startActivity(intent);
                break;
        }
    }
}
