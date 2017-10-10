package com.example.lesson26_webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUrl=(EditText) findViewById(R.id.url);
    }

    @Override
    public void onClick(View v) {
        Intent in=new Intent(this,WebActivity.class);
        switch (v.getId()){
            case R.id.load_url:
                String url=etUrl.getText().toString();
                in.putExtra("action",0);
                in.putExtra("data",url);
                startActivity(in);
                break;
            case R.id.load_data:
                in.putExtra("action",1);
                startActivity(in);
                break;
        }
    }
}
