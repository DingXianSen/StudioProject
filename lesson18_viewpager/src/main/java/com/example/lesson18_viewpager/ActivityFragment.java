package com.example.lesson18_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 怪蜀黍 on 2016/11/28.
 */

public class ActivityFragment extends Fragment {


//注意访问修饰符是protected
//    /**
//     * 用来Fragment的场景的状态保存
//     *
//     * @param outState
//     */
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean("f", f);
//    }
//    /**
//     * 用来Fragment的场景的状态保存
//     *或者
//     */
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//    }

//    protected void onRestoreInstanceState(Bundle savedIn);
    private View v;

    private boolean f;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_activity, null);
        }
        //表示异常结束
        if (savedInstanceState != null) {
//            回复场景
//            f=savedInstanceState.getBoolean("f");
        }
        return v;
//
    }
}
