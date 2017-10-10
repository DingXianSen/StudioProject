package com.example.aidl_test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lesson24_service.User;
import com.example.lesson24_service.adil.IUserManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private IUserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setClassName("", "");
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            userManager = IUserManager.Stub.asInterface(service);//转换，注意不能强转
            try {
                //远程服务异常
                userManager.addUser(new User());
                userManager.getUsers();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        if (userManager!=null){
            try {
                List<User> users=userManager.getUsers();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
