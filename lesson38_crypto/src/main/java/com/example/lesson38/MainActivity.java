package com.example.lesson38;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvEn,tvDe;
    private EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEn= (TextView) findViewById(R.id.symmetry);
        tvDe= (TextView) findViewById(R.id.desymmetry);
        edit= (EditText) findViewById(R.id.edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.symmetry_en:
                String str=edit.getText().toString();
                if (!TextUtils.isEmpty(str)){
                    tvEn.setText(SymmetryCrypto.encrypt(str,"qaqqaq",SymmetryCrypto.AES));
                }
                break;
            case R.id.symmetry_dn:
                str =tvEn.getText().toString();
                if (!TextUtils.isEmpty(str)){
                    tvDe.setText(SymmetryCrypto.decrypt(str,"qaqqaq",SymmetryCrypto.AES));
                }
                break;
        }
    }
}