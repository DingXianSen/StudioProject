package com.example.lesson25_recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2016/12/16.
 */

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout input1, input2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sliding_draw);
        input1 = (TextInputLayout) findViewById(R.id.input1);
        input2 = (TextInputLayout) findViewById(R.id.input2);

////        设置密码框的"眼睛",或者直接在XML中设置
//        input2.getEditText().setInputType(InputType.TYPE_CLASS_TEXT
//        |InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        input2.setPasswordVisibilityToggleEnabled(true);

        input2.getEditText().addTextChangedListener(watcher);
    }

    //   文本变化监听
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int len =s.length();
            if (len<6){
                input2.setErrorEnabled(true);
                input2.setError("密码长度必须大于6位");
            }else{
                input2.setErrorEnabled(false);
                input2.setError("");
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatbt:
            Snackbar.make(v, "LHW is  ZZ", Snackbar.LENGTH_INDEFINITE)
                    .setAction("yes", this).show();
                break;
            default:
                Toast.makeText(this, "=_=!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
