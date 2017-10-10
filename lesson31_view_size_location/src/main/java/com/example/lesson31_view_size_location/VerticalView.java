package com.example.lesson31_view_size_location;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by 怪蜀黍 on 2016/12/27.
 */

public class VerticalView extends ViewGroup {
    private int width;
    private int height;

    public VerticalView(Context context) {
        super(context);
    }

    public VerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int N = getChildCount();
        int maxWidth = 0;
        int sumHeight = getPaddingTop() + getPaddingBottom();//上下内边距
        for (int j = 0; j < N; j++) {
            View child = getChildAt(j);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int lm = lp.leftMargin;
            int tm = lp.width;
            int bm = lp.bottomMargin;
            int cw = child.getMeasuredWidth();//测量宽
            int ch = child.getMeasuredHeight();//测量高
            child.layout(getPaddingLeft() + lm
                    , sumHeight + tm, getPaddingLeft() + lm + cw, sumHeight + tm + ch);
            sumHeight = sumHeight + tm + bm + ch;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        测量所有子控件的显示尺寸
        measureChildren(widthMeasureSpec, heightMeasureSpec);//测量所有的子控件的大小
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec);
//
        measureSize();
        if (wmode != MeasureSpec.AT_MOST) {
            width = wsize;
        }
        if (hmode != MeasureSpec.AT_MOST) {
            height = hsize;
        }
        setMeasuredDimension(width, height);
    }

    private void measureSize() {
        int N = getChildCount();
        int maxWidth = 0;
        int sumHeight = getPaddingTop() + getPaddingBottom();//上下内边距
        for (int i = 0; i < N; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int lm = lp.leftMargin;
            int tm = lp.width;
            int rm = lp.rightMargin;
            int bm = lp.bottomMargin;
            int cw = child.getMeasuredWidth();//测量宽
            int ch = child.getMeasuredHeight();//测量高
            if (maxWidth < lm + rm + cw) {
                maxWidth = lm + rm + cw;//最大宽度
            }
            sumHeight = sumHeight + tm + bm + ch;
        }
        width = getPaddingLeft() + getPaddingRight() + maxWidth;
        height = sumHeight;
    }

    @Override//子控件在添加时，所使用的LayoutParams有以下三个方法决定其类型
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {

        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
