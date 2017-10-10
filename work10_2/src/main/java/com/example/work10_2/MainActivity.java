package com.example.work10_2;

import android.content.Intent;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST=1;
private EditText et_info;
    private Button bt_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_info= (EditText) findViewById(R.id.et_info);
        bt_next= (Button) findViewById(R.id.bt_next);
    }

    @Override
    public void onClick(View v) {
        String info=et_info.getText().toString();
        Intent intent=new Intent(MainActivity.this,SeconedActivity.class);
        intent.putExtra("result",info);
       startActivityForResult(intent,REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&requestCode==1){
            et_info.setText(data.getStringExtra("resu"));
        }
    }
}
