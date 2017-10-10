package com.dc.lesson07_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int TO_ABCTIVITY = 30;
    private TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvresult = (TextView) findViewById(R.id.result);
        Log.e("aaaa","==========onCreate========");
    }

    @Override
    public void onClick(View v) {
        //创建打开BActivity的意图
        Intent intent = new Intent(MainActivity.this, BActivity.class);
        //新建一个栈启动，注意finishAffinity只能关闭同栈的Activity
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //存放要传递到下一个界面的数据
        intent.putExtra("data",new String[]{"data1","data2","data3"});
        switch (v.getId()) {
            case R.id.to_bactivity:
                //Context方法，执行一个打开Activity的意图
                startActivity(intent);
                break;
            case R.id.to_bactivity_for_result:
                //Activity的方法，执行一个打开Activity的意图，并为了得到一些结果
                startActivityForResult(intent, TO_ABCTIVITY);
                break;
        }
    }

    /**
     * 当有跳转以startActivityForResult进行启动时，被启动界面关闭后调用
     *
     * @param requestCode   请求码
     * @param resultCode    结果码
     * @param data           数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TO_ABCTIVITY && resultCode == RESULT_OK && data != null) {
            //取出在Bactivity姐main存入的结果，数据类型要和设置时对应，参数是存放是的key
            String str = data.getStringExtra("str");
            Toast.makeText(this, "有数据返回===========" + str, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "有结果，无数据返回", Toast.LENGTH_SHORT).show();
        }
    }
}
