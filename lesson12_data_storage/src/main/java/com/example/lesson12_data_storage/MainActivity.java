package com.example.lesson12_data_storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit;
    private TextView tvShow;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        tvShow = (TextView) findViewById(R.id.show);
//        获取sp有三中方式
//        1、    参数1：产生的xml文件的名称，2：mode 其他应用访问此文件的访问权限/模式
        sp = getSharedPreferences("mtest", Context.MODE_PRIVATE);//私有的，Context在这一个类可以省略
//        2、    将调用者Activity的名称作为文件名，
//        sp=getPreferences(MODE_PRIVATE);
//        3、    将包名作为前缀，作为文件名，是在这个应用中唯一的名称
//        sp= PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                String str = edit.getText().toString();
                if (!str.matches(".+,.+")) {//如果不包含“,”
                    Toast.makeText(this, "格式应为\"key,value\"", Toast.LENGTH_SHORT).show();
                }
                String[] strs = str.split(",");
                SharedPreferences.Editor editor = sp.edit();//原本写入到的文本中的值，在editor中也存在
                editor.putString(strs[0], strs[1]);
//                editor.commit();//提交到文件
                editor.apply();//也可以提交，提交到文件并更新到Shared Preferences
//                其他操作，操作后也要提交
//                editor.clear();//清空
//                editor.remove("key");//移除某一个
                break;
            case R.id.read:
                str = edit.getText().toString();
//                参数1：key,参数2：key对应的值不存在时返回的默认值
                str = sp.getString(str, null);
                tvShow.setText(str);
                break;
        }
    }
}
