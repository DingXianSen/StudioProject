package com.example.lesson35_media;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by 怪蜀黍 on 2017/1/3.
 */

public class VideoActivity extends AppCompatActivity {
    private VideoView vv;
    private Button button;
    private int orientation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vv = (VideoView) findViewById(R.id.vv);
        button = (Button) findViewById(R.id.orientation);

        File file = new File(Environment.getExternalStorageDirectory(), "Download/fangzong.mp4");
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        String path = file.getPath();
        vv.setVideoPath(path);
//        设置控制条
        vv.setMediaController(new MediaController(this));
        vv.start();

//        第一种横屏切换
//        if (savedInstanceState!=null){
//            vv.seekTo(savedInstanceState.getInt("position"));
//        }


        button.setOnClickListener(clickLis);
//      当前屏幕真正的方向
        orientation = getResources().getConfiguration().orientation;
    }

    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//        获取当前请求的屏幕方向
            int requested = getRequestedOrientation();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            vv.requestLayout();
            vv.getLayoutParams().width = dm.heightPixels;
            if (requested == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED &&//未指定
                    (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    || orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
        }
    };

    //    横屏竖屏发生变化时触发
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//       更改为新的屏幕方向值
        orientation=newConfig.orientation;
        if (orientation==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT||
                orientation==ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT){
            button.setText("全屏");
        }else{
            button.setText("还原");
        }
        DisplayMetrics dm = getResources().getDisplayMetrics();
        vv.requestLayout();
        vv.getLayoutParams().width = dm.widthPixels;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vv.isPlaying() && vv.canPause()) {
            vv.pause();
        }
    }

    @Override
    protected void onDestroy() {
        if (vv.isPlaying()) {
            vv.stopPlayback();
        }
        super.onDestroy();
    }

//          第一种横屏切换
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("position",vv.getCurrentPosition());
//    }


}
