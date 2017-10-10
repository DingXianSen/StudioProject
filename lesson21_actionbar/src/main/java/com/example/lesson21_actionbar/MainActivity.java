package com.example.lesson21_actionbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private CheckBox display, displayHome, displayTitle, setTitle, setSubtitle, setLogo, setIcon, asUp;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (CheckBox) findViewById(R.id.display);
        displayHome = (CheckBox) findViewById(R.id.display_home);
        displayTitle = (CheckBox) findViewById(R.id.display_title);
        setTitle = (CheckBox) findViewById(R.id.set_title);
        setSubtitle = (CheckBox) findViewById(R.id.set_subtitle);
        setLogo = (CheckBox) findViewById(R.id.set_logo);
        setIcon = (CheckBox) findViewById(R.id.set_icon);
        asUp = (CheckBox) findViewById(R.id.as_up);
//        获取V7中的ActivityBar
        actionBar = getSupportActionBar();

        display.setOnCheckedChangeListener(changeListener);
        displayHome.setOnCheckedChangeListener(changeListener);
        displayTitle.setOnCheckedChangeListener(changeListener);
        setTitle.setOnCheckedChangeListener(changeListener);
        setSubtitle.setOnCheckedChangeListener(changeListener);
        setLogo.setOnCheckedChangeListener(changeListener);
        setIcon.setOnCheckedChangeListener(changeListener);
        asUp.setOnCheckedChangeListener(changeListener);

//        设置home作为返回功能时的图标
//        actionBar.setHomeAsUpIndicator();
    }

    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
//        @OnCheckedChanged({R.id.display})
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                switch (buttonView.getId()) {
                    case R.id.display:
                        actionBar.hide();//隐藏
                        break;
                    case R.id.display_home:
                        actionBar.setDisplayShowHomeEnabled(false);//隐藏
                        break;
                    case R.id.display_title:
                        actionBar.setDisplayShowTitleEnabled(false);
                        break;
                    case R.id.set_title:
                        actionBar.setTitle("title");//空字符串
                        break;
                    case R.id.set_subtitle:
                        actionBar.setSubtitle("subtitle");
                        break;
                    case R.id.set_logo:
                        actionBar.setLogo(R.mipmap.ic_launcher);
                        break;
                    case R.id.set_icon:
                        actionBar.setIcon(R.mipmap.ic_launcher);
                        break;
                    case R.id.as_up:
                        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
                        break;
                }
            } else {
                switch (buttonView.getId()) {
                    case R.id.display:
                        actionBar.show();//显示
                        break;
                    case R.id.display_home:
                        actionBar.setDisplayShowHomeEnabled(true);//显示
                        break;
                    case R.id.display_title:
                        actionBar.setDisplayShowTitleEnabled(true);
                        break;
                    case R.id.set_title:
                        actionBar.setTitle("");//空字符串
                        break;
                    case R.id.set_subtitle:
                        actionBar.setSubtitle("");
                        break;
                    case R.id.set_logo:
                        actionBar.setLogo(null);
                        break;
                    case R.id.set_icon:
                        actionBar.setIcon(null);
                        break;
                    case R.id.as_up:
                        actionBar.setDefaultDisplayHomeAsUpEnabled(false);
                        break;
                }
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        查找一个菜单项
        MenuItem item = menu.findItem(R.id.search);//id是菜单布局中的id
//        获取ActionView——>对应属性actionViewClass
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("请输入查找的内容。。。");
//        searchView.setOnQueryTextFocusChangeListener();//焦点监听
        searchView.setOnQueryTextListener(onQueryTextListener);//文本监听


//        第二菜单项 分享功能
//        MenuItem menuitem= (MenuItem) findViewById(R.id.share);
////        app包中的menuitem.getActionProvider()
//        ShareActionProvider share= (ShareActionProvider) MenuItemCompat.getActionProvider(menuitem);//这是v7的
//
//        File file=new File(Environment.getExternalStorageDirectory(),"Download/aaaaa.jpg");
////        设置操作为要发送一个内容
//        Intent intent=new Intent(Intent.ACTION_SEND);//意图,发送Intent.ACTION_SEND
////        intent.setType("text/plain");//设置要分享的内容类型，这样效果有电话和短信
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));//EXTRA_STREAM文件，分享文件
//        intent.putExtra(Intent.EXTRA_TITLE, "一个你懂的文件");//EXTRA_STREAM文件
//        intent.putExtra(Intent.EXTRA_SUBJECT, "你想要的文件");//邮件的形式发送
//        intent.putExtra(Intent.EXTRA_TEXT, "lalalalalalalalalala");//内容
//
////        设置要分享的内容
//        share.setShareIntent(intent);
        return true;
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override //当前提交查询时触发
        public boolean onQueryTextSubmit(String query) {
            if ("toolbar".equals(query)){
                Intent in=new Intent(MainActivity.this,ToolBarActivity.class);
                startActivity(in);
            }
            Toast.makeText(MainActivity.this, "onQueryTextSubmit===="+query+"====query", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override//文本改变时监听
        public boolean onQueryTextChange(String newText) {
            Toast.makeText(MainActivity.this, "onQueryTextChange===="+newText+"====newText", Toast.LENGTH_SHORT).show();
            return true;
        }
    };
}