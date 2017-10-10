package com.example.lesson25_recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by 怪蜀黍 on 2016/12/15.
 */

public class NvActivity extends AppCompatActivity {
    private NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(listener);
    }
    private NavigationView.OnNavigationItemSelectedListener listener=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Toast.makeText(NvActivity.this, ""+item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;//true的时候才能改变选中状态
        }
    };
}
