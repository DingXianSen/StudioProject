package com.dc.work09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/15.
 */
public class CActivity extends Activity implements View.OnClickListener {
    private EditText et_pwd;
    private TextView qd1, qd2, qd3;
    private String strong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        qd1= (TextView) findViewById(R.id.qd1);
        qd2= (TextView) findViewById(R.id.qd2);
        qd3= (TextView) findViewById(R.id.qd3);
        et_pwd= (EditText) findViewById(R.id.etpwd);

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = et_pwd.getText().toString();
                if (pass != null) {
                    if (6 < pass.length() && pass.length() < 20) {
                        if (pass.length() > 8) {//判断密码强度
                            qd1.setBackgroundResource(R.color.text);
                            qd2.setBackgroundResource(R.color.text);
                            qd3.setBackgroundResource(R.color.text);
                            strong = "强";
                        }
                        if (pass.length()==8 ) {//判断密码强度
                            qd1.setBackgroundResource(R.color.text);
                            qd2.setBackgroundResource(R.color.text);
                            qd3.setBackgroundResource(R.color.colorPrimaryDark);
                            strong = "中";

                        }
                        if (pass.length() < 8) {//判断密码强度
                            qd1.setBackgroundResource(R.color.text);
                            qd2.setBackgroundResource(R.color.colorPrimaryDark);
                            qd3.setBackgroundResource(R.color.colorPrimaryDark);
                            strong = "差";
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.putExtra("strong",strong);
        setResult(1,intent);
        finish();
    }
}