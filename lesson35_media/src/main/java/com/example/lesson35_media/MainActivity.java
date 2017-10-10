package com.example.lesson35_media;

import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer player;
    private Button btPlay;
    private String path;
    private SeekBar progress;
    private SurfaceView sv;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btPlay = (Button) findViewById(R.id.play);
        progress = (SeekBar) findViewById(R.id.progress);
        sv = (SurfaceView) findViewById(R.id.sv);
        btPlay.setTag(null);

        progress.setOnSeekBarChangeListener(sbchange);


//        首先播放视频时要求屏幕常量
        sv.setKeepScreenOn(true);
//        获取Surface Holder用来进行Surface的绘制的控制
        sv.getHolder().addCallback(callback);
        btPlay.setEnabled(false);
        progress.setEnabled(false);
    }

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override//surface创建完成
        public void surfaceCreated(SurfaceHolder holder) {
            btPlay.setEnabled(true);
            progress.setEnabled(true);
        }

        @Override//surface的 大小发生改变时调用
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override//surface销毁时调用
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    private SeekBar.OnSeekBarChangeListener sbchange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {//当前进度变化是否来源于用户拖拽操作
                if (player != null) {
                    int position = seekBar.getProgress();
//            跳转到制定的时间位置
                    player.seekTo(position);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
//            去除自动更新进度
            handler.removeCallbacks(r);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
//            重新开始自动更新进度
            handler.postDelayed(r, 1000);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                Boolean tag = (Boolean) v.getTag();
                if (tag == null) {
                    initPlayer();
                } else if (tag) {
                    player.pause();//暂停播放
                    btPlay.setTag(false);
                    btPlay.setText("播放");
                } else {
                    player.start();//开始播放
                    btPlay.setTag(true);
                    btPlay.setText("暂停");
                }
                break;
            case R.id.stop:
                if (player.isPlaying()) {
                    player.stop();//停止
                    btPlay.setTag(null);
                    btPlay.setText("播放");
                }
                break;
        }
    }

    private void initPlayer() {
        if (path == null) {
//            File file=new File(Environment.getExternalStorageDirectory(),"Download/faded.mp3");
            File file = new File(Environment.getExternalStorageDirectory(), "Download/fangzong.mp4");
            if (!file.exists()) {
                Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
                return;
            }
            path = file.getPath();
        }

        btPlay.setText("准备中...");
        if (player == null) {
            player = new MediaPlayer();
        } else {
            player.reset();//重置播放器
        }
//        设置要播放的资源
        try {
            player.setDataSource(path);


//            视频播放时，只要添加这一句话
//            设置画面显示，同时在xml中添加SurfaceView控件
            player.setDisplay(sv.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        设置准备监听
        player.setOnPreparedListener(preparedLis);
//        网络加载缓冲监听
        player.setOnBufferingUpdateListener(bufferLis);
//        播放完成监听
        player.setOnCompletionListener(completeLis);

        player.prepareAsync();//异步准备
    }

    private MediaPlayer.OnBufferingUpdateListener bufferLis = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            //设置缓冲进度
            progress.setSecondaryProgress(percent);
        }
    };


    private MediaPlayer.OnCompletionListener completeLis = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            btPlay.setTag(null);
            btPlay.setText("播放");
            progress.setProgress(0);
        }
    };


    private MediaPlayer.OnPreparedListener preparedLis = new MediaPlayer.OnPreparedListener() {
        @Override//准备完成时调用
        public void onPrepared(MediaPlayer mp) {
            int w = mp.getVideoWidth();
            int h = mp.getVideoHeight();
            if (w != 0 && h != 0) {
                int sw = getResources().getDisplayMetrics().widthPixels;
                sv.getLayoutParams().width = w;
                sv.getLayoutParams().height = h * sw / w;

            }


            //开始播放
            mp.start();
            btPlay.setTag(true);//设置为正在播放
            btPlay.setText("暂停");
//            设置音频时间为最大值
            progress.setMax(mp.getDuration());
            handler.post(r);
        }
    };

    private Runnable r = new Runnable() {
        @Override
        public void run() {
//          设置进度值为当前播放的时间位置
            progress.setProgress(player.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }
    };
}
