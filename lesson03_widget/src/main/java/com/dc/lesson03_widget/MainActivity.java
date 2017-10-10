package com.dc.lesson03_widget;

import android.graphics.Color;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edittext1;
    private TextView textview1;
    private int max = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview1 = (TextView) findViewById(R.id.text_view1);
        /*设置字符串*/
        //textview1.setText(String.valueOf(0));
        //textview1.setText(0+"");
        textview1.setText("11");
        textview1.setTextColor(Color.RED);
        /*将字符串解析成颜色*/
        textview1.setTextColor(Color.parseColor("#ff0000"));

        /*TypedValue.COMPLEX_UNIT_SP,表示的是sp            ,表示20sp*/
        textview1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        /*设置位置  ，  drawableLeft,Right   设置*/
//        textview1.setCompoundDrawables();


        /*第二种方法*/
        Button button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(l);
        /*第三种，先实现接口，重构方法*/
        button_1.setOnClickListener(this);








        /*EditText*/
        edittext1 = (EditText) findViewById(R.id.edittext_1);
        edittext1.getText().toString();
        //常用的方法
//        edittext1.setEnabled(); 设置是否可用
//        edittext1.setFocusable();设置是否可以获取焦点
//        edittext1.isEnabled(); 是否可用


        /*这个方法可以做到，填写字符是，数字可以改变的效果*/
//        添加文本监听
        edittext1.addTextChangedListener(watcher);

//
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox_1);
//        设置选中改变监听
        checkbox1.setOnCheckedChangeListener(changeLis);


    }

    /*********************************************************************************************************/
    private CompoundButton.OnCheckedChangeListener changeLis = new CompoundButton.OnCheckedChangeListener() {
//        当选中改变时，参数CheckBox继承了CompoundButton ：谁触发了监听，参数就是谁      ，参数2：当前是否选中
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };
    /*********************************************************************************************************/


    private TextWatcher watcher = new TextWatcher() {
        /*文本改变之前*/
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /*当文本改变的时候*/
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /*文本改变之后*/
        @Override
        public void afterTextChanged(Editable s) {
            //获取已经输入的的文字总数
            int currentCount = s.length();
            //计算还能输入多少个字符，记住，数字不能直接写
            textview1.setText(String.valueOf(max - currentCount));
        }
    };
    /*********************************************************************************************************/
    //创建点击监听对象
    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    /*第一种方法*/
    public void click(View v) {

    }

    @Override
    public void onClick(View v) {
        String text = edittext1.getText().toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
