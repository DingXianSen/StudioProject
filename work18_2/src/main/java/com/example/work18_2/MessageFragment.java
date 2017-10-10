package com.example.work18_2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class MessageFragment extends Fragment {
    private RadioGroup radioGroup;
    private View view ;
    private TextView textView;

    private Fragment_info info;
    private Fragment_th th;
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (view == null){
//            view = inflater.inflate(R.layout.fragment_message,null);
//        }
        view = inflater.inflate(R.layout.fragment_message,null);
        textView = (TextView) view.findViewById(R.id.tv1);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg1);

        info=new Fragment_info();
        th=new Fragment_th();

        fm = getFragmentManager();
        ft=fm.beginTransaction();
        ft.add(R.id.container,info,Fragment_info.class.getName()).add(R.id.container,th,Fragment_th.class.getName()).hide(th).show(info).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getFragmentManager();
                ft = fm.beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        ft.hide(th).show(info);
                        ft.commit();
                        break;
                    case R.id.rb2:
                        ft.hide(info).show(th);
                        ft.commit();
                        break;
                }
            }
        });
        return view;
    }
}
