package com.example.lesson11_dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        获取日历对象
        calendar=Calendar.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message:
                showMessageDialog();    //最基本的dialog使用
                break;
            case R.id.items:
                showItemsDialog();
                break;
              /*============================================*/
            case R.id.single_items:
                showSingleItem();
                break;
            case R.id.multi_items:
                showMultiItems();
                break;
            case R.id.custom://自定义试图显示
                showCustomDialog();
                break;
//            设置bt1和bt2的监听
            case R.id.bt1:
            case R.id.bt2:
                if (dialog != null && dialog.isShowing()) {
                    //取消显示
                    dialog.cancel();//会触发取消监听
                    dialog.dismiss();//不会触发取消监听
                }
                break;

            /*============================================*/
            case R.id.progress:
                showProgressDialog();
                break;
            case R.id.date:
                showDateDialog();
                break;
            case R.id.time:
                showTimeDialog();
                break;
        }
    }
    /**
     * 11-18===================6 时间
     */
    private void showTimeDialog() {
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        calendar.set(2016,11,1,0,0,0);//设置日期
        calendar.add(Calendar.MONTH,1);//让月份+1
        calendar.add(Calendar.DAY_OF_MONTH,-1);//天-1
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, hour, minute, false);//false表示12小时，可以写大时间，会自动转换
//        要想获取系统时间，先定义全局Calendar
        dialog.show();
    }

    /**
     * 11-18===================5 日期
     */
    private void showDateDialog() {
        /**
         * 第二个参数，设置样式，android.R.style.Theme_Holo_Light_Dialog,
         */
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override//根据API的不同，调用的次数不同
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.e("aaaa",year+"=============="+month+"=============="+dayOfMonth);
            }
        }, year, month, day);
        dialog.show();
    }

    /**
     * 11-18===================4 进度条
     */
    private void showProgressDialog() {
        ProgressDialog pd=new ProgressDialog(this);
        pd.setMax(100);//设置最大进度值

        pd.setMessage("Loadding...");//消息
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        pd.setProgressNumberFormat(String.format("%d/%d",pd.getMax(),50));//
        pd.setProgressPercentFormat(new DecimalFormat("##0.00"));//修改进度百分比格式化，这个显示的是，0.5，
        pd.show();//默认
        pd.setProgress(50);//注意，进度要设置在show()后边
    }

    /**
     * 11-18===================3 自定义
     */
    private void showCustomDialog() {
//        全局dialog
        dialog = new AlertDialog.Builder(this)
//                .setView(R.layout.layout_dialog)//最低21版本
                .setView(getLayoutInflater().inflate(R.layout.layout_dialog, null))
                .show();
        dialog.findViewById(R.id.bt1).setOnClickListener(this);
        dialog.findViewById(R.id.bt2).setOnClickListener(this);

    }

    /**
     * 11-18===================2 复选框
     */
    private Dialog dialog;

    private void showMultiItems() {
//        new AlertDialog.Builder(this).setMultiChoiceItems(new String[]{"item1", "item2", "item3", "item4"},
//                new boolean[] {false,true,true,false},onMultiChoiceClickListener)
//                .show().
//              setCanceledOnTouchOutside(false);
//        或者
        dialog = new AlertDialog.Builder(this).setMultiChoiceItems(new String[]{"item1", "item2", "item3", "item4"},
                new boolean[]{false, true, true, false}, onMultiChoiceClickListener)
                .show();
        dialog.setCanceledOnTouchOutside(false);//点击窗体意外不消失
    }

    //    对应的点击事件
    DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Toast.makeText(MainActivity.this, which + "=============" + isChecked, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 按键事件
     *
     * @param keyCode keyCode触发键的键码
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        判断dialog是否为空，判断dialog是否正在显示中
        if (keyCode == KeyEvent.KEYCODE_BACK && dialog != null && dialog.isShowing()) {
            return true;//返回true事件表示已经处理完成，将不会咋调用后续处理
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 11-18===================1 单选列表
     */
    private void showSingleItem() {
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(new String[]{"item1", "item2", "item3", "item4"}, 0, diaOnCancelListener)
                .show();
    }

    private void showItemsDialog() {
        new AlertDialog.Builder(this)
                .setItems(new String[]{"item1", "item2", "item3", "item4"}, diaOnCancelListener)
                .show();
    }

    private void showMessageDialog() {
//        Dialog
        AlertDialog d = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("标题")
//                提示的信息
                .setMessage("再点一个试试")
//                对应的三个按钮
                .setNegativeButton("点就点", diaOnCancelListener)
                .setNeutralButton("点你咋地", diaOnCancelListener)
                .setPositiveButton("点你，不服啊", diaOnCancelListener)
//                show();创建并显示，这个show()相当与，.create.show();
                .create();//创建，
        d.show();//使其显示
    }

    /**
     * 选项的点击事件
     */
    private DialogInterface.OnClickListener diaOnCancelListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String str = "" + which;
            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    str = "哎呀，弄屎你";
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    str = "大哥，你牛B";
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    str = "服了，服了";
                    break;
                case 1://case 1是列表的第二项
                    str = "===============item";
                    break;
            }
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };
}
