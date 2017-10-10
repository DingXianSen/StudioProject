package com.dc.work3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/7.
 */

public class MainSecondActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button bt1;
    private Button bt2;


    ///SeekBar
    private SeekBar seekBar;

    private TextView progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);

/*======================================================================================*/
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        progress=(TextView)findViewById(R.id.progress);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt1:
                        int curr = progressBar.getProgress();//获取
                        //
                        Log.e("info", "===================================" + curr);
                        if (curr > 0) {
                            progressBar.setProgress(curr - 1);//设置
                        }
                        break;
                    case R.id.bt2:
                        curr = progressBar.getProgress();
                        if (curr < progressBar.getMax()) {//如果它小于最大值
                            progressBar.setProgress(curr + 1);
                        }
                        break;
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt1:
                        int curr = progressBar.getProgress();//获取
                        //
                        Log.e("info", "===================================" + curr);
                        if (curr > 0) {
                            //设置进度条的长度，减少
                            progressBar.setProgress(curr - 1);//设置
                        }
                        break;
                    case R.id.bt2:
                        //获取进度条的长度，
                        curr = progressBar.getProgress();
                        if (curr < progressBar.getMax()) {//如果它小于最大值
                            progressBar.setProgress(curr + 1);
                        }
                        break;
                }
            }
        });

    }
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        //进度改变后调用

        /**
         *
         * @param seekBar
         * @param p   当前进度
         * @param fromUser  进度改变是否是用户操作，反例看电影时的进度条
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int p, boolean fromUser) {
            /*格式化      第一个%整数 ，第三个百分号，这样出来的格式是。例10%  */
            progress.setText(String.format("%d%%",p));
        }

        //当手指触碰到拖拽按钮时调用   当开始跟踪触摸时
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            progress.setVisibility(View.VISIBLE);
        }

        //当手指离开拖拽按钮时调用      当停止跟踪时
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            progress.setVisibility(View.INVISIBLE);
        }
    };
}
