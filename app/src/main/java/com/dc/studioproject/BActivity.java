package com.dc.studioproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2016/11/1.
 */

public class BActivity extends Activity {
    private EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里指定的是自身的Activity文件
        setContentView(R.layout.bactivity_main);
        //设置吐司，当点击按钮时，提示你点击了该按钮
        editText3 = (EditText) findViewById(R.id.edit_text3);

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                //拿到第二个页面的Edit Text中的文本值
                if (text.equals("exit")) {
                    BActivity.this.finish();
                }
        }

        @Override
        public void afterTextChanged (Editable s){

        }
    }

    );

}
}