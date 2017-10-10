package com.example.administrator.housework1114;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class updatepwd extends AppCompatActivity {
    private EditText edpwd;
    private TextView t1,t2,t3;
    private String qd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwdqd);
        t1 = (TextView) findViewById(R.id.qd1);
        t2 = (TextView) findViewById(R.id.qd2);
        t3 = (TextView) findViewById(R.id.qd3);
        edpwd = (EditText) findViewById(R.id.et1);
        edpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = edpwd.getText().toString();
                if (text!=null){
                    if (6<text.length()&&text.length()<20){
                        if (text.length()>8){
                            t1.setBackgroundResource(R.drawable.selqdstyle);
                            t2.setBackgroundResource(R.drawable.selqdstyle);
                            t3.setBackgroundResource(R.drawable.selqdstyle);
                            qd="强";
                        }if (text.length()==8){
                            t1.setBackgroundResource(R.drawable.selqdstyle);
                            t2.setBackgroundResource(R.drawable.selqdstyle);
                            qd = "中";
                        }if (text.length()>0&&text.length()<8){
                            t1.setBackgroundResource(R.drawable.selqdstyle);
                            qd="弱";
                        }
                    }
                }
            }
        });
    }
    public void houTui(View view){
        Intent intent = new Intent();
        intent.putExtra("qd",qd);
        setResult(1,intent);
        finish();
    }
}
