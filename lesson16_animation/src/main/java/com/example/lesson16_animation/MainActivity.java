package com.example.lesson16_animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image;

    private Animation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /********************************************************帧动画********************************************************************/
        image = (ImageView) findViewById(R.id.image);
//        start开始播放
        ((AnimationDrawable) image.getBackground()).start();

//        getResources()，获取所有的资源
//        getResources().getString(R.string.app_name);//取出字符串
//        AnimationDrawable ad= (AnimationDrawable) getResources().getDrawable(R.drawable.anim_bg);//取出图片资源
//        image.setBackground(ad);
//        ad.start();


//        string.xml字符串资源
//        getResources().getString(R.string.app_name);
//          dimens.xml尺寸资源
//        getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
//        getResources().getStringArray();
//        getResources().getColor();
        /********************************************************帧动画********************************************************************/

//        加载xml动画
//        透明
        alpha= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        createAplhaAnim();
//        平移
        translate=AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        createTranslateAnim();

        set=AnimationUtils.loadAnimation(this,R.anim.anim_set);
    }
    /********************************************************透明********************************************************************/
    private void createAplhaAnim() {
//        参数1：起始参数2：到
        alpha = new AlphaAnimation(1, 0.5f);
        alpha.setDuration(2000);//设置时间
        alpha.setStartOffset(2000);//延迟两秒
        alpha.setFillAfter(true);
    }

    /********************************************************代码平移********************************************************************/
    private Animation translate;
    private void createTranslateAnim(){
        translate=new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,1,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,3);//
        translate.setDuration(2000);
//        差值器
        translate.setInterpolator(new OvershootInterpolator());
//        加速
    }
    /********************************************************代码平移********************************************************************/



    /********************************************************缩放********************************************************************/
    private Animation scale;
    private void createScale(){
        scale=new ScaleAnimation(0,3,0,3);
        scale.setDuration(2000);
    }
    /********************************************************缩放********************************************************************/

    /********************************************************旋转********************************************************************/
    private Animation rotate;
    private void createRotate(){
        rotate=new RotateAnimation(0,180);
        rotate.setDuration(2000);
    }
    /********************************************************旋转********************************************************************/

    private Animation set;
//    private void CreateSet(){
//        set=new AnimationSet(true,R.anim.anim_set);
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                image.startAnimation(alpha);
                break;
            case R.id.translation://平移
                image.startAnimation(translate);
                break;
            case R.id.scale://缩放
                createScale();
                image.startAnimation(scale);
                break;
            case R.id.rotate://旋转
                createRotate();
                image.startAnimation(rotate);
                break;
            case R.id.set://集合
                image.startAnimation(set);
                break;
            case R.id.tiao:
                Intent intent=new Intent(this,AnimatorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
//                应用场景
//                listview    layoutAnimation属性，效果
//                viewgroup    属性动画，容器控件
//                LinearLayout ll;
//                ll.setLayoutTransition();

                break;
        }
    }
}
