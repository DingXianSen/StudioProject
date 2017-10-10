package com.example.lesson31_view_size_location;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 怪蜀黍 on 2016/12/27.
 */

public class MyTextView extends View {
    private String text = "hehehehehehehehehe";
    private int size = 30;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        宽度模式和高度模式
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
        int width = 100;
        int height = 100;
        if (wmode == MeasureSpec.AT_MOST) {
            width = text.length() * size + 10;
        }
        if (hmode == MeasureSpec.AT_MOST) {
            height = size + 10;
        }
        setMeasuredDimension(width, height);//设置控件显示的尺寸值
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawText(text, 5, size + 5, paint);
    }
}
