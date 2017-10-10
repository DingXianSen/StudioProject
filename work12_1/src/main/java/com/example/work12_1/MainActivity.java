package com.example.work12_1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private TextView tv_birthday;
    private TextView tv_message;
    private EditText et_message2;
    private TextView tv_max2;
    private int max = 140;
    private Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        获取日历对象
        calendar = Calendar.getInstance();
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_message = (TextView) findViewById(R.id.tv_message);
        et_message2 = (EditText) findViewById(R.id.et_message2);
        tv_max2 = (TextView) findViewById(R.id.tv_size2);
        object = new Object();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            点击未填写时，使用日期对话框选择日期
            case R.id.tv_birthday:
                addBirthday();
                break;
            case R.id.tv_message:
                addMessage();
                break;
            case R.id.getnew://检查更新
                getNewApp();
                newapplication();
                break;
        }
    }

    private void newapplication() {
    }

    /**
     * 检查更新
     */
    private int num;

    private void getNewApp() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("下载中...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//进度条
        dialog.setMax(100);
        dialog.show();
        new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 0; i <= dialog.getMax(); i++) {
                        dialog.setProgress(i);
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                对话框关闭
                    dialog.dismiss();
//                    让其在主线程显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialogs=new AlertDialog.Builder(MainActivity.this).setView(getLayoutInflater().inflate(R.layout.newapplication_style,null )).show();
                            dialogs.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "取消安装", Toast.LENGTH_SHORT).show();
                                    dialogs.dismiss();
                                }
                            });
                            dialogs.findViewById(R.id.anzhuang).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "安装完成", Toast.LENGTH_SHORT).show();
                                    dialogs.dismiss();
                                }
                            });
                        }
                    });
                }
            }
        }.start();
    }
private AlertDialog dialogs;
    /*
     * 点击意见方法
     */
    private static Dialog dialog;


    private void addMessage() {
        dialog = new AlertDialog.Builder(this).setView(getLayoutInflater().inflate(R.layout.message_style, null)).show();
        dialog.findViewById(R.id.et_message2).addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                dialog.findViewById(R.id.et_message2);
                View vs = dialog.findViewById(R.id.et_message2);
                dialog.findViewById(R.id.tv_size2);
                Log.e("message", "======================" + dialog.findViewById(R.id.et_message2));
                Toast.makeText(MainActivity.this, "=====onViewAttachedToWindow111111111111=====", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Toast.makeText(MainActivity.this, "=====onViewDetachedFromWindow2222222222=====", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 日期对话框
     */
    private void addBirthday() {
//        年
        int year = calendar.get(Calendar.YEAR);
//        月
        int month = calendar.get(Calendar.MONTH);
//        日
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // tv_birthday.setText(String.format("yyyy/MM/dd"),year,month,day);
                String str = year + "/" + (month + 1) + "/" + dayOfMonth;
                tv_birthday.setText(str);
            }
        }, year, month, day);
        dialog.show();
    }
}
