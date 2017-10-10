package com.dc.lesson07_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 怪蜀黍 on 2016/11/10.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityM.addActivity(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        ActivityM.removeActivity(this);
        super.onDestroy();
    }
}
