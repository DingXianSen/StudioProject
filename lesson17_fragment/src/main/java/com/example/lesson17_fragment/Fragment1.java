package com.example.lesson17_fragment;


import android.content.Context;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/28.
 */
//导入v4包
public class Fragment1 extends Fragment {
    public TextView textView;
    @Nullable
    @Override//3
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        第一步
        View v = inflater.inflate(R.layout.fragment_1, null);
        Log.e("aaaa","==========================onCreateView");
        textView= (TextView) v.findViewById(R.id.text);
        return v;
    }

    public void setText(String str){
        textView.setText(str);
    }
    //生命周期
    @Override//1
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("aaaa", "=========onAttach=========");
    }

    @Override//2
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("aaaa", "=========onCreate=========");
    }

    @Override//4
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("aaaa", "==========onActivityCreated========");
    }

    @Override//5
    public void onStart() {
        super.onStart();
        Log.e("aaaa", "===========onStart=======");
    }

    @Override//6
    public void onResume() {
        super.onResume();
        Log.e("aaaa", "==========onResume========");
    }

    @Override//7
    public void onPause() {
        super.onPause();
        Log.e("aaaa", "=========onPause=========");
    }

    @Override//8
    public void onStop() {
        super.onStop();
        Log.e("aaaa", "==========onStop========");
    }

    @Override//9
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("aaaa", "=========onDestroyView=========");
    }

    @Override//10
    public void onDestroy() {
        super.onDestroy();
        Log.e("aaaa", "========onDestroy==========");
    }

    @Override//11
    public void onDetach() {
        super.onDetach();
        Log.e("aaaa", "==========onDetach========");
    }

}
