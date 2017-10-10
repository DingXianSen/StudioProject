package com.example.lesson_30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by 怪蜀黍 on 2016/12/26.
 */

//模拟刮卡，效果，初始化灰色
public class GCard extends ImageView {
    private Paint paint;//画笔
    private Bitmap bmp;
    private Canvas canvas;
    private Path path;//路径对象，手指滑动的路径

    //    继承之后，重写构造方法，第四个是高版本
    public GCard(Context context) {
        this(context, null);//让自己调用第二个构造方法
    }

    public GCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);//第二个调用第三个构造方法
    }

    public GCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.RED);//颜色
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        paint.setStrokeCap(Paint.Cap.ROUND);//笔头圆角
        paint.setStrokeJoin(Paint.Join.ROUND);//圆角连接
//        这句话可实现清除
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));//在这句话之前的叫dst,在这句话之后的叫src,模式为清除模式
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));//绘制

        path = new Path();
    }

    /**
     * 测量尺寸大小
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == 0 || h == 0) return;
        if (bmp == null || (bmp != null && bmp.getWidth() != w)) {
//            创建空的图片
            bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//            测试，覆盖的改为一个图片
//          Bitmap  bmp2=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//            Log.e("aaaa","===============bmp2"+bmp2.getWidth());
//            bmp=Bitmap.createBitmap(bmp2);
            canvas = new Canvas(bmp);
//            canvas.drawColor(Color.WHITE);
//            canvas.drawColor(Color.TRANSPARENT);//透明
            canvas.drawColor(Color.GRAY);//灰色
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmp, 0, 0, null);

//不用这些代码可以实现刮卡
        /**************************************
//        paint.reset();
//        Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//        paint.setShader(new BitmapShader(bmp, Shader.TileMode.MIRROR,Shader.TileMode.REPEAT));//着色
////        canvas.drawRect(0,0,300,300,paint);
//        paint.setAntiAlias(true);//抗锯齿
//        canvas.drawCircle(bmp.getWidth()/2,bmp.getHeight()/2,bmp.getWidth()/3,paint);
         *****************************************/
    }

    private float lastX;
    private float lastY;

    @Override//触摸事件
    public boolean onTouchEvent(MotionEvent event) {
//        获取手指触碰点相对于当前控件的左上角的坐标
        float x = event.getX();
        float y = event.getY();
//        获取事件类型，判断是什么操作
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下的时候
                path.reset();//重置
                path.moveTo(x, y);
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://滑动的时候
//                注释掉这会比较灵敏
//                float dx = Math.abs(x - lastX);
//                float dy =  Math.abs(x - lastY);
//                if (dx > 10 && dy > 10) {//判断移动的距离，但是如果加上这个的话，鼠标指针会有间隔，所以会显得不灵敏
                path.lineTo(x, y);
                canvas.drawPath(path, paint);
                invalidate();//废弃，废弃后会调用onDrow更新显示
//                }
                break;
            case MotionEvent.ACTION_UP://抬起的时候
                break;
        }
        return true;
    }


    /**
     * 掌握控制整个控件的显示
     *
     * @param canvas, 使用的时候把s改为onDraw
     */
    protected void s(Canvas canvas) {
//        首先定义全局画笔，然后在构造方法中初始化

        paint.setStyle(Paint.Style.FILL);//设置样式FILL填充   STROKE边框//1
//        用画板进行绘制
        Rect r = new Rect(10, 10, 100, 100);//3
        canvas.drawRect(r, paint);//2

//        画一个空心边框
        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(255, 0, 255, 0);
        paint.setStrokeWidth(5);//设置边框的宽度
        r = new Rect(110, 10, 160, 60);
        canvas.drawRect(r, paint);


//        绘制圆角矩形
        RectF rf = new RectF(10, 110, 100, 200);
        canvas.drawRoundRect(rf, 20, 10, paint);//中间的两个参数，分别对应的是x和y的圆角大小

        paint.setStyle(Paint.Style.FILL);
//        绘制扇形,第三个参数，连接中心点，如果绘制实心，修改画笔
        canvas.drawArc(rf, 0, 100, true, paint);//参数，第二个起始角度，第三个，扇形的角度
//        不链接中心点
        canvas.drawArc(rf, 180, 90, false, paint);

        paint.setColor(Color.BLUE);
//        绘制圆形,第一和第二中心点，第三个参数，半径
        canvas.drawCircle(160, 160, 50, paint);

//        绘制线
        paint.setStrokeCap(Paint.Cap.ROUND);//这句话，设置线的两段为圆形
        canvas.drawLine(110, 10, 160, 60, paint);


//        绘制多条线
        canvas.drawLines(new float[]{0, 0, 0, 200, 200, 200, 200, 0}, paint);

//        绘制图片,画笔可以设置为空
//        加载图片
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        paint.setColorFilter(new LightingColorFilter(Color.parseColor("#ff0000"), 0));//颜色过滤器，使用这一个还可以保留眼睛
        canvas.drawBitmap(bmp, 170, 170, paint);


        RectF dst = new RectF(270, 270, 420, 420);
        Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight() / 2);
        canvas.drawBitmap(bmp, src, dst, null);

//        绘制路径
        Path path = new Path();
        paint.setStyle(Paint.Style.STROKE);
//        首先移动到指定点
        //绘制正方体的,moveTo是设置起始点，lineTo是线段到那一个点
        path.moveTo(10, 270);//起点
        path.lineTo(160, 270);//
        path.lineTo(160, 420);//
        path.lineTo(10, 420);
        path.lineTo(10, 270);
        path.lineTo(60, 220);
        path.lineTo(210, 220);
        path.lineTo(160, 270);
        path.moveTo(210, 220);
        path.lineTo(210, 370);
        path.lineTo(160, 420);
        path.addCircle(160, 420, 20, Path.Direction.CW);//在正方体的一角添加一个圆形的标记物
        canvas.drawPath(path, paint);


//        绘制文字
//        还可以设置画笔的样式，填充还是边框，边框的话，就是空心字体
        paint.setTextSize(100);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(1);
        canvas.drawText("是不是给你惯的", 10, 450, paint);

    }
}









