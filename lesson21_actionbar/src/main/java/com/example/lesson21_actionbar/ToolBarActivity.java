package com.example.lesson21_actionbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

/**
 * Created by 怪蜀黍 on 2016/12/2.
 */

public class ToolBarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
//        设置标题
//        toolbar.setTitle("Title");
//        toolbar.setSubtitle("subtitle");//子标题
//        toolbar.setTitleTextColor(Color.WHITE);//标题颜色
//        toolbar.setSubtitleTextColor(Color.WHITE);//子标题颜色
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);//好像只有5.0版本的才可以使用
//        /        将toolbar作为ActionBar使用
//        setSupportActionBar(toolbar);
//        设置返回图标的点击事件
        toolbar.setNavigationOnClickListener(clickLis);
//        如果单独使用，不作为ActionBar时，加载菜单到ToolBar
//        toolbar.inflateMenu(R.menu.menu_main);

    }
    private View.OnClickListener clickLis=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
