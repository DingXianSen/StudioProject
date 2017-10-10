package com.example.lesson25_recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 怪蜀黍 on 2016/12/15.
 */

/**
 * 装饰类
 */
public class MyDecotation extends RecyclerView.ItemDecoration {
    private Paint paint;
    private Drawable icon;

    //    初始化
    public MyDecotation(Context context) {
//        初始化画笔
        paint = new Paint();
//        设置画笔颜色为红色
        paint.setColor(Color.RED);
        icon = context.getResources().getDrawable(R.mipmap.icon_scan);//获取到图片
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        给每项之间添加红色间隔线
        int N = parent.getChildCount();//子项总数
        for (int i = 0; i < N; i++) {
//            获取第几个子项，根据i下标
            View child = parent.getChildAt(i);
            Rect rect = new Rect(child.getLeft(), child.getBottom(), child.getRight(), child.getTop() + 5);//位置
//           绘制矩形 参数1：要绘制的矩形，参数2：画笔
            c.drawRect(rect, paint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        在每项的右上角添加一个晓得图片
        int N = parent.getChildCount();//子项总数
        for (int i = 0; i < N; i++) {
//            获取第几个子项，根据i下标
            View child = parent.getChildAt(i);
//            每项的左和上
            int left = child.getLeft();
            int top = child.getTop();
////            获取图片本身的大小
//            Rect rect = icon.getBounds();
            icon.setBounds(left, top,
                    left + 80,
                    top + 80);
//            将图片绘制到画板上
            icon.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, 5);//添加了间隔
    }
}
