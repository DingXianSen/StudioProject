package com.dc.lesson06_style_shape_selector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb;
    private boolean isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb = (CheckBox) findViewById(R.id.cb);

        //如果控件是保存过的
        if(savedInstanceState!=null){//将recreate前保存的值重新赋值给控件
            cb.setChecked(savedInstanceState.getBoolean("check"));
        }
        //checkBox的监听事件
        cb.setOnCheckedChangeListener(changeListener);
    }

    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            cb.setOnCheckedChangeListener(null);
            if (isChecked) {
                //使用夜间模式
                //因为 setContentView(R.layout.activity_main);已经加载过，所以32行
               // isCheck=true;
                setTheme(R.style.AppTheme_Night);
            } else {
                //正常模式
               // isCheck=false;
                setTheme(R.style.AppTheme);
            }
            //重新create
            //recreate();
            //窗体背景不会跟着切换，需要给背景一个属性颜色
            setContentView(R.layout.activity_main);
            cb = (CheckBox) findViewById(R.id.cb);
            //注意一定要把监听放在46的后边，不然会出错
            cb.setChecked(isChecked);
            cb.setOnCheckedChangeListener(changeListener);
        }
    };

    /**
     * 保存控件状态
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存复选框的选中状态
        outState.putBoolean("check", cb.isChecked());
        super.onSaveInstanceState(outState);
    }
}
