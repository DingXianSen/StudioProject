package com.dc.lesson07_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by 怪蜀黍 on 2016/11/10.
 */
public class BActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        //获取启动此界面的意图
        Intent intent = getIntent();
        //获取启动此界面时存放的数据
        String[] strArr = intent.getStringArrayExtra("data");
        Toast.makeText(this, "==="+ Arrays.toString(strArr), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_result:
                //设置活动操作后的返回结果
//                setResult(RESULT_OK);//操作成功
//                setResult(RESULT_CANCELED);//设置操作取消
//                setResult(40);//设置自定义结果
                Intent data = new Intent();
                //存放数据，以在组建间传递，可用值为，基本数据类型及其数组，字符串及其数据，序列化类及其数组
                data.putExtra("str", "设置的操作的结果");
                setResult(RESULT_OK, data);//设置成功，并返回一些数据
                break;
            case R.id.close:
                //关闭调用者
               // finish();
               ActivityM.exit();
                //关闭同栈的Activity
                //finishAffinity();
                break;
            case R.id.exit_applicaion:
                System.exit(0);
                break;
            case R.id.kill_process:
                //杀死进程,参数：要被杀死的进程的PID
                Process.killProcess(Process.myPid());
                break;
        }
    }
}
