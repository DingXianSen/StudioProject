package com.example.lesson39_other_jar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.lesson39_other_jar.internet.MyFactory;
import com.example.lesson39_other_jar.internet.Programme;
import com.example.lesson39_other_jar.internet.Result;
import com.example.lesson39_other_jar.service.UserService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private UserService userService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.56.1:8080/")//注意地址最后一定要加一个/
                .baseUrl("http://www.toolsmi.com/starclass/")//注意地址最后一定要加一个/
                .addConverterFactory(new MyFactory())//转换工厂
                .build();

        userService = retrofit.create(UserService.class);
//        UserService userService = retrofit.create(UserService.class);
//        Call<String> call = userService.login("1", "10");
//        call.enqueue(callback);
//        call.execute();

//        注册想要接受信息的对象111111111111111111111111111
        EventBus.getDefault().register(this);
    }

    @Subscribe//通过注解来识别是要在接收消息时应该处理消息的方法333333333333333333333333333
    public void onEvent(Result result) {//222222222222222222222222222222
        Log.e("aaaa", "===================" + result.describe);
        Log.e("aaaa", "===================" + result.state);
        if (result.state == 1) {
            for (Programme p : result.data) {
                Log.e("aaaa", "===============" + p.getLname());
            }
        }
    }

    @Override
    protected void onDestroy() {
//        在不需要接受消息后取消对象注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private Callback<String> callback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            String json = response.body();
            Log.e("aaaa", "================json" + json);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    public void onClick(View v) {
//        Call<String> call = userService.login();
//        call.enqueue(callback);
        Call<Result> call = userService.login("1", "3");
        call.enqueue(resultCallback);
    }

    private Callback<Result> resultCallback = new Callback<Result>() {
        @Override
        public void onResponse(Call<Result> call, Response<Result> response) {
            Result result = response.body();
        //
        //            Log.e("aaaa", "===================" + result.describe);
        //            Log.e("aaaa", "===================" + result.state);
        //            if (result.state == 1) {
        //                for (Programme p : result.data) {
        //                    Log.e("aaaa", "===============" + p.getLname());
        //                }
        //            }


        //            发送消息4444444444444444444444444

            EventBus.getDefault().post(result);
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {

        }
    };
}
