package com.dc.work3;

import android.content.Intent;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText edittextphone;
    private EditText edittextinfo;
    private TextView textviewmax;
    private Button buttonyes;
    private int max=140;

    //进入第二个界面
    private Button button2;


    //定义全局变量接受输入的信息
    private String phone;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //先定义Max：的文本
        textviewmax=(TextView)findViewById(R.id.textviewmax);
        textviewmax.setText("Max:"+String.valueOf(max));
        /*首先实现记录最大数，并返回到界面*/
        edittextinfo=(EditText)findViewById(R.id.edittextinfo);
        edittextinfo.addTextChangedListener(watcher);


        /*然后实现发送按钮的功能*/
        edittextphone=(EditText)findViewById(R.id.edittextphone);
        //按钮的功能
        buttonyes=(Button)findViewById(R.id.buttonyes);
        buttonyes.setOnClickListener(click);

        //进入第二个界面
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(click2);
    }
    /*=======================================================================================================================*/
    //进入第二个界面
    public View.OnClickListener click2=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,MainActivity2s.class);
            startActivity(intent);
        }
    };
/*=======================================================================================================================*/
    //写最大数减少的方法
    public TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int count=s.length();
            textviewmax.setText("Max:"+String.valueOf(max-count));
        }
    };
    /*=======================================================================================================================*/
    //写按钮的方法
    public View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //拿到phone的输入信息
            phone=edittextphone.getText().toString();
            //拿到info的输入信息
            info=edittextinfo.getText().toString();
            Toast.makeText(MainActivity.this,phone+info , Toast.LENGTH_LONG).show();
        }
    };
}
