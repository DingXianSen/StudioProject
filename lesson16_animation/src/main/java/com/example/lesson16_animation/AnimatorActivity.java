package com.example.lesson16_animation;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewAnimator;

/**
 * Created by 怪蜀黍 on 2016/11/25.
 */

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener{
    private ValueAnimator valueAnimator;
    private ObjectAnimator objectAnimator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

//        使用要求：可以使用任何对象，不只限制于控件
//        要控制的属性必须有直接对属性修改的getter/setter方法


        valueAnimator=(ValueAnimator) AnimatorInflater.loadAnimator(this,R.animator.animator_value_rotate);
//        valueAnimator=ValueAnimator.ofInt(Color.BLUE,Color.RED,Color.GRAY);
//        添加值更新监听
        valueAnimator.addUpdateListener(listener);
//      对象动画
        objectAnimator= (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animator_object_translate);

//        代码创建
//        objectAnimator=ObjectAnimator.ofFloat(findViewById(R.id.translate),"translationY",50);
//        objectAnimator.setDuration(1000);
//        指定要操作的对象
        objectAnimator.setTarget(findViewById(R.id.translate));
    }
    private ValueAnimator.AnimatorUpdateListener listener=new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
//            取出更新变化的值
            float value= (float) valueAnimator.getAnimatedValue();
//            设置控件属性变化
            findViewById(R.id.rotate).setRotation(value);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotate:
//                启动动画
                valueAnimator.start();
                break;
            case R.id.translate:
                objectAnimator.start();
                break;
        }
    }
}
