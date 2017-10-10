package com.example.lesson11_menu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout flTop;
    private FrameLayout flBottom;


    private PopupMenu popupMenu;
    private Button btpopupMenu;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flTop = (FrameLayout) findViewById(R.id.a);
        flBottom = (FrameLayout) findViewById(R.id.b);
        btpopupMenu = (Button) findViewById(R.id.popmenu);
//        注册上下文
        registerForContextMenu(flTop);
        registerForContextMenu(flBottom);
    }

    //取消注册上下文
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(flTop);
        unregisterForContextMenu(flBottom);
    }

    /**
     * 重写此方法
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//         参数1：加载的menu，参数2：父容器
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.bdqn:
                break;
            case R.id.ytzl:
                break;
            case R.id.android:
                break;
            case R.id.web_ui:
                break;
            case R.id.java:
                break;

        }
        return true;//表示事件完成
    }

    /**
     * 上下文菜单
     *
     * @param menu
     * @param v        触发当前上下文事件的控件，被注册了上下文菜单的控件
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.a:
                getMenuInflater().inflate(R.menu.menu_main, menu);
                break;
            case R.id.b:
                getMenuInflater().inflate(R.menu.menu_b, menu);
                break;
        }
        // getMenuInflater().inflate(R.menu.menu_main, menu);
//        在初始化时进行注册
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popmenu:
                showPopupMenu();
                break;
            case R.id.pop_window:
                showPopupWindow(v);
                break;
            case R.id.bt:
            case R.id.bt2:
                Toast.makeText(this, "===" + ((Button) v).getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * popupWindow
     */
    private void showPopupWindow(View v) {
        if (popupWindow == null) {
            View view = getLayoutInflater().inflate(R.layout.layout_pop_window, null);
//            给加载出来的布局中的按钮添加点击事件
            view.findViewById(R.id.bt).setOnClickListener(this);
            view.findViewById(R.id.bt2).setOnClickListener(this);

//            popupWindow = new PopupWindow(view, 300, 200);//宽300xp,高200xp

//            测量控件需要的尺寸,控件宽高
//
            int size = View.MeasureSpec.makeMeasureSpec(1 << 30 - 1, View.MeasureSpec.AT_MOST);
            view.measure(size, size);
//            获取测量后的宽高
            int w=view.getMeasuredWidth();
            int h=view.getMeasuredHeight();
            popupWindow=new PopupWindow(view,w,h);
//            popupWindow.setContentView(view);//设置显示视图
//            popupWindow.setWidth(300);//设置宽
//            popupWindow.setHeight(200);//设置高
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));//设置背景
            popupWindow.setFocusable(true);//可获得焦点
            popupWindow.setOutsideTouchable(false);//屏幕外是否可以点击

        }
        popupWindow.showAsDropDown(v);//设置锚点
//        popupWindow.showAtLocation(flBottom, Gravity.CENTER,0,0);
    }

    /**
     * 创建
     */
    private void showPopupMenu() {
        if (popupMenu == null) {
            popupMenu = new PopupMenu(this, btpopupMenu);
//            API16
//            版本判断
//            Build.VERSION.SDK_INT正在运行的系统的版本
//            Build.VERSION_CODES.JELLY_BEAN  有史以来所有的系统的版本没那个  此为api16的名字
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                popupMenu.inflate(R.menu.menu_main);
            } else {
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
//                getMenuInflater().inflate(R.menu.menu_main,popupMenu.getMenu());
            }
            popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        }
//        显示
        popupMenu.show();
    }

    private PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            return false;
        }
    };
}
