package com.dc.lesson06_theme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/*必须是AppCompatActivity，styles.xml必须是Day Night有两个样式的*/
public class MainActivity extends AppCompatActivity {
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb=(CheckBox) findViewById(R.id.mode);
        //第二种方法时要注意的 
        if(savedInstanceState!=null){
            cb.setChecked(savedInstanceState.getBoolean("check"));
        }
        cb.setOnCheckedChangeListener(changeListener);
    }
    private CompoundButton.OnCheckedChangeListener changeListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                //设置应用本地夜间模式是否开启
                //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//设置本地夜间模式是否开启



                //第二种
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                ///设置应用本地夜间模式关闭
               // getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);//设置本地夜间关闭

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            //第二种模式下需要调用recreate方法，并且需要注意复选框状态的改变，要不会引起循环
            recreate();
        }
    };

    /**
     * 第二种方法中才写这个方法
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("check",cb.isChecked());
        super.onSaveInstanceState(outState);
    }
}
