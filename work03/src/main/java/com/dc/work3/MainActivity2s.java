package com.dc.work3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 怪蜀黍 on 2016/11/4.
 */

public class MainActivity2s extends AppCompatActivity {
    private CheckBox checkboxall;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;


    private TextView textviewinfo;
    private List<String> checkedStr;




    //操作取消一个时，全选取消，这个变量是是否是用户点击
    private boolean checkFoUser=true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        checkboxall = (CheckBox) findViewById(R.id.checkboxall);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkbox6);
        textviewinfo = (TextView) findViewById(R.id.textviewinfo);

        checkBox1.setOnCheckedChangeListener(changeListener);
        checkBox2.setOnCheckedChangeListener(changeListener);
        checkBox3.setOnCheckedChangeListener(changeListener);
        checkBox4.setOnCheckedChangeListener(changeListener);
        checkBox5.setOnCheckedChangeListener(changeListener);
        checkBox6.setOnCheckedChangeListener(changeListener);
        checkboxall.setOnCheckedChangeListener(changeListener);

        checkedStr=new LinkedList<>();

    }
    public CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.checkbox1:
                case R.id.checkbox2:
                case R.id.checkbox3:
                case R.id.checkbox4:
                case R.id.checkbox5:
                case R.id.checkbox6:
                    String str=buttonView.getText().toString();
                    if(isChecked){
                        checkedStr.add(str);
                    }else {
                        checkedStr.remove(str);
                    }
//                    checkFoUser=false;
                    checkboxall.setOnCheckedChangeListener(null);
                    if(checkBox1.isChecked()&&checkBox2.isChecked()&&checkBox3.isChecked()&&checkBox4.isChecked()&&checkBox5.isChecked()&&checkBox6.isChecked()){
                        //表示如果都选中时，把全选按钮也选中
                        checkboxall.setChecked(true);
                    }else {
                        //否则就全选按钮去不选中，但是这样会触发checkboxall的监听，会把所有的都取消掉
                        checkboxall.setChecked(false);
                    }
//                    checkFoUser=true;
                    /*第二种方法*/
                    checkboxall.setOnCheckedChangeListener(changeListener);
                    break;
                case R.id.checkboxall:
                    if(checkFoUser) {
                        checkBox1.setChecked(isChecked);
                        checkBox2.setChecked(isChecked);
                        checkBox3.setChecked(isChecked);
                        checkBox4.setChecked(isChecked);
                        checkBox5.setChecked(isChecked);
                        checkBox6.setChecked(isChecked);
                        break;
                    }
            }
            StringBuffer sb=new StringBuffer();
            for(String str:checkedStr){
                sb.append(str+",");
            }
            if(sb.length()>0){
                //设置长度为长度-1,去除最后的“，”
                sb.setLength(sb.length()-1);
            }
            textviewinfo.setText("已选择："+sb.toString());
        }
    };

}