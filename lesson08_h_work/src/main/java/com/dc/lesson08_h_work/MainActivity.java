package com.dc.lesson08_h_work;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup rg;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private EditText etdata;
    private EditText ettype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        etdata = (EditText) findViewById(R.id.data);
        ettype = (EditText) findViewById(R.id.type);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = rg.getCheckedRadioButtonId();
        //如果为-1表示没有选中直接return
        if (id == -1) return;
//        两种方式：rg.findViewById(id);//父容器
//        或者findViewById(id);
        String action = ((RadioButton) rg.findViewById(id)).getText().toString();//获取选中项的字符串
        intent.setAction(action);

//        复选框
        if (cb1.isChecked()) {
            intent.addCategory(cb1.getText().toString());
        }
        if (cb2.isChecked()) {
            intent.addCategory(cb2.getText().toString());
        }
        if (cb3.isChecked()) {
            intent.addCategory(cb3.getText().toString());
        }

        //文本框
         String etData=etdata.getText().toString();
         String etType=ettype.getText().toString();

        /*需要注意，如果只写下边的两个if会报错，data和type有冲突*/
        if (!TextUtils.isEmpty(etData)&&!TextUtils.isEmpty(etType)){
            intent.setDataAndType(Uri.parse(etData),etType);
        }else if(!TextUtils.isEmpty(etData)){//如果不等于空//TextUtils工具类
//            Uri.parse(etData)将字符串解析成URI
            intent.setData(Uri.parse(etData));
        }else if(!TextUtils.isEmpty(etType)){
            intent.setType(etType);
        }
        /**
         * 第一个参数，intent，
         * 第二个参数，
         */
        List<ResolveInfo> infos= getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
//        判断集合是不是空集合
        if (infos!=null&&infos.size()>0){
            startActivity(intent);
        }else{
            Toast.makeText(this, "未找到Activity处理请求", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
