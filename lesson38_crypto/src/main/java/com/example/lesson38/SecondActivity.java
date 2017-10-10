package com.example.lesson38;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2017/1/5.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit;
    private TextView tvEn, tvDe;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        edit = (EditText) findViewById(R.id.edit);
        tvDe = (TextView) findViewById(R.id.desymmetry);
        tvEn = (TextView) findViewById(R.id.symmetry);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generator_key:
                String[] keys = UnsymmetryCrypto.generatorKey();
                if (keys != null) {
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("pub", keys[0]);
                    e.putString("pri", keys[1]);
                    e.apply();
                    Toast.makeText(this, "已生成并保存", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "生成失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.symmetry_en:
                String pub = sp.getString("pub", null);
                if (pub == null) {
                    Toast.makeText(this, "未生成pubkey", Toast.LENGTH_SHORT).show();
                    return;
                }
                String str = edit.getText().toString();
                tvDe.setText(Digest.md5(str)+"\n"+Digest.SHA1(str));
                if (!TextUtils.isEmpty(str)) {
                    tvEn.setText(UnsymmetryCrypto.encrypt(pub, str));
                }
                break;
            case R.id.symmetry_dn:
                String pri = sp.getString("pri", null);
                if (pri == null) {
                    Toast.makeText(this, "未生成prikey", Toast.LENGTH_SHORT).show();
                    return;
                }
                str = tvEn.getText().toString();
                if (!TextUtils.isEmpty(str)) {
                    tvDe.setText(UnsymmetryCrypto.decrypt(pri, str));
                }
                break;
        }
    }
}
