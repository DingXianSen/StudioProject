package com.example.lesson12_data_storage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 怪蜀黍 on 2016/11/21.
 */

public class ROMActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_result;
    private EditText et_edit;
    private CheckBox cb_append;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rom);

        tv_result = (TextView) findViewById(R.id.result);
        et_edit = (EditText) findViewById(R.id.edit);
        cb_append= (CheckBox) findViewById(R.id.append);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                String str = et_edit.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
//                获取流
                try {
//                    表示的是，如果复选框选中，则追加，否则覆盖，三元表达式
                    FileOutputStream fos = openFileOutput("my.txt",cb_append.isChecked()?MODE_APPEND: MODE_PRIVATE);
                    fos.write(str.getBytes());
//                等同于fow.write(str.getBytes("utf-8)")
//                刷新缓存，用户将缓存中的内容刷新到文件中
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
//            读取文件
            case R.id.read:
                try {
                    FileInputStream fis = openFileInput("my.txt");
                    byte[] b = new byte[1024];
//                临时存储
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                为了防止多存储
                    int len;
                    while ((len=fis.read(b)) != -1) {
                        bos.write(b,0,len);// 每次读取的长度
                    }
//                    写入到控件
                    tv_result.setText( new String(bos.toByteArray(),"utf-8"));
                    bos.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
