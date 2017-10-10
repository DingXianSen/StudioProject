package com.example.homewrok_1124;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar sb_1, sb_2, sb_3, sb_4;
    private ImageView image;
    private int tran;
    private float sf, xz, qx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= (ImageView) findViewById(R.id.image);
        sb_1 = (SeekBar) findViewById(R.id.progress1);
        sb_2 = (SeekBar) findViewById(R.id.progress2);
        sb_3 = (SeekBar) findViewById(R.id.progress3);
        sb_4 = (SeekBar) findViewById(R.id.progress4);

        sb_1.setOnSeekBarChangeListener(listener);
        sb_2.setOnSeekBarChangeListener(listener);
        sb_3.setOnSeekBarChangeListener(listener);
        sb_4.setOnSeekBarChangeListener(listener);
    }
    public SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.progress1://缩放
                    sf=0.5f+progress/10f;
                    break;
                case R.id.progress2://旋转
                    xz=progress;
                    break;
                case R.id.progress3://倾斜
                    qx=progress/10f;
                    break;
                case R.id.progress4://平移
                    tran=progress;
                    break;
            }
            Matrix matrix=new Matrix();
//            matrix.postRotate();//旋转
//            matrix.postRotate(tran,0);//评议tran
//            matrix.postRotate(qx);//倾斜sweek
            matrix.postScale(sf,sf);//缩放
            matrix.postRotate(xz);//旋转
            matrix.postSkew(qx,0);//倾斜
            matrix.postTranslate(tran,0);//评议
            image.setImageMatrix(matrix);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
