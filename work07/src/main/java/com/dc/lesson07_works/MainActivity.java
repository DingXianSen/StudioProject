package com.dc.lesson07_works;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private ImageView photo;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;


    private static final int TO_ABCTIVITY = 30;
    private TextView tv_name;
    private TextView tv_telphone;
    private TextView tv_email;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_telphone = (TextView) findViewById(R.id.tv_telphone);
        tv_email = (TextView) findViewById(R.id.tv_email);
        photo = (ImageView) findViewById(R.id.photo);
        photo.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        tv_telphone.setOnClickListener(this);
        tv_email.setOnClickListener(this);
    }
    /*
            * 剪切图片
            */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
    * 判断sdcard是否被挂载
    */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo:
                // 激活系统图库，选择一张图片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                break;

            case R.id.tv_name:
                intent = new Intent(this, SeconedActivity.class);
                intent.putExtra("tv_name", tv_name.getText().toString());
                Toast.makeText(this, "" + tv_name.getText().toString(), Toast.LENGTH_SHORT).show();
                intent.putExtra("source", "name");
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_telphone:
                intent = new Intent(this, SeconedActivity.class);
                intent.putExtra("tv_phone", tv_telphone.getText().toString());
                Toast.makeText(this, "" + tv_telphone.getText().toString(), Toast.LENGTH_SHORT).show();
                intent.putExtra("source", "phone");
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_email:
                intent = new Intent(this, SeconedActivity.class);
                intent.putExtra("tv_email", tv_email.getText().toString());
                Toast.makeText(this, "" + tv_email.getText().toString(), Toast.LENGTH_SHORT).show();
                intent.putExtra("source", "email");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int    resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            tv_name.setText(data.getStringExtra("content"));
        }
        if (requestCode == 1 && resultCode == 2) {
            tv_telphone.setText(data.getStringExtra("content"));
        }
        if (requestCode == 1 && resultCode == 3) {
            tv_email.setText(data.getStringExtra("content"));

        }
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        }  else if (requestCode == PHOTO_REQUEST_CUT) {
                // 从剪切图片返回的数据
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    photo.setImageBitmap(bitmap);
                }
                try {
                    // 将临时文件删除
                    tempFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }