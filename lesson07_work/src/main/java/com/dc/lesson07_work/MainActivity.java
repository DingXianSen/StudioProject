package com.dc.lesson07_work;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private Button btn_adddate;
    private Button btn_removedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);
        btn_adddate=(Button)findViewById(R.id.adddate);
        btn_removedate=(Button)findViewById(R.id.removedate);
    }

    @Override
    public void onClick(View v) {
//        Intent intent=new Intent(this);
        switch (v.getId()){
            case R.id.adddate:

                break;
            case R.id.removedate:
                break;
        }
    }
}
