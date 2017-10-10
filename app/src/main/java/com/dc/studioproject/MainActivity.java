package com.dc.studioproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //定义按钮
    private Button button_1;
    private Button button_2;
    //定义文本
    private EditText edit_text;

    //定义图片
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置吐司，当点击按钮时，提示你点击了该按钮
        button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击了该按钮！！！", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,BActivity.class);
                startActivity(intent);
//                Intent intent=new Intent(MainActivity.this,BActivity.class);
//                startActivity(intent);
            }
        });

        //拿到按钮2，也就是点我拿到内容按钮
        button_2 = (Button) findViewById(R.id.button_2);
        //把输入的内容，通过吐司拿出
        edit_text = (EditText) findViewById(R.id.edit_text);
        //设置button_2的点击事件
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_2:
                        String inputText = edit_text.getText().toString();
                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        //图片
        imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.image_view:
                        Toast.makeText(MainActivity.this, "图片已经切换。。。", Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.ff);
                        break;
                    default:
                        break;
                }
            }
        });

        Button button_close=(Button)findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }
}
