package com.example.lesson17_fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg;
    private MessageFragment mf;
    private CanstactFragment cf;
    private ActivityFragment af;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*******************************************静态fragment*****************************************************/
//        Log.e("aaaa", "MainActivity========onCreate=======");
//        FragmentManager fm = getSupportFragmentManager();
////        fm.findFragmentByTag(),或者
//        Fragment fragment = fm.findFragmentById(R.id.fragment1);
////        获取所有已经加载的Fragment
//        List<Fragment> lis = fm.getFragments();
//        if (fragment instanceof Fragment1){
//            ((Fragment1)fragment).setText("233333333333333333333");
//        }
        /*******************************************静态fragment*****************************************************/
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(listener);
//        第一种，全部加载，显示隐藏替换
        mf = new MessageFragment();
        cf = new CanstactFragment();
        af = new ActivityFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, mf, MessageFragment.class.getName())
                .add(R.id.container, cf, CanstactFragment.class.getName())
                .add(R.id.container, af, ActivityFragment.class.getName())
//        隐藏
                .hide(cf)
                .hide(af)
//       事物完成
                .commit();
//        第一种，全部加载，显示隐藏替换

    }

    //    改变时的监听
    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            switch (checkedId) {
                case R.id.message:
//                    ft.replace(R.id.rongqi,new MessageFragment(),MessageFragment.class.getName());替换的方式
                    ft.hide(cf).hide(af).show(mf);
                    ft.commit();//在这注意别忘了提交
                    Log.e("aaaa", "===============message");
                    break;
                case R.id.constact:
                    ft.hide(mf).hide(af).show(cf);
                    ft.commit();
//                    ft.show(cf);
                    Log.e("aaaa", "===============constact");
                    break;
                case R.id.active:
                    ft.hide(cf).hide(mf).show(af);
                    ft.commit();
                    Log.e("aaaa", "===============active");
                    break;
            }
        }
    };


//    Activity的生命周期

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("aaaa", "MainActivity=======onStart========");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("aaaa", "MainActivity========onResume=======");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("aaaa", "MainActivity========onPause=======");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("aaaa", "MainActivity=======onStop========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("aaaa", "MainActivity=========onDestroy======");
    }

}
